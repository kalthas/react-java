import React, { PureComponent } from 'react';
import TextField from 'material-ui/TextField';

class Text extends PureComponent {

    render() {
        const { record } = this.props;
        return (
            <TextField
                id={record.uipath}
                label={record.displayName}
                value={record.value}
                margin='normal'
            />
        )
    }

}

export default Text;