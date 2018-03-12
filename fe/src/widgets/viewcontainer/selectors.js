import { createSelector } from 'reselect';

import {getViewContainer, getViews} from "../selectors";

const getActiveViewUIPath = createSelector(
    [getViewContainer],
    (viewContainer) => (viewContainer.activeView)
);

export const getActiveView = createSelector(
    [getActiveViewUIPath, getViews],
    (activeViewUIPath, views) => (
        views.get(activeViewUIPath)
    )
);