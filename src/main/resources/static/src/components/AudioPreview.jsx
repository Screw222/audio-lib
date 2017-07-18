import React from 'react';
import $ from "jquery";
import Auth from '../auth/Auth';

var AudioPreview = React.createClass({
  getDefaultUrl:function(){
      return "/api/audio/";
  },
  loadAudio: function() {
    $.ajax({
      url: this.getDefaultUrl()+this.props.params.audioId,
      beforeSend: function(request) {
        request.setRequestHeader("X-Auth-Token", Auth.getToken());
     },
      dataType: 'json',
      cache: true,
      success: function(data) {
        this.setState({ 
            id: data["id"],
            name: data["name"],
            author: data["author"],
            album: data["album"],
            year: data["year"],
            url: data["url"]});
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this)
    });
  },
  getInitialState: function() {
    return {
        id: "",
        name: "",
        author: "",
        album: "",
        year: "",
        url:""
    };
  },
     handleInput:function(event){
        var value = event.target.value;
        var name = event.target.name;
        this.setState({
            [name]: value
        });
    },
  componentDidMount: function() {
    this.loadAudio();
  },
  render: function() {      
    return (
        <div className="audio-content">
             <div className="audio-content-name">
                 <label htmlFor="name">Name: </label>
                    <input type="text" name="name" value={this.state.name} onChange={this.handleInputChange}  readOnly />
            </div>
             <div className="audio-content-author">
                 <label htmlFor="author">Author: </label>
                  <input type="text" name="author" value={this.state.author} onChange={this.handleInputChange} readOnly />
            </div>
             <div className="audio-content-album">
                 <label htmlFor="album">Album: </label>
                 <input type="text" name="album" value={this.state.album} onChange={this.handleInputChange}  readOnly />
                
            </div>
             <div className="audio-content-year">
                 <label htmlFor="year">Year: </label>
                   <input type="text" name="year" value={this.state.year} onChange={this.handleInputChange} readOnly />
            </div>
             <div className="audio-content-url">
                < audio controls="controls">
                    <source src={this.state.url} type="audio/ogg" />
                </     audio>
            </div>
        </div>
    );
  }
});


export default AudioPreview;