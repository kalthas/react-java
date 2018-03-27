import { PureComponent } from 'react';
import Immutable from 'immutable';

import {EMPTY_LIST} from "../constants/Pool";
import {EventType} from "../meta/records/Event";
import {uiEvent} from "../meta/action";

class EventsAware extends PureComponent {

    constructor(props) {
        super(props);
        this.events =(this.props.record.events || EMPTY_LIST).reduce((evtMap, event) => (
            evtMap.set(event.type, event)
        ), Immutable.fromJS({}));
    }

    handleClick = () => {
        const event = this.events.get(EventType.ONCLICK);
        if (event) {
            this.props.dispatch(uiEvent({
                uipath: this.props.record.uipath,
                event
            }));
        }
    }

}

export default EventsAware;