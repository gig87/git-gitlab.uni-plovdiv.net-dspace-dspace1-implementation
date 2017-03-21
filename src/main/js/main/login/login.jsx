import React from 'react';
import TextField from 'material-ui/TextField';
import { Grid, Row, Col } from 'react-bootstrap';

import RaisedButton from 'material-ui/RaisedButton';
import Paper from 'material-ui/Paper';

import axios from 'axios';
import * as actions from "../redux/actions.jsx";
import { Card, CardActions, CardHeader, CardText } from 'material-ui/Card';
import injectTapEventPlugin from 'react-tap-event-plugin';
import Snackbar from 'material-ui/Snackbar';

const stylePaper = {
    marginTop: 300,
    margin: 20,
    textAlign: 'center',
    display: 'inline-block'
};

const style = {
    paddingLeft: 30,
    height: 60
};

class Login extends React.Component {
    constructor(props){
        super(props);
        injectTapEventPlugin();
        this.state = {};

        this.state.snackbar = {};

        this.state.snackbar.open = false;
        this.state.snackbar.msg = "";

        this.state.errors = {};

        this.state.errors.username = "";
        this.state.errors.password = "";

        this.state.user = { };
    }
    login(){
        let user = this.state.user;
        if(user.username && user.password){
            axios.post(window.BASE_URL + "User/login", { user }).then(res => {
                if(res.data.result){
                    window.location = window.BASE_URL + "layout/index/";
                }else{
                    let snackbar = this.state.snackbar;
                    snackbar.open = true;

                    snackbar.msg = res.data.exception;
                    this.setState({ snackbar });
                }
            });
        } else {
            let errors = this.state.errors;

            if (!user.username) {
                errors.username = "Username is required!";
            }

            if (!user.password) {
                errors.password = "Password is required!";
            }

            this.setState({errors});
        }

    }
    onUserChange(e, type){
        let user = this.state.user;
        let errors = this.state.errors;
        let snackbar = this.state.snackbar;

        let value = e.target.value;

        user[type] = value;

        snackbar.open = false;
        snackbar.msg = "";

        if (value) {
            errors[type] = "";
        }

        this.setState({user, snackbar, errors });
    }
    render(){
        return (
            <Grid>
                <Row style={{marginTop: 90 }}>

                    <Col sm={6} lg={6} smOffset={3} lgOffset={3}>
                        <Paper style= {stylePaper} zDepth={5}>
                            <Card>
                                <CardHeader style={{padding: 15, backgroundColor: "#2f506c", color: "#fff"}}><h2>DSpace Login</h2></CardHeader>
                                <CardText expandable={false}>

                                    <TextField
                                        floatingLabelText="Username"
                                        fullWidth ={ true }
                                        type="text"
                                        value ={ this.state.user.username }
                                        onChange= {e => this.onUserChange.call(this, e, "username") }
                                        errorText ={ this.state.errors.username }
                                    />
                                    <TextField
                                        floatingLabelText="Password"
                                        fullWidth ={ true }
                                        type="password"
                                        value ={ this.state.user.password }
                                        onChange= {e => this.onUserChange.call(this, e, "password") }
                                        errorText ={ this.state.errors.password }
                                    />
                                </CardText>
                                <CardActions>
                                    <RaisedButton primary={true}  label="Login"  onTouchTap={this.login.bind(this)}/>
                                </CardActions>
                            </Card>
                        </Paper>
                    </Col>
                </Row>
                <Snackbar
                    open={ this.state.snackbar.open }
                    message={ this.state.snackbar.msg }
                    autoHideDuration={4000}
                />
            </Grid>
        )
    }
}


export default Login;