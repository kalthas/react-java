import ApplicationRecord, { ApplicationProps} from "./Application";
import PerspectiveRecord, { PerspectiveProps } from "./Perspective";
import ViewGroupRecord, { ViewGroupProps } from "./ViewGroup";
import ViewRecord, { ViewProps} from "./View";
import {RecordStatus} from "./Widget";

const Meta = [
    [ApplicationRecord, ApplicationProps],
    [PerspectiveRecord, PerspectiveProps],
    [ViewGroupRecord, ViewGroupProps],
    [ViewRecord, ViewProps]
];
const TypeMetaMap = Meta.reduce((accumulator, cur) => {
    accumulator[cur[1].widgetType] = cur; return accumulator;
}, {});

/*
 * This fromJS works similarly as native Immutable.fromJS. Only that it is aware of react view records. Not that
 * View will be normalized. say for
 *
 *   perspective1:
 *     ...perspective1Props
 *     allContents:
 *       view1:
 *         uipath: "app/perspective1/view1"
 *         ...view1Props
 *       view2:
 *         uipath: "app/perspective1/view2"
 *         ...view2Props
 *       viewGroup1:
 *         ...viewGroup1Props
 *         allContents:
 *           view3:
 *             uipath: "app/perspective1/viewGroup1/view3"
 *             ...view3Props
 *
 * will be converted into :
 *
 *   perspective1:
 *     ...perspective1Props
 *     allContents:
 *       "app/perspective1/view1"
 *       "app/perspective1/view2"
 *       viewGroup1:
 *         ...viewGroup1Props
 *         allContents:
 *           "app/perspective1/viewGroup1/view3"
 *           ...view3Props
 *
 *   viewContainer:
 *     activeView: uipath of view1 or view2 or view3
 *
 *   views:
 *     "app/perspective1/view1":
 *       uipath: "app/perspective1/view1"
 *       ...view1Props
 *     "app/perspective1/view2":
 *       uipath: "app/perspective1/view2"
 *       ...view2Props
 *     "app/perspective1/viewGroup1/view3":
 *       uipath: "app/perspective1/viewGroup1/view3"
 *       ...view3Props
 *
 */
const fromJS = (json) => {
    const meta = TypeMetaMap[json && json.widgetType];
    if (meta) {
        const [Record, Props] = meta;
        const isContainer = Props.hasOwnProperty("allContents");
        if (isContainer && json.allContents && Array.isArray(json.allContents)) {
            json.allContents = json.allContents.map(fromJS);
        }
        json.recordStatus = RecordStatus.INITIALIZED;
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