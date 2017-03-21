import React from "react";

export default class Header extends React.Component {
    render(){

        return (
            <img style={{width: 200, marginLeft: 40 }} src={ window.BASE_URL + "assets/delc-logo.png"} />
        )
    }
};