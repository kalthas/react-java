import MutationSubject from '../subjects/MutationSubject';
import EventSubject from '../subjects/EventSubject';
import {
    isEventAction,
    isMutationAction
} from "./action";

const changeInterceptor = store => next => action => {
    if (isEventAction(action)) {
        EventSubject.next(action);
        MutationSubject.next(null);
        return store;
    }
    if (isMutationAction(action)) {
        MutationSubject.next(action);
    }
    return next(action)
};

export default changeInterceptor;