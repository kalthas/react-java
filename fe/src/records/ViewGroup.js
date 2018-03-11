import { Record } from 'immutable';

import WidgetContainerProps from "./WidgetContainer";
import {VIEW_GROUP} from "../constants/WidgetType";

const ViewGroupProps = Object.assign(
    {},
    WidgetContainerProps,
    {
        widgetType: VIEW_GROUP
    }
);

const ViewGroupRecord = new Record(ViewGroupProps);

export {
    ViewGroupProps
}
export default ViewGroupRecord;