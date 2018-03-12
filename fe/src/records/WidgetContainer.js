import { List } from 'immutable';
import WidgetProps from './Widget';

const WidgetContainerProps = Object.assign(
    {},
    WidgetProps,
    {
        allContents: new List()
    }
);

export default WidgetContainerProps;