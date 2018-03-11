import { Record } from 'immutable';

import WidgetContainerProps from "./WidgetContainer";
import {APPLICATION} from "../constants/WidgetType";

const ApplicationProps = Object.assign(
    {},
    WidgetContainerProps,
    {
        widgetType: APPLICATION
    }
);

const ApplicationRecord = new Record(ApplicationProps);

export {
    ApplicationProps
}
export default ApplicationRecord;