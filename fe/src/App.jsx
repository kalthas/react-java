import * as React from 'react';
import { MuiThemeProvider, createMuiTheme } from 'material-ui/styles';
import blue from 'material-ui/colors/blue';
import amber from 'material-ui/colors/amber';
import {
    BrowserRouter as Router,
    Route
} from 'react-router-dom'

import AppMain from "./widgets/AppMain";
import NoAppName from "./widgets/NoAppName";
import {Fragment} from "react";

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
                <Router>
                    <Fragment>
                        <Route exact path='/' component={NoAppName} />
                        <Route path='/:appName' component={AppMain} />
                    </Fragment>
                </Router>
            </MuiThemeProvider>
        </div>
    );
  }
}

export default App;
