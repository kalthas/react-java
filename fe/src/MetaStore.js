import { createStore } from 'redux';

import WsClient from "./ws/client";
import fromJS from "./records/index";
import MessageType from "./ws/message-types";
import rootReducer from './widgets/reducer';

class MetaStore {

    constructor() {
        this._reduxStore = createStore(rootReducer);
    }

    get reduxStore() {
        return this._reduxStore;
    }

    getClient() {
        if (!this.wsClient) {
            this.wsClient = new WsClient(`ws://${window.location.host}/ws`);
            this.wsClient.subscribe((data) => {
                if (data.type === MessageType.CONTENT) {
                    const record = fromJS(data.payload);
                    this._reduxStore.dispatch({
                        type: 'LOAD',
                        record
                    });
                } else {
                    console.error('incompatible data');
                }
            });
        }
        return this.wsClient;
    }

    load(uiPath) {
        this.getClient().load(uiPath);
    }

}

export default new MetaStore();