import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import {connect} from "react-redux";
import {withStyles} from "material-ui/styles/index";

import {getView} from "./selectors";
import {EMPTY_LIST} from "../../constants/Pool";
import {createWidget} from "../index";
import {isAppLoaded} from "../app/selectors";
import {isContentsLoaded} from "../../meta/index";
import MetaStore from "../../meta/MetaStore";
import LoadingIndicator from "../../components/LoadingIndicator";


const styles = theme => ({
});
class View extends PureComponent {

    componentWillReceiveProps(nextProps, nextContext) {
        if (nextProps.isAppLoaded && !isContentsLoaded(nextProps.view)) {
            MetaStore.load(nextProps.view.uipath);
        }
    }

    render() {
        const { isAppLoaded, view, dispatch } = this.props;
        let result;
        if (view && isAppLoaded) {
            if (isContentsLoaded(view)) {
                result = (view.allContents || EMPTY_LIST).map(content => createWidget(content, {dispatch}));
            } else {
                result = <LoadingIndicator />
            }
        } else {
            result = null;
        }
        return result;
    }

}
View.propTypes = {
    isAppLoaded: PropTypes.bool.isRequired,
    view: PropTypes.object
};

/*
 * isAppLoaded is not much helpful for this component, but is needed to refresh props
 */
const mapStateToProps = (state, props) => ({
    isAppLoaded: isAppLoaded(state),
    view: getView(state, props)
});
const mapDispatchToProps = (dispatch) => ({
    dispatch: dispatch
});

export default withStyles(styles)(connect(
    mapStateToProps,
    mapDispatchToProps
)(View));