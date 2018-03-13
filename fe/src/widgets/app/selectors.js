import { createSelector } from 'reselect';

import {getApplication} from "../selectors";
import {isContentsLoaded} from "../../meta/index";

const isAppLoaded = createSelector(
    [getApplication],
    (application) => isContentsLoaded(application)
);

export {
    isAppLoaded
}