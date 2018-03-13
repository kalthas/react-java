import React, { PureComponent } from 'react';
import { default as MuiButton } from 'material-ui/Button';

class Button extends PureComponent {

    render() {
        const { record } = this.props;
        return (
            <MuiButton variant="raised" color="primary">
                { record.displayName }
            </MuiButton>
        )
    }

}

export default Button;