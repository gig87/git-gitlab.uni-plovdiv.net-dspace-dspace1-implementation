import React from "react";
import { Grid, Row, Col } from 'react-bootstrap';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actions from "../../redux/actions.jsx";
import { find, isEmpty } from "underscore";
import axios from "axios";

import TextField from 'material-ui/TextField';

import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';

class Community extends React.Component{
    constructor(props){
        super(props);

        axios.get(window.BASE_URL + `Community/edit?id=${props.params.idCommunity}`).then(res=> {
            if(res.data.success){
                this.props.changeCommunity(res.data.result);
            }
        });
    }

    getRows(){
        if(!isEmpty(this.props.community)){
            return this.props.community.collections.map(colleciton =>{
                return (
                    <TableRow key={colleciton.id}>
                        <TableRowColumn>{colleciton.id}</TableRowColumn>
                        <TableRowColumn>
                            <Link to={`/communities/${this.props.community.id}/${colleciton.id}`} activeClassName="active">{colleciton.name}</Link>
                        </TableRowColumn>
                        <TableRowColumn>{colleciton.shortDescription}</TableRowColumn>
                    </TableRow>
                )
            });
        }

    }


    render(){
        return(
            <Grid>
                <Row>
                    <Col lg={12} sm={12}>
                        <TextField
                            floatingLabelText="Name"
                            fullWidth ={ true }
                            type="text"
                            value ={ this.props.community.name }
                        />
                        <TextField
                            floatingLabelText="Short Description"
                            fullWidth ={ true }
                            value ={ this.props.community.shortDescription }
                        />

                    </Col>
                </Row>
                <Row>
                    <h2>Collections</h2>
                    <Table>
                        <TableHeader>
                            <TableRow>
                                <TableHeaderColumn>ID</TableHeaderColumn>
                                <TableHeaderColumn>Name</TableHeaderColumn>
                                <TableHeaderColumn>Description</TableHeaderColumn>
                            </TableRow>
                        </TableHeader>
                        <TableBody>
                                { this.getRows() }
                        </TableBody>
                    </Table>
                </Row>
            </Grid>
        )
    }
}


const mapStateToProps = state => {
    return {
        community: state.community
    }
};

const mapsDispachToProps = dispatch =>{
    return bindActionCreators({
        changeCommunity: actions.changeCommunity
    }, dispatch);
};


export default connect(mapStateToProps, mapsDispachToProps)(Community);