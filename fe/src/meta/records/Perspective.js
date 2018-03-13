import { Record } from 'immutable';

import WidgetContainerProps from "./WidgetContainer";
import {PERSPECTIVE} from "../../constants/WidgetType";

const PerspectiveProps = Object.assign(
    {},
    WidgetContainerProps,
    {
        widgetType: PERSPECTIVE
    }
);

const PerspectiveRecord = new Record(PerspectiveProps);

export {
    PerspectiveProps
}
export default PerspectiveRecord;