import { createSelector } from 'reselect';

import { getApplication } from "../selectors";

export const getTreeProps = createSelector(
    [getApplication],
    (application) => application
);