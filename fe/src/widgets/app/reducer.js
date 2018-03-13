import ApplicationRecord from "../../meta/records/Application";
import {ActionTypes} from "../../meta/action";
import {APPLICATION} from "../../constants/WidgetType";

const applicationReducer = (state = new ApplicationRecord(), action) => {
    switch (action.type) {
        case ActionTypes.ADD:
            if (action.record.widgetType === APPLICATION) {
                return action.record;
            } else {
                return state;
            }
        default:
            return state;
    }
};

export {
    applicationReducer
};