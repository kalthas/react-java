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
        case ActionTypes.UPDATE:
            return state.mutateProp(action.payload);
        default:
            return state;
    }
};

export {
    viewsReducer
}