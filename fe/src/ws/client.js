import Rx from 'rxjs';
import MessageTypes from './message-types';

class WebSocketStatus {
    static CONNECTING = 0;// The connection is not yet open.
    static OPEN = 1; //	The connection is open and ready to communicate.
    static CLOSING = 2; //The connection is in the process of closing.
    static CLOSED = 3;
}

export class WsClient {

    constructor(url, options) {
        const {
            lazy = false,
        } = (options || {});

        this.wsImpl = window.WebSocket || window.MozWebSocket;

        if (!this.wsImpl) {
            throw new Error('Unable to find native implementation for WebSocket!');
        }

        this.url = url;
        this.unsentMessagesQueue = [];
        this.lazy = !!lazy;
        this.closedByUser = false;
        this.client = null;
        this.subject = null;

        if (!this.lazy) {
            this.connect();
        }
    }

    get status() {
        if (this.client === null) {
            return WebSocketStatus.CLOSED;
        }

        return this.client.readyState;
    }

    subscribe(onNext) {
        this.subject.subscribe(onNext);
    }

    close(isForced = true, closedByUser = true) {
        if (this.client !== null) {
            this.closedByUser = closedByUser;

            if (isForced) {
                this.sendMessage(undefined, MessageTypes.CONNECTION_TERMINATE, undefined);
            }

            this.client.close();
            this.client = null;
        }
    }

    connect() {
        if (this.status !== WebSocketStatus.CLOSED) {
            return;
        }
        this.client = new this.wsImpl(this.url, 'react-view-ws');
        this.subject = new Rx.Subject();

        this.client.onopen = () => {
            this.closedByUser = false;

            // Send CONNECTION_INIT message, no need to wait for connection to success (reduce roundtrips)
            this.sendMessage(undefined, MessageTypes.CONNECTION_INIT, undefined);
        };

        this.client.onclose = () => {
            this.subject.complete();
            if ( !this.closedByUser ) {
                this.close(false, false);
            }
        };

        this.client.onerror = () => {
            this.subject.error(new Error("client onerror"));
            // Capture and ignore errors to prevent unhandled exceptions, wait for
            // onclose to fire before attempting a reconnect.
        };

        this.client.onmessage = ({ data }) => {
            this.processReceivedData(data);
        };
    }

    load(uipath) {
        this.sendMessage(undefined, MessageTypes.LOAD, {uipath});
    }

    sendMessage (id, type, payload) {
        this.sendMessageRaw(this.buildMessage(id, type, payload));
    }

    updatesAndEvent(updates, event) {
        this.sendMessage(undefined, MessageTypes.SYNC_EVENT, {updates, event});
    }

    fireEvent(uipath, content) {
        this.sendMessage(undefined, MessageTypes.EVENT, {uipath, content})
    }

    sendMessageRaw (message) {
        switch (this.status) {
            case this.wsImpl.OPEN:
                const serializedMessage = JSON.stringify(message);
                this.client.send(serializedMessage);
                break;
            case this.wsImpl.CONNECTING:
                this.unsentMessagesQueue.push(message);
                break;
            default:
                if (!this.reconnecting) {
                    throw new Error('A message was not sent because socket is not connected, is closing or ' +
                        'is already closed. Message was: ' + JSON.stringify(message));
                }
        }
    }

    buildMessage (id, type, payload) {
        const payloadToReturn = payload;
        return {
            id: id,
            type: type,
            payload: payloadToReturn,
        };
    }

    flushUnsentMessagesQueue () {
        this.unsentMessagesQueue.forEach((message) => this.sendMessageRaw(message));
        this.unsentMessagesQueue = [];
    };

    processReceivedData(receivedData) {
        let parsedMessage;

        try {
            parsedMessage = JSON.parse(receivedData);
        } catch (e) {
            throw new Error(`Message must be JSON-parseable. Got: ${receivedData}`);
        }

        switch (parsedMessage.type) {
            case MessageTypes.CONNECTION_ERROR:
                console.error("WebSocket connection error: ", parsedMessage);
                break;

            case MessageTypes.CONNECTION_ACK:
                this.reconnecting = false;
                this.flushUnsentMessagesQueue();
                break;

            case MessageTypes.CONTENT:
            case MessageTypes.ERROR:
                this.subject.next(parsedMessage);
                break;

            case MessageTypes.CONNECTION_KEEP_ALIVE:
                // what to do?
                break;

            default:
                throw new Error('Invalid message type!');
        }
    }

}

export default WsClient;