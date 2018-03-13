import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';
import {connect} from "react-redux";
import {withStyles} from "material-ui/styles/index";
import {CircularProgress} from "material-ui/Progress";

import {getView} from "./selectors";
import {EMPTY_LIST} from "../../constants/Pool";
import {getWidgetClass} from "../index";
import {isAppLoaded} from "../app/selectors";
import {isContentsLoaded} from "../../meta/index";
import MetaStore from "../../meta/MetaStore";


const styles = theme => ({
    progressContainer: {
        height: 44 + theme.spacing.unit * 4,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center'
    }
});
class View extends PureComponent {

    componentWillReceiveProps(nextProps, nextContext) {
        if (nextProps.isAppLoaded && !isContentsLoaded(nextProps.view)) {
            MetaStore.load(nextProps.view.uipath);
        }
    }

    render() {
        const { isAppLoaded, view, classes } = this.props;
        let result;
        if (view && isAppLoaded) {
            if (isContentsLoaded(view)) {
                result = (view.allContents || EMPTY_LIST).map(content => {
                    const WidgetClass = getWidgetClass(content.widgetType);
                    return <WidgetClass key={content.uipath} uipath={content.uipath} />
                });
            } else {
                result = <div className={classes.progressContainer}>
                    <CircularProgress/>
                </div>
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
    // ?
});

export default withStyles(styles)(connect(
    mapStateToProps,
    mapDispatchToProps
)(View));