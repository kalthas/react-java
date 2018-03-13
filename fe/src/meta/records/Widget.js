import {DEFAULT} from "../../constants/WidgetType";

class RecordStatus {
    static UNINITIALIZED = "UNINITIALIZED";
    static INITIALIZED = "INITIALIZED";
}

const IdentifiableProps = {
    uipath: ""
};

const WidgetProps = Object.assign(
    {},
    IdentifiableProps,
    {
        name: "",
        displayName: null,
        visible: false,
        widgetType: DEFAULT,

        // This prop is front-end only, which is used to tracking record's lifecycle
        recordStatus: RecordStatus.UNINITIALIZED
    }
);

export {
    IdentifiableProps,
    RecordStatus
}
export default WidgetProps;