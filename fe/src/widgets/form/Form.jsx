import React, { PureComponent } from 'react';
import Paper from 'material-ui/Paper';
import {withStyles} from "material-ui/styles/index";

import {EMPTY_LIST} from "../../constants/Pool";
import {BUTTON} from "../../constants/WidgetType";
import {isContentsLoaded} from "../../meta";
import {createWidget} from "../index";
import LoadingIndicator from "../../components/LoadingIndicator";

const styles = theme => ({
    container: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
        padding: theme.spacing.unit
    },
    buttons: {
        display: 'flex',
        alignItems: 'center',
        boxSizing: 'border-box',
        marginTop: theme.spacing.unit * 2,
        padding: `${theme.spacing.unit}px ${theme.spacing.unit / 2}px`,
        [theme.breakpoints.up('sm')]: {
            padding: `${theme.spacing.unit}px ${theme.spacing.unit * 1.5}px`,
        },
        alignSelf: 'flex-end'
    }
});
class Form extends PureComponent {

    componentWillMount() {
        this.buttonClass = {
            className: this.props.classes.button
        };
    }

    render() {
        const { record, classes, dispatch } = this.props;
        const buttons = [];
        const fields = [];
        (record.allContents || EMPTY_LIST).forEach(content => {
            if (content.widgetType === BUTTON) {
                buttons.push(content);
            } else {
                fields.push(content);
            }
        });
        let result;
        const createWithDispatch = content => createWidget(content, {dispatch});
        if (isContentsLoaded(record)) {
            result = fields.map(createWithDispatch).concat([
                <div key='buttons' className={classes.buttons}>
                    { buttons.map(createWithDispatch) }
                </div>
            ]);
        } else {
            result = <LoadingIndicator />
        }
        return (
            <Paper className={classes.container}>
                { result }
            </Paper>
        )
    }

}

export default withStyles(styles)(Form);