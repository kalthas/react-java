import { List } from 'immutable';
export const isString = (obj) => (typeof obj === 'string');
export const isNonEmptyArray = (array) => (
    (Array.isArray(array) && (array.length > 0)) ||
    (List.isList(array) && (array.size > 0))
);