import fromJS from "./index";

class ActionTypes {

    /*
     * actions recognized by both front-end & back-end
     */
    static ADD = "ADD";
    static UPDATE = "UPDATE";
    static EVENT = "EVENT";

}

const addRecord = (record) => ({
    type: ActionTypes.ADD,
    record
});

const uiInput = (uipath, propName, newValue) => ({
    type: ActionTypes.UPDATE,
    payload: {
        uipath,
        propName,
        value: newValue
    }
});

const uiEvent = (payload) => ({
    type: ActionTypes.EVENT,
    payload
});

const makeAddRecord = (recordJson) => {
    return (dispatch) => {
        const record = fromJS(recordJson, record => dispatch(addRecord(record)));
        dispatch(addRecord(record));
    }
};

const isMutationAction = (action) => (
    action.type && action.type === ActionTypes.UPDATE
);

const isEventAction = (action) => (
    action.type && action.type === ActionTypes.EVENT
);

export {
    ActionTypes,
    makeAddRecord,
    uiInput,
    uiEvent,
    isEventAction,
    isMutationAction
}