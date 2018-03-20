import React, { PureComponent } from 'react';
import TextField from 'material-ui/TextField';

import { uiInput } from '../../meta/action';

class Text extends PureComponent {

    handleChange = (event) => {
        const newValue = event.target.value;
        this.props.dispatch(uiInput({
            uipath: this.props.record.uipath,
            newValue
        }));
    };

    render() {
        const { record } = this.props;
        return (
            <TextField
                id={record.uipath}
                label={record.displayName}
                value={record.value || ""}
                margin='normal'
                onChange={this.handleChange}
            />
        )
    }

}

export default Text;