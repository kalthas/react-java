import {Map} from "immutable";
import {ActionTypes} from "../../meta/action";
import {VIEW} from "../../constants/WidgetType";

const viewsReducer = (state = new Map(), action) => {
    switch (action.type) {
        case ActionTypes.ADD:
            if (action.record.widgetType === VIEW) {
                return state.set(action.record.uipath, action.record);
            } else {
                return state;
            }
        default:
            return state;
    }
};

export {
    viewsReducer
}