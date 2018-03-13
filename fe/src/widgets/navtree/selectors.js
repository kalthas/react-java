import { createSelector } from 'reselect';

import { getApplication } from "../selectors";
import {RecordStatus} from "../../meta/records/Widget";
import {isNonEmptyArray} from "../../utils/TypeUtils";

const widgetRecordToNode = (record) => (
    isNonEmptyArray(record.allContents) ? {
        key: record.uipath,
        text: record.displayName,
        children: record.allContents.map(widgetRecordToNode)
    } : {
        key: record.uipath,
        text: record.displayName
    }
);

export const getRoots = createSelector(
    [getApplication],
    (application) => (
        application.recordStatus === RecordStatus.UNINITIALIZED ? null : (
            application.allContents.map(widgetRecordToNode)
        )
    )
);