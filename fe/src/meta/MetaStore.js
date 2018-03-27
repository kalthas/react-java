import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

import WsClient from "../ws/client";
import MessageType from "../ws/message-types";
import rootReducer from './reducer';
import {ActionTypes, makeAddRecord} from "./action";
import changeInterceptor from "./changeInterceptor";
import MutationSubject from '../subjects/MutationSubject';
import EventSubject from '../subjects/EventSubject';
import {isNonEmptyArray} from "../utils/TypeUtils";

class MetaStore {

    constructor() {
        this._reduxStore = createStore(
            rootReducer,
            applyMiddleware(changeInterceptor, thunk)
        );
        this.loading = new Set();

        const combinedStream = MutationSubject
            .scan(
                (buffered, action) => {
                    if (action) {
                        buffered[action.payload.uipath] = action;
                    } else {
                        buffered = {};
                    }
                    return buffered;
                }, {}
            ).combineLatest(
                EventSubject,
                (buffered, eventAction) => ({buffered, eventAction})
            ).first()
            .repeat()
            .publish();

        combinedStream.subscribe(({buffered, eventAction}) => {
            const updates = [];
            for (let key in buffered) {
                updates.push(buffered[key]);
            }
            if (isNonEmptyArray(updates)) {
                this.updatesAndEvent(updates, eventAction);
            } else {
                this.fireEvent(eventAction);
            }
        });
        combinedStream.connect();
    }

    get reduxStore() {
        return this._reduxStore;
    }

    getClient() {
        if (!this.wsClient) {
            this.wsClient = new WsClient(`ws://${window.location.host}/ws`);
            this.wsClient.subscribe((data) => {
                if (data.type === MessageType.CONTENT) {
                    this.loading.delete(data.payload.uipath);
                    this._reduxStore.dispatch(makeAddRecord(data.payload.content));
                } else {
                    console.error('incompatible data');
                }
            });
        }
        return this.wsClient;
    }

    load(uiPath) {
        if (!this.loading.has(uiPath)) {
            this.getClient().load(uiPath);
            this.loading.add(uiPath);
        }
    }

    updatesAndEvent(updates, eventAction) {
        const updatePayloads = updates.filter(update => update.type === ActionTypes.UPDATE)
                                      .map(update => update.payload);
        const eventPayload = {uipath: eventAction.payload.uipath, content: eventAction.payload.event};
        this.getClient().updatesAndEvent(updatePayloads, eventPayload);
    }

    fireEvent(eventAction) {
        const {uipath, event} = eventAction.payload;
        this.getClient().fireEvent(uipath, event);
    }

}

export default new MetaStore();