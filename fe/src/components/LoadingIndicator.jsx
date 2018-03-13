import React, { PureComponent } from 'react';
import {CircularProgress} from "material-ui/Progress";
import {withStyles} from "material-ui/styles/index";

const styles = theme => ({
    progressContainer: {
        height: 44 + theme.spacing.unit * 4,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center'
    }
});
class LoadingIndicator extends PureComponent {

    render() {
        return <div className={this.props.classes.progressContainer}>
            <CircularProgress/>
        </div>
    }

}

export default withStyles(styles)(LoadingIndicator);