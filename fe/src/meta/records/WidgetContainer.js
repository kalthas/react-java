import { List } from 'immutable';
import WidgetProps from './Widget';

const WidgetContainerProps = Object.assign(
    {},
    WidgetProps,
    {
        contentsLoaded: false,
        allContents: new List()
    }
);

export default WidgetContainerProps;