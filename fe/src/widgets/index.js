import Immutable from 'immutable';

import {
    FORM
} from '../constants/WidgetType';
import FormWidget from './form/Form';

const TYPE_WIDGET_MAPPING = Immutable.fromJS({
    [FORM]: FormWidget
});

const getWidgetClass = (widgetType) => TYPE_WIDGET_MAPPING.get(widgetType);

export {
    getWidgetClass
}
