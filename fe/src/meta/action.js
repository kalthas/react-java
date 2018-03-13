import fromJS from "./index";

class ActionTypes {

    static ADD = "ADD";

}

const addRecord = (record) => ({
    type: ActionTypes.ADD,
    record
});

const makeAddRecord = (recordJson) => {
    return (dispatch) => {
        const record = fromJS(recordJson, record => dispatch(addRecord(record)));
        dispatch(addRecord(record));
    }
};

export {
    ActionTypes,
    makeAddRecord
}