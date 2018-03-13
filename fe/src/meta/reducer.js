import { combineReducers } from 'redux-immutable';

import { applicationReducer } from '../widgets/app/reducer';
import { viewsReducer } from "../widgets/view/reducer";

export default combineReducers({
    application: applicationReducer,
    views: viewsReducer
})