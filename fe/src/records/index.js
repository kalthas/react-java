import ApplicationRecord, { ApplicationProps} from "./Application";
import PerspectiveRecord, { PerspectiveProps } from "./Perspective";
import ViewGroupRecord, { ViewGroupProps } from "./ViewGroup";
import ViewRecord, { ViewProps} from "./View";

const Meta = [
    [ApplicationRecord, ApplicationProps],
    [PerspectiveRecord, PerspectiveProps],
    [ViewGroupRecord, ViewGroupProps],
    [ViewRecord, ViewProps]
];
const TypeMetaMap = Meta.reduce((accumulator, cur) => {
    accumulator[cur[1].widgetType] = cur; return accumulator;
}, {});
const fromJS = (json) => {
    const meta = TypeMetaMap[json && json.widgetType];
    if (meta) {
        const [Record, Props] = meta;
        const isContainer = Props.hasOwnProperty("allContents");
        if (isContainer && json.allContents && Array.isArray(json.allContents)) {
            json.allContents = json.allContents.map(fromJS);
        }
        return new Record(json);
    }
    return undefined;
};

export {
    ApplicationRecord,
    PerspectiveRecord,
    ViewGroupRecord,
    ViewRecord
}
export default fromJS;