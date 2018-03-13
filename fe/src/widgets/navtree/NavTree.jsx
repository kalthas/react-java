import React, { PureComponent } from 'react';
import {connect} from "react-redux";
import { withRouter } from 'react-router-dom';

import {getRoots} from "./selectors";
import Tree from '../../components/Tree';

const mapStateToProps = (state) => ({
    roots: getRoots(state)
});

class NavTree extends PureComponent {

    handleLeafClick = (uipath) => {
        this.props.history.push(uipath);
    };

    render() {
        return <Tree onLeafClick={this.handleLeafClick} roots={this.props.roots} />
    }

}

export default withRouter(connect(
    mapStateToProps
)(NavTree));