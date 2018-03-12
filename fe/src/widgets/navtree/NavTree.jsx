import React, {PureComponent} from 'react';
import List, { ListItem, ListItemText } from 'material-ui/List';
import { CircularProgress } from 'material-ui/Progress';
import ExpandLess from 'material-ui-icons/ExpandLess';
import ExpandMore from 'material-ui-icons/ExpandMore';
import Collapse from 'material-ui/transitions/Collapse';
import {connect} from "react-redux";
import autobind from 'autobind-decorator';

import {getTreeProps} from "./selectors";
import {RecordStatus} from "../../records/Widget";

class NavTree extends PureComponent {

    constructor(props) {
        super(props);
        this.state = {
            open: {}
        }
    }

    handleClick = uipath => () => {
        this.setState(prevState => ({
            open: {
                ...prevState.open,
                [uipath]: prevState.open[uipath] || false
            }
        }));
    };

    @autobind
    createItem(item, indent) {
        console.log('createItem for ' + item.uipath);
        const nextIndent = indent + 1;
        const hasContents = item.allContents && (item.allContents.length > 0);
        const collapse = hasContents ? item.allContents.reduce((accumulator, current) => (
                                            accumulator.concat(this.createItem(current, nextIndent))
                                        ), []) : null;
        const isOpen = this.state.open[item.uipath] || true;
        const retItem = (
            <ListItem button key={`i-${item.uipath}`} onClick={this.handleClick(item.uipath)}>
                <ListItemText inset={indent!==0} primary={item.title}/>
                {
                    hasContents &&
                    (isOpen ? <ExpandLess /> : <ExpandMore />)
                }
            </ListItem>
        );
        return hasContents ? [retItem, (
            <Collapse key={`c-${item.uipath}`} in={isOpen} timeout="auto" unmountOnExit>
                <List component="div">
                    {
                        collapse
                    }
                </List>
            </Collapse>
        )] : [retItem];
    }

    render() {
        const { tree } = this.props;
        const result = tree.allContents.reduce((accumulator, current) => (
            accumulator.concat(this.createItem(current, 0))
        ), []);
        return tree.recordStatus === RecordStatus.UNINITIALIZED ?
            <CircularProgress/> :
            (
                <List component="nav">
                    {
                        result
                    }
                </List>
            );
    }

}

const mapStateToProps = (state) => ({
    tree: getTreeProps(state)
});
const mapDispatchToProps = (dispatch) => ({

});

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(NavTree);