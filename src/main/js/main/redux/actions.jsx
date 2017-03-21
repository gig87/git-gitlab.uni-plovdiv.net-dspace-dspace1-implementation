import * as types from "./types.jsx";

export function changeCommunities(list){
    return{
        type: types.COMMUNITIES_LIST_CHANGE,
        payload: list
    }
}

export function changeCommunity(community){
    return{
        type: types.COMMUNITY_CHANGE,
        payload: community
    }
}