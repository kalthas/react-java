package com.aiexpanse.react.intf.websockets.protocol;

/*
 *  * Messages Flow *
 *
 *   This is a demonstration of client-server communication, in order to get a better understanding of the protocol flow:
 *
 *   ** Session Init Phase **
 *    The phase initializes the connection between the client and server, and usually will also build the server-side
 *    context for the execution.
 *
 *    Client sends CONNECTION_INIT message to the server.
 *    Server calls onConnect callback with the init arguments, waits for init to finish and returns it's return value
 *    with CONNECTION_ACK + CONNECTION_KEEP_ALIVE (if used), or CONNECTION_ERROR in case of false or thrown exception
 *    from onConnect callback.
 *    Client gets CONNECTION_ACK + CONNECTION_KEEP_ALIVE (if used) and waits for subsequent communication.
 *
 *   ** Connected Phase **
 *    This phase called per each operation the client request to execute:
 *
 *    LOAD {id: /ui/path/to/load}
 *    CONTENT {id: /ui/path/of/content, content: {*content goes here*}}
 *    EVENT {id: /ui/path/of/event, type: eventType, payload: {*event payload*}}
 *    ERROR {type: errorType}
 *
 *    When client is being redirect, or being closed, it should try to send CONNECTION_TERMINATE.
 *
 */
public enum MessageType {

    CONNECTION_INIT, // Client -> Server
    CONNECTION_ACK, // Server -> Client
    CONNECTION_ERROR, // Server -> Client

    // NOTE: The keep alive message type does not follow the standard due to connection optimizations
    CONNECTION_KEEP_ALIVE, // Server -> Client

    CONNECTION_TERMINATE, // Client -> Server

    LOAD, // Client -> Server
    CONTENT, // Server -> Client
    EVENT, //  Client -> Server
    ERROR;  //  Server -> Client

}
