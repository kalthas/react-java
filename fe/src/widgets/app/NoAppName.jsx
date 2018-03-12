import React from 'react';
import Typography from 'material-ui/Typography';

const NoAppName = () => (
    <div>
        <Typography color='error' variant='headline'>
            No application name is specified in the URL. Please specify, say for app named 'appName', {window.location.href}appName
        </Typography>
    </div>
);

export default NoAppName;