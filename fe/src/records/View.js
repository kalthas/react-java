import { Record } from 'immutable';

import WidgetContainerProps from "./WidgetContainer";
import {VIEW} from "../constants/WidgetType";

const ViewProps = Object.assign(
    {},
    WidgetContainerProps,
    {
        widgetType: VIEW
    }
);

const ViewRecord = new Record(ViewProps);

export {
    ViewProps
}
export default ViewRecord;