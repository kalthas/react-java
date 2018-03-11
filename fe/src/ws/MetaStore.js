import WsClient from "./client";
import fromJS from "../records";
import MessageType from "./message-types";

class MetaStore {

    getClient() {
        if (!this.wsClient) {
            this.wsClient = new WsClient(`ws://${window.location.host}/ws`);
        }
        return this.wsClient;
    }

    load(uiPath, cb, err) {
        const client = this.getClient();
        client.load(uiPath);
        client.subscribe((data) => {
            if (data.type === MessageType.CONTENT) {
                const record = fromJS(data.payload);
                cb(record);
            } else {
                err('incompatible data');
            }
        });
    }

}

export default new MetaStore();