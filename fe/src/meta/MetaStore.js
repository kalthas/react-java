import { createStore, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';

import WsClient from "../ws/client";
import MessageType from "../ws/message-types";
import rootReducer from './reducer';
import {makeAddRecord} from "./action";

class MetaStore {

    constructor() {
        this._reduxStore = createStore(
            rootReducer,
            applyMiddleware(thunk)
        );
        this.loading = new Set();
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
                    this._reduxStore.dispatch(makeAddRecord(data.payload));
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

}

export default new MetaStore();