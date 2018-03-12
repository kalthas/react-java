import { Record } from 'immutable';

import WidgetContainerProps from "./WidgetContainer";
import {VIEW} from "../constants/WidgetType";
import {IdentifiableProps} from "./Widget";

const ViewProps = Object.assign(
    {},
    WidgetContainerProps,
    {
        widgetType: VIEW
    }
);

const ViewRecord = new Record(ViewProps);

const ViewRefProps = Object.assign(
    {},
    IdentifiableProps
);

const ViewRefRecord = new Record(ViewRefProps);

export {
    ViewProps,
    ViewRefProps,
    ViewRefRecord
}
export default ViewRecord;