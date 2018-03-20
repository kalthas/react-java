import { Map, Record } from 'immutable';

const findUpdatePattern = (content, relativePath) => {
    const result = ['allContents'];
    let target = content;
    relativePath.forEach((path, pindex) => {
        target.allContents.forEach((content, index) => {
            if (content.name === path) {
                target = content;
                result.push(index);
                if (pindex < relativePath.length-1) {
                    result.push('allContents');
                }
            }
        });
    });
    return result;
};

class ViewsRecord extends Record({
    views: new Map(),
    viewIds: []
}) {
    addView(record) {
        const newViewIds = this.viewIds.concat(record.uipath).sort((a, b) => a.length - b.length);
        return this.set('views', this.views.set(record.uipath, record)).set('viewIds', newViewIds);
    }

    uiInput(payload) {
        const { uipath, newValue } = payload;
        const viewId = this.viewIds.find((id) => uipath.startsWith(id));
        const relativePath = uipath.substr(viewId.length+1).split('/');
        const view = this.views.get(viewId);
        const pattern = findUpdatePattern(view, relativePath).concat('value');
        return this.set('views', this.views.set(viewId, view.setIn(pattern, newValue)));
    }

}

export default ViewsRecord;