import { Record, List } from 'immutable';


const ViewContainerProps = {
    activeView: "",
    views: new List()
};

const ViewContainerRecord = new Record(ViewContainerProps);

export {
    ViewContainerProps
}
export default ViewContainerRecord;