import { Record } from 'immutable';

class EventType {
    static ONCLICK = "ONCLICK";
}

const EventProps = {
    type: null,
    field: ""
};

const EventRecord = new Record(EventProps);

export {
    EventType,
    EventProps
}
export default EventRecord;
