import { PureComponent } from 'react';
import Immutable from 'immutable';

import {EMPTY_LIST} from "../constants/Pool";
import {EventType} from "../meta/records/Event";
import metaStore from '../meta/MetaStore';

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
            metaStore.fireEvent(this.props.record.uipath, event);
        }
    }

}

export default EventsAware;