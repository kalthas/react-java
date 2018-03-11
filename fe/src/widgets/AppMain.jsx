import React, { Component } from 'react';
import PropTypes from 'prop-types';
import { withStyles } from 'material-ui/styles';
import classNames from 'classnames';
import AppBar from 'material-ui/AppBar';
import Typography from 'material-ui/Typography';
import Toolbar from 'material-ui/Toolbar';
import IconButton from 'material-ui/IconButton';
import MenuIcon from 'material-ui-icons/Menu';
import Drawer from 'material-ui/Drawer';
import {
    Route,
    Switch
} from 'react-router-dom';

import MetaStore from "../ws/MetaStore";
import {getRoot} from "../utils/PathUtils";

const drawerWidth = 240;

const styles = theme => ({
    flex: {
        flex: 1,
    },
    appFrame: {
        position: 'relative',
        display: 'flex',
        width: '100vw',
        height: '100vh',
        overflow: 'hidden'
    },
    appBar: {
        position: 'absolute',
        zIndex: theme.zIndex.drawer + 1
    },
    drawerPaper: {
        position: 'relative',
        marginTop: 56,
        height: 'calc(100% - 56px)',
        width: drawerWidth
    },
    drawerInner: {
        // Make the items inside not wrap when transitioning:
        width: drawerWidth,
    },
    content: {
        backgroundColor: theme.palette.background.default,
        transition: theme.transitions.create('left', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        position: 'absolute',
        top: 56,
        [theme.breakpoints.up('sm')]: {
            top: 64
        },
        left: 0,
        right: 0,
        bottom: 0,
        flex: 1
    },
    contentCancelMargin: {
        marginLeft: 0
    },
    contentShift: {
        transition: theme.transitions.create('left', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
        left: drawerWidth
    },
    contentInner: {
        width: '100%',
        height: '100%'
    },
    hidden: {
        display: 'none'
    }
});


class AppMain extends Component {

    state = {
        drawerOpen: false
    };

    componentDidMount() {
        MetaStore.load(this.props.match.url.substr(1), ()=>null);
    }

    handleDrawerOpen = () => {
        this.setState({drawerOpen: true});
    };

    handleDrawerToggle = () => {
        this.setState(preState => ({
            ...preState,
            drawerOpen: !preState.drawerOpen
        }));
    };

    render() {
        const { match, classes } = this.props;
        const { drawerOpen } = this.state;

        return (
            <div className={classes.appFrame}>
                <AppBar
                    className={classes.appBar}
                >
                    <Toolbar disableGutters>
                        {
                            <IconButton
                                color="inherit"
                                aria-label="open drawer"
                                onClick={this.handleDrawerToggle}
                            >
                                <MenuIcon />
                            </IconButton>
                        }
                        <Typography variant="title" color="inherit" noWrap className={classes.flex}>
                            {getRoot(match.url)}
                        </Typography>
                    </Toolbar>
                </AppBar>
                {
                    <Drawer
                        variant="persistent"
                        classes={{
                            paper: classes.drawerPaper
                        }}
                        anchor='left'
                        open={drawerOpen}
                    >
                        <div className={classes.drawerInner}>
                            {/*<Sidebar onExpand={this.handleDrawerOpen}/>*/}
                        </div>
                    </Drawer>
                }
                <main
                    className={classNames(classes.content, {
                        [classes.contentShift]: drawerOpen
                    })}
                >
                    <div className={classes.contentInner}>
                        <Switch>
                            <Route path={`${match.path}/:perspectiveName`} component={(props)=><div>{props.match.path}</div>} />
                        </Switch>
                    </div>
                </main>
            </div>
        );
    }
}

AppMain.propTypes = {
    classes: PropTypes.object.isRequired,
};

export default withStyles(styles)(AppMain);
