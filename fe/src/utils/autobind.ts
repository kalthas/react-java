export default function (target: any, propertyKey: string, descriptor: PropertyDescriptor) {
    let fn = descriptor.value;

    if (typeof fn !== 'function') {
        throw new Error(`@autobind decorator can only be applied to methods not: ${typeof fn}`);
    }

    // In IE11 calling Object.defineProperty has a side-effect of evaluating the
    // getter for the property which is being replaced. This causes infinite
    // recursion and an "Out of stack space" error.
    let definingProperty = false;

    return {
        configurable: true,
        get() {
            if (definingProperty || this === target.prototype || this.hasOwnProperty(propertyKey)
                || typeof fn !== 'function') {
                return fn;
            }

            let boundFn = fn.bind(this);
            definingProperty = true;
            Object.defineProperty(this, propertyKey, {
                configurable: true,
                get() {
                    return boundFn;
                },
                set(value) {
                    fn = value;
                    delete this[propertyKey];
                }
            });
            definingProperty = false;
            return boundFn;
        },
        set(value: any) {
            fn = value;
        }
    };
}