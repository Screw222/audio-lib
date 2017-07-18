import React,{PropTypes} from 'react';
import Audio from './Audio.jsx';
import {Link} from 'react-router';
import Auth from "../auth/Auth";

const AudioList = React.createClass({
   
    getInitialState:function(){
        return {
         displayedAudio:  this.displayedAudio,
        errors:"",
        };
    },
    handleErrors:function(errors){
      this.setState({errors:errors});
    },
    contextTypes: {
        router: PropTypes.object.isRequired,
        isAdmin: React.PropTypes.bool,
  },
    render:function(){
        return (
        <div>
           <div className="errors">
                   <p>{this.state.errors}</p>
               </div>
                {this.context.isAdmin  
                ?( <div className="add-new-audio">
                       <Link to="/audio/add">Add New Audio</Link>
                   </div>) 
                :""}
            <ul className="audio-list">
               { this.props.displayedAudio.map(function(el){
                    return <Audio 
                        errorHandler={this.handleErrors}
                        key={el.id}
                        id={el.id}
                        name={el.name} 
                        author={el.author}
                        audioUrl={el.url}
                        />
                    },this)
                }
           </ul>
       </div>
        );
        
    }
});

export default AudioList;