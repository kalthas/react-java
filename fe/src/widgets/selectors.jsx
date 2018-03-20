const getApplication = (state) => state.get('application');
const getViews = (state) => state.get('views').views;

export {
    getApplication,
    getViews
}