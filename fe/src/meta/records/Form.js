import { Record } from 'immutable';

import WidgetContainerProps from "./WidgetContainer";
import {FORM} from "../../constants/WidgetType";

const FormProps = Object.assign(
    {},
    WidgetContainerProps,
    {
        widgetType: FORM
    }
);

const FormRecord = new Record(FormProps);

export {
    FormProps
}
export default FormRecord;