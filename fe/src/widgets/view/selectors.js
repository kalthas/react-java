import { createSelector } from 'reselect';

import {getViews} from "../selectors";

const getViewUIPath = (state, props) => {
    return props.uipath;
};

const getView = createSelector(
    [getViews, getViewUIPath],
    (views, uipath) => (views.get(uipath))
);

export {
    getView
}