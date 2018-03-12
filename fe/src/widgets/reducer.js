import { combineReducers } from 'redux-immutable';
import { Map } from 'immutable';

import applicationReducer from './app/reducer';
import ViewContainerRecord from "../records/ViewContainer";

const viewContainerReducer = (state = new ViewContainerRecord(), action) => {
    switch (action.type) {
        default:
            return state;
    }
};

const viewsReducer = (state = new Map(), action) => {
    switch (action.type) {
        default:
            return state;
    }
};

export default combineReducers({
    application: applicationReducer,
    viewContainer: viewContainerReducer,
    views: viewsReducer
})