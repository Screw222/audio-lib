import ReactDOM from 'react-dom';
import React from 'react';

import AudioLibrary from './AudioLibrary.jsx';


const AudioLibContainer= React.createClass({
    getInitialState:function(){
        return {
            url: "/api/audio?",
            size: "5",
            searchParams: ""
        };
    },

    render:function(){
         return(
             <div className="content"> 

             <AudioLibrary 
                url={this.state.url}
                size={this.state.size}
            />
            </div>
             );
    }
    
});

export default AudioLibContainer;