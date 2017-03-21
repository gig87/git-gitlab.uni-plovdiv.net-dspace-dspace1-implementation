import React from 'react';
import ReactDOM from 'react-dom';

import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import getMuiTheme from 'material-ui/styles/getMuiTheme';
import { indigo500, brown500, indigo700, redA200 } from 'material-ui/styles/colors';

import Login from './main/login/login.jsx';
const muiTheme = getMuiTheme({
    palette: {
        primary1Color: indigo500,
        primary2Color: indigo500,
        accent1Color: brown500,
        pickerHeaderColor: indigo500
    }
});

const stylePaper = {
    marginTop: 300,
    margin: 20,
    textAlign: 'center',
    display: 'inline-block'
};

const style = {
    paddingLeft: 25,
    height: 60
};



const LoginMain = () => (
    <MuiThemeProvider muiTheme={muiTheme}>
        <Login />
    </MuiThemeProvider>
);

ReactDOM.render( <LoginMain />, document.getElementById('login') );