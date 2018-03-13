import { Record } from 'immutable';

import {BUTTON} from "../../constants/WidgetType";
import WidgetProps from "./Widget";

const ButtonProps = Object.assign(
    {},
    WidgetProps,
    {
        color: "",
        widgetType: BUTTON
    }
);

const ButtonRecord = new Record(ButtonProps);

export {
    ButtonProps
}
export default ButtonRecord;