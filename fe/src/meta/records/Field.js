import { Record } from 'immutable';

import {FIELD} from "../../constants/WidgetType";
import WidgetProps from "./Widget";

class FieldType {
    static TEXT = "TEXT";
}

const FieldProps = Object.assign(
    {},
    WidgetProps,
    {
        type: FieldType.TEXT,
        value: null,
        valueType: "",
        widgetType: FIELD
    }
);

const FieldRecord = new Record(FieldProps);

export {
    FieldType,
    FieldProps
}
export default FieldRecord;