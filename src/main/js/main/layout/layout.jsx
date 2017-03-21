import React from 'react';

import injectTapEventPlugin from 'react-tap-event-plugin';
export default class Layout extends React.Component{
    constructor(props){
        super(props);
        injectTapEventPlugin();
    }
    render(){
        return (
            <div>
                { this.props.children }
            </div>

        )
    }
}