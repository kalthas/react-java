import React from 'react';
import { default as MuiButton } from 'material-ui/Button';

import EventsAware from "../../components/EventsAware";

class Button extends EventsAware {

    render() {
        const { record } = this.props;
        return (
            <MuiButton variant="raised" color="primary" onClick={this.handleClick}>
                { record.displayName }
            </MuiButton>
        )
    }

}

export default Button;