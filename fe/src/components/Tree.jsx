/*
 * Supports below structure as props:
 *
 *   {
 *     defaultExpand: true|false
 *     roots: [
 *       {
 *          key: "root1",
 *          text: "Root 1"
 *          children: [
 *            {
 *              key: "root1/c1",
 *              text: "Child 1"
 *            },
 *            {
 *              key: "root1/c2",
 *              text: "Child 2"
 *            }
 *          ]
 *       },
 *       {
 *          key: "root2",
 *          text: "Root 2"
 *       }
 *     ]
 *   }
 *
 * Note that child key must starts with parent key as above.
 * If roots is null then loading indicator shows instead of tree
 *
 */

import React, {Fragment, PureComponent} from 'react';
import PropTypes from 'prop-types';
import Immutable from 'immutable';
import { CircularProgress } from 'material-ui/Progress';
import List, { ListItem, ListItemText } from 'material-ui/List';
import Collapse from 'material-ui/transitions/Collapse';
import ExpandLess from 'material-ui-icons/ExpandLess';
import ExpandMore from 'material-ui-icons/ExpandMore';
import { withStyles } from 'material-ui/styles';

import {isNonEmptyArray} from "../utils/TypeUtils";


const styles = theme => ({
    progressContainer: {
        height: 44 + theme.spacing.unit * 4,
        display: 'flex',
        justifyContent: 'center',
        alignItems: 'center'
    },
    inset1: {
        '&:first-child': {
            paddingLeft: theme.spacing.unit
        }
    },
    inset2: {
        '&:first-child': {
            paddingLeft: theme.spacing.unit * 2
        }
    },
    inset3: {
        '&:first-child': {
            paddingLeft: theme.spacing.unit * 3
        }
    }
});

class Tree extends PureComponent {

    constructor(props) {
        super(props);
        this.state = {
            open: Immutable.fromJS({})
        };
    }

    isOpen = (nodeKey, state) => (
        (state || this.state).open.has(nodeKey) ? (state || this.state).open.get(nodeKey) : this.props.defaultExpand
    );

    handleNodeToggle = (nodeKey, isLeaf) => () => {
        if (isLeaf) {
            this.props.onLeafClick && this.props.onLeafClick(nodeKey);
        } else {
            this.setState(prevState => ({
                open: prevState.open.setIn([nodeKey], !this.isOpen(nodeKey, prevState))
            }));
        }
    };

    createNode = (node, indent=0) => {
        const nextIndent = indent + 1;
        const hasChildren = isNonEmptyArray(node.children);
        const childNodes = hasChildren ? node.children.map(childNode => this.createNode(childNode, nextIndent)) : null;
        const isOpen = this.isOpen(node.key);
        const resultNode = (
            <ListItem button key={`i-${node.key}`} onClick={this.handleNodeToggle(node.key, !hasChildren)}>
                <ListItemText primary={node.text} className={this.props.classes[`inset${indent}`]}/>
                {
                    hasChildren &&
                    (isOpen ? <ExpandLess /> : <ExpandMore />)
                }
            </ListItem>
        );
        return hasChildren ?
            <Fragment key={node.key}>
                { resultNode }
                <Collapse in={isOpen} timeout="auto" unmountOnExit>
                    <List disablePadding component="div">
                        {
                            childNodes
                        }
                    </List>
                </Collapse>
            </Fragment> :
            resultNode;
    };

    render() {
        console.log('render Tree');
        const { roots, classes } = this.props;
        const loading = roots === null || roots === undefined;
        if (loading) {
            return (
                <div className={classes.progressContainer}>
                    <CircularProgress/>
                </div>
            );
        }
        const result = roots.map(this.createNode);
        return (
            <List disablePadding component="div">
                {
                    result
                }
            </List>
        );
    }

}

Tree.propTypes = {
    roots: PropTypes.object,
    defaultExpand: PropTypes.bool,
    onLeafClick: PropTypes.func
};
Tree.defaultProps = {
    defaultExpand: true
};

export default withStyles(styles)(Tree);