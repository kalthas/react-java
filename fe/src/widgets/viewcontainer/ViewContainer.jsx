import React, { PureComponent } from 'react';
import {
    Switch,
    Route
} from "react-router-dom";
import {connect} from "react-redux";
import { CircularProgress } from 'material-ui/Progress';
import { withStyles } from 'material-ui/styles';

import {getActiveView} from "./selectors";

const styles = theme => ({
    progressContainer: {
        height: 44 + theme.spacing.unit * 4,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center'
    }
});
class ViewContainer extends PureComponent {

    render() {
        const { match, classes } = this.props;
        return (
            <div>
                <div className={classes.progressContainer}>
                    <CircularProgress/>
                </div>
            {/*<Switch>*/}
                {/*<Route path={`${match.path}/:perspectiveName/:viewName`} component={(props)=><div>{props.match.url}</div>} />*/}
            {/*</Switch>*/}
            </div>
        );
    }

}

const mapStateToProps = (state) => ({
    activeView: getActiveView(state)
});
const mapDispatchToProps = (dispatch) => ({
});

export default withStyles(styles)(connect(
    mapStateToProps,
    mapDispatchToProps
)(ViewContainer));