import React from 'react';
import Immutable from 'immutable';

import {
    FIELD,
    FORM,
    BUTTON
} from '../constants/WidgetType';
import FormWidget from './form/Form';
import Field from './field';
import Button from "./button/Button";

const TYPE_WIDGET_MAPPING = Immutable.fromJS({
    [FORM]: FormWidget,
    [FIELD]: Field,
    [BUTTON]: Button
});

const getWidgetClass = (widgetType) => TYPE_WIDGET_MAPPING.get(widgetType);
const createWidget = (content, props) => {
    const WidgetClass = getWidgetClass(content.widgetType);
    return <WidgetClass key={content.uipath} record={content} {...props} />
};

export {
    getWidgetClass,
    createWidget
}
