import React from 'react';
import Immutable from "immutable";

import { FieldType } from '../../meta';
import Text from './Text';

const TYPE_FIELD_MAPPING = Immutable.fromJS({
    [FieldType.TEXT]: Text
});

const Field = (props) => {
    const FieldClass = TYPE_FIELD_MAPPING.get(props.record.type);
    return <FieldClass record={props.record} />
};

export default Field;