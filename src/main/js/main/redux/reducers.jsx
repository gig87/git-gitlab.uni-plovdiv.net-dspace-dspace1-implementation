import {  routerReducer } from 'react-router-redux';
import { combineReducers } from 'redux';
import * as types from "./types.jsx";

function communities(state = [], action){
    switch(action.type){
        case types.COMMUNITIES_LIST_CHANGE:
            return action.payload;
        default:
            return state;
    }

    return state;
}

function community(state = {}, action){
    switch(action.type){
        case types.COMMUNITY_CHANGE:
            return action.payload;
        default:
            return state;
    }

    return state;
}



export default combineReducers({
    communities: communities,
    community: community,
    routing: routerReducer
});