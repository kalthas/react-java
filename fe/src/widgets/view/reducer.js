import {ActionTypes} from "../../meta/action";
import {VIEW} from "../../constants/WidgetType";
import ViewsRecord from "../../meta/records/Views";

const viewsReducer = (state = new ViewsRecord(), action) => {
    switch (action.type) {
        case ActionTypes.ADD:
            if (action.record.widgetType === VIEW) {
                return state.addView(action.record);
            } else {
                return state;
            }
        case ActionTypes.UI_INPUT:
            return state.uiInput(action.payload);
        default:
            return state;
    }
};

export {
    viewsReducer
}