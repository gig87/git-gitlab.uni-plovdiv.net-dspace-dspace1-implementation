import React from 'react';
import ReactDOM from 'react-dom';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import { indigo500, brown500, indigo700, redA200 } from 'material-ui/styles/colors';

import { createStore, combineReducers } from 'redux';
import { Provider } from 'react-redux';
import { syncHistoryWithStore, routerReducer } from 'react-router-redux';

import { Router, Route, hashHistory } from 'react-router';

import Login from "./main/login/login.jsx"
import Communities from "./main/dspace/communities/communities.jsx";
import Community from "./main/dspace/communities/community.jsx";
import Layout from './main/layout/layout.jsx';

import reducers from "./main/redux/reducers.jsx";

const store = createStore(reducers);

const muiTheme = getMuiTheme({
    palette: {
        primary1Color: indigo500,
        primary2Color: indigo500,
        accent1Color: brown500,
        pickerHeaderColor: indigo500
    }
});
const va = 1;
const history = syncHistoryWithStore(hashHistory, store);
history.listen(location => console.log(location));

const Main = () => (
    <Provider store={ store }>
        <Router  history={ history }>
            <Route component={ Layout }>
                <Route path="/" component={ Communities } />
                <Route path="communities" component={ Communities } />
                <Route path="communities/(:idCommunity)" component={ Community } />
            </Route>
        </Router>
    </Provider>
);



const App = () => (
    <MuiThemeProvider muiTheme={muiTheme}>
        <Main />
    </MuiThemeProvider>
);

ReactDOM.render( <App />, document.getElementById('app') );