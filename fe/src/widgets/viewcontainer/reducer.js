import ViewContainerRecord from "../../records/ViewContainer";
import {SWITCH_VIEW} from "./action";

const viewContainerReducer = (state = new ViewContainerRecord(), action) => {
    switch (action.type) {
        case SWITCH_VIEW:
            return state.activeView.set(action.uipath);
        default:
            return state;
    }
};

export default viewContainerReducer;