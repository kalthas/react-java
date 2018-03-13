export const isString = (obj) => (typeof obj === 'string');
export const isNonEmptyArray = (array) => (Array.isArray(array) && (array.length > 0));