export default class MessageType {
    static CONNECTION_INIT = "CONNECTION_INIT"; // Client -> Server
    static CONNECTION_ACK = "CONNECTION_ACK"; // Server -> Client
    static CONNECTION_ERROR = "CONNECTION_ERROR"; // Server -> Client
    static CONNECTION_KEEP_ALIVE = "CONNECTION_KEEP_ALIVE"; // Server -> Client
    static CONNECTION_TERMINATE = "CONNECTION_TERMINATE"; // Client -> Server
    static LOAD = "LOAD"; // Client -> Server
    static CONTENT = "CONTENT"; // Server -> Client
    static EVENT = "EVENT"; //  Client -> Server
    static ERROR = "ERROR";  //  Server -> Client
}
