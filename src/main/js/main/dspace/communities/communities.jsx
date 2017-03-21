import React from "react";
import axios from 'axios';
import { connect } from "react-redux";
import { bindActionCreators } from "redux";
import * as actions from "../../redux/actions.jsx";
import { Grid, Row, Col } from 'react-bootstrap';
import { Link } from 'react-router';
import RaisedButton from 'material-ui/RaisedButton';

import {Table, TableBody, TableHeader, TableHeaderColumn, TableRow, TableRowColumn} from 'material-ui/Table';

class Communities extends React.Component {

    componentWillMount(){

        axios.get(window.BASE_URL + "Community/list").then(res =>{
            let response = res.data;

            if(response.success){
                this.props.changeCommunities(response.result);
            }
        })
    }

    getRow(){
        return this.props.communities.map(community =>{
            return (
                <TableRow key={community.id}>
                    <TableRowColumn>{community.id}</TableRowColumn>
                    <TableRowColumn><Link to={`/communities/${community.id}`} activeClassName="active">{community.name}</Link></TableRowColumn>
                    <TableRowColumn>{community.shortDescription}</TableRowColumn>
                </TableRow>
            )
        });
    }
    addCommunity(){

    }

    render(){
        return(
            <Grid>
                <Row>
                    <Col>
                        <RaisedButton primary={true}  label="Add new community"  onTouchTap={this.addCommunity.bind(this)}/>
                    </Col>
                </Row>
                <Row style={{ paddingTop: 15}}>
                    <Col sm={12} lg={12} >
                        <Table>
                            <TableHeader>
                                <TableRow>
                                    <TableHeaderColumn>ID</TableHeaderColumn>
                                    <TableHeaderColumn>Name</TableHeaderColumn>
                                    <TableHeaderColumn>Description</TableHeaderColumn>
                                </TableRow>
                            </TableHeader>
                            <TableBody>
                                    { this.getRow() }
                            </TableBody>
                        </Table>
                    </Col>

                </Row>
            </Grid>
        )
    }
}

const mapStateToProps = state => {
    return {
        communities: state.communities
    }
};

const mapsDispachToProps = dispatch =>{
  return bindActionCreators({
      changeCommunities: actions.changeCommunities
  }, dispatch);
};


export default connect(mapStateToProps, mapsDispachToProps)(Communities);