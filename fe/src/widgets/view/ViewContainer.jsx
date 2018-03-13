import React, { PureComponent } from 'react';
import PropTypes from 'prop-types';

import View from "./View";
import {likeViewUIPath} from "../../utils/PathUtils";


class ViewContainer extends PureComponent {

    render() {
        const uipath = this.props.matchingPath;
        return (
            likeViewUIPath(uipath) ?
                <View uipath={uipath} /> :
                null
        );
    }

}
ViewContainer.propTypes = {
    matchingPath: PropTypes.string.isRequired,
};

export default ViewContainer;