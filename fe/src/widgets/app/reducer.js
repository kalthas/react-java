import ApplicationRecord from "../../records/Application";

const applicationReducer = (state = new ApplicationRecord(), action) => {
    switch (action.type) {
        case 'LOAD':
            return action.record;
        default:
            return state;
    }
};

export default applicationReducer;