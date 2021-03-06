import { List } from 'immutable';
import ApplicationRecord, { ApplicationProps} from "./records/Application";
import PerspectiveRecord, { PerspectiveProps } from "./records/Perspective";
import ViewGroupRecord, { ViewGroupProps } from "./records/ViewGroup";
import ViewRecord, { ViewProps} from "./records/View";
import {RecordStatus} from "./records/Widget";
import {isNonEmptyArray} from "../utils/TypeUtils";
import {VIEW} from "../constants/WidgetType";
import FormRecord, {FormProps} from "./records/Form";
import FieldRecord, {FieldProps, FieldType} from "./records/Field";
import ButtonRecord, {ButtonProps} from "./records/Button";
import EventRecord from "./records/Event";
import {EMPTY_LIST} from "../constants/Pool";

const Meta = [
    [ApplicationRecord, ApplicationProps],
    [PerspectiveRecord, PerspectiveProps],
    [ViewGroupRecord, ViewGroupProps],
    [ViewRecord, ViewProps],
    [FormRecord, FormProps],
    [FieldRecord, FieldProps],
    [ButtonRecord, ButtonProps]
];
const TypeMetaMap = Meta.reduce((accumulator, cur) => {
    accumulator[cur[1].widgetType] = cur; return accumulator;
}, {});
const NormalizedTypes = new Set([
    VIEW
]);

/*
 * This fromJS works similarly as native Immutable.fromJS. Only that it is aware of react view records. Note that
 * View will be normalized. say
 *
 *   perspective1:
 *     ...perspective1Props
 *     allContents:
 *       view1:
 *         uipath: "app/perspective1/view1"
 *         ...view1Props
 *         allContents:
 *           ...view1Contents
 *       view2:
 *         uipath: "app/perspective1/view2"
 *         ...view2Props
 *         allContents
 *           ...view2Contents
 *       viewGroup1:
 *         ...viewGroup1Props
 *         allContents:
 *           view3:
 *             uipath: "app/perspective1/viewGroup1/view3"
 *             ...view3Props
 *             allContents:
 *               ..view3Contents
 *
 * will be converted into :
 *
 *   perspective1:
 *     ...perspective1Props
 *     allContents:
 *       view1:
 *         uipath: "app/perspective1/view1"
 *         ...view1Props
 *         allContents:
 *           []
 *       view2:
 *         uipath: "app/perspective1/view2"
 *         ...view2Props
 *         allContents
 *           []
 *       viewGroup1:
 *         ...viewGroup1Props
 *         allContents:
 *           view3:
 *             uipath: "app/perspective1/viewGroup1/view3"
 *             ...view3Props
 *             allContents:
 *               []
 *
 *   views:
 *     "app/perspective1/view1":
 *       uipath: "app/perspective1/view1"
 *       ...view1Props
 *       allContents:
 *         ...view1Contents
 *     "app/perspective1/view2":
 *       uipath: "app/perspective1/view2"
 *       ...view2Props
 *       allContents
 *         ...view2Contents
 *     "app/perspective1/viewGroup1/view3":
 *       uipath: "app/perspective1/viewGroup1/view3"
 *       ...view3Props
 *       allContents:
 *         ..view3Contents
 *
 */
const fromJS = (json, addRecord, forNormalize=false) => {
    const meta = TypeMetaMap[json && json.widgetType];
    if (meta) {
        const [Record, Props] = meta;
        const hasEvents = Props.hasOwnProperty("events");
        if (hasEvents && isNonEmptyArray(json.events)) {
            const allEvents = json.events.reduce((evtMap, event) => {
                const fieldName = event.field || json.name;
                const perFieldEvents = evtMap.get(fieldName) || [];
                perFieldEvents.push(event);
                evtMap.set(fieldName, perFieldEvents);
                return evtMap;
            }, new Map());
            json.events = (allEvents.get(json.name) || EMPTY_LIST).map(event => new EventRecord(event));
            if (json.allContents) {
                json.allContents.forEach(content => {
                    if (allEvents.has(content.name)) {
                        content.events = new List(allEvents.get(content.name));
                    }
                });
            }
        }
        const isContainer = Props.hasOwnProperty("allContents");
        if (isContainer && isNonEmptyArray(json.allContents)) {
            for (let i=0; i<json.allContents.length; i++) {
                const content = json.allContents[i];
                if (!forNormalize && NormalizedTypes.has(content.widgetType)) {
                    const record = fromJS(content, addRecord, true);
                    addRecord(record);
                    json.allContents[i] = record.set('allContents', []);
                } else {
                    json.allContents[i] = fromJS(content, addRecord);
                }
            }
        }
        if (Array.isArray(json.allContents)) {
            json.allContents = new List(json.allContents);
        }
        json.recordStatus = RecordStatus.INITIALIZED;
        return new Record(json);
    }
    return undefined;
};

const isContentsLoaded = (record) => (
    record && record.contentsLoaded
);

export {
    ApplicationRecord,
    PerspectiveRecord,
    ViewGroupRecord,
    ViewRecord,
    FormRecord,
    FieldRecord,
    ButtonRecord,
    FieldType,

    isContentsLoaded
}
export default fromJS;