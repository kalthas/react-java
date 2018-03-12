import { combineReducers } from 'redux-immutable';
import { Map } from 'immutable';

import applicationReducer from './app/reducer';
import viewContainerReducer from "./viewcontainer/reducer";

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