import React, { PropTypes } from 'react';
import { Link } from 'react-router'
import $ from "jquery";
import Modal from './Modal.jsx';
import Auth from "../auth/Auth";

const Audio = React.createClass({
    getInitialState:function(){
        return { isOpen: false};
    },
    getDefaultUrl:function(){
      return "/api/audio/";
  },
    handleDeleteAudio: function(event) {   
      var xhr = new XMLHttpRequest();
      var body =  JSON.stringify(this.state);
        xhr.open("DELETE", this.getDefaultUrl()+this.props.id, true);
        xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
        xhr.setRequestHeader("X-Auth-Token", Auth.getToken());
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        console.log(xhr);
        xhr.addEventListener('load', () => {
            if (xhr.status === 200 ||xhr.status === 201) {
                this.props.errorHandler("");
                this.context.router.replace('/app');
            } else {
                var resp = xhr.response;
                if(resp==null || resp==undefined || resp==""){
                   this.props.errorHandler("Failed to delete audio.");
                }else{  
                    var errorResponse = JSON.parse(xhr.response);
                    var error =  errorResponse.error + ": " + errorResponse.message;
                    this.props.errorHandler("Failed to delete audio. " + error);
                   
                }  
            }
        });
        xhr.send(body);  
  },

     contextTypes: {
        isAdmin: React.PropTypes.bool,
        router: PropTypes.object.isRequired
    },
toggleModal:function(event){
    var name = event.target.name;
    event.preventDefault();
    if(name=="modal-yes"){
        this.handleDeleteAudio();
    }
    
    this.setState({
      isOpen: !this.state.isOpen
    });
  },
    render:function(){
        return (
            <li className="audio-node">
               
                <div className="audio-info">
                    <div className="audio-author"> {this.props.author} </div>
                    <div className="audio-name"> {this.props.name} </div>
                    <div className="audio-player">
                        <audio controls="controls">
                          <source src={this.props.audioUrl} type="audio/ogg" />
                        </audio>
                    </div>
                </div>
                {this.context.isAdmin  
                ?(  <div className="audio-manage">
                        <Link to={"/audio/preview/" + this.props.id} > DETAILS </Link> 
                        <Link to={"/audio/edit/" + this.props.id} >EDIT</Link>               
                        <a href="#" onClick={this.toggleModal} > DELETE </a> 
                    </div>)
                
                :(  <div className="audio-manage">
                        <Link to={"/audio/preview/" + this.props.id} > DETAILS </Link> 
                    </div>)}
                    
                 <Modal 
                    closeHanlder
                    show={this.state.isOpen}
                    onClose={this.toggleModal}>
                        <div className="modal-content">Are you sure?</div>
                        <a href={"#" + this.props.id} className="button-big"name="modal-yes" onClick={this.toggleModal} >YES </a> 
                        <a href={"#" + this.props.id} className="button-big" name="modal-no" onClick={this.toggleModal} >NO</a> 
                </Modal>
            </li>   
        );
    }
});
//                    <a href={"/" + this.props.id} > DETAILS </a> 

export default Audio;