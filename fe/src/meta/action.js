import fromJS from "./index";

class ActionTypes {

    static ADD = "ADD";
    static UI_INPUT = "UI_INPUT";

}

const addRecord = (record) => ({
    type: ActionTypes.ADD,
    record
});

const uiInput = (payload) => ({
    type: ActionTypes.UI_INPUT,
    payload
});

const makeAddRecord = (recordJson) => {
    return (dispatch) => {
        const record = fromJS(recordJson, record => dispatch(addRecord(record)));
        dispatch(addRecord(record));
    }
};

export {
    ActionTypes,
    makeAddRecord,
    uiInput
}