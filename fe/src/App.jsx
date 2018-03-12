import * as React from 'react';
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';
import blue from 'material-ui/colors/blue';
import amber from 'material-ui/colors/amber';
import {Fragment} from "react";
import {
    BrowserRouter as Router,
    Route
} from 'react-router-dom'
import { Provider } from 'react-redux';

import AppMain from "./widgets/app/AppMain";
import NoAppName from "./widgets/app/NoAppName";
import MetaStore from './MetaStore';

const theme = createMuiTheme({
    palette: {
        primary: blue,
        secondary: amber
    }
});

class App extends React.Component {
  render() {
    return (
        <div className="App">
            <MuiThemeProvider theme={theme}>
                <Provider store={MetaStore.reduxStore}>
                    <Router>
                        <Fragment>
                            <Route exact path='/' component={NoAppName} />
                            <Route path='/:appName' component={AppMain} />
                        </Fragment>
                    </Router>
                </Provider>
            </MuiThemeProvider>
        </div>
    );
  }
}

export default App;
