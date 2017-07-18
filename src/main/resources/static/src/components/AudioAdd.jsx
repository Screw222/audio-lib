import React ,{ PropTypes } from 'react';
import $ from "jquery";
import Auth from "../auth/Auth"

var AudioAdd = React.createClass({
  getDefaultUrl:function(){
      return "/api/audio/";
  },
  contextTypes: {
    router: PropTypes.object.isRequired
  },
  handleAddAudio: function(event) {   
      var xhr = new XMLHttpRequest();
      var audioNew = {
            name: this.state.name,
            author: this.state.author,
            album: this.state.album,
            year: this.state.year,
            url: this.state.url
      };
     var body =  JSON.stringify(audioNew);
      
        xhr.open("POST", this.getDefaultUrl(), true);
        xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
        xhr.setRequestHeader("X-Auth-Token", Auth.getToken());
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        console.log(xhr);
        xhr.addEventListener('load', () => {
            if (xhr.status === 200 ||xhr.status === 201) {
                //this.context.router.replace('/app');
                this.setState({sucess:"Audio Node was successfully add",
                               errors:""
                              });
            } else {
                var errors = xhr.response.errors ? xhr.response.errors : {};
                var errorResponse = JSON.parse(xhr.response);
                var error =  errorResponse.error + ": " + errorResponse.message;
                this.setState({sucess:"",
                               errors:error
                              });
            }
        });
        xhr.send(body);  
  },
  getInitialState: function() {
    return {
        name: "",
        author: "",
        album: "",
        year: "",
        url:"",
        success:"",
        errors:""
    };
  },

handleInputChange:function(event){
    var name = event.target.name;
    var value = event.target.value;
    this.setState({
        [name]: value
    });
},
  render: function() {      
    return (
        <div className="audio-content">
            <div className="sucess">
                <p>{this.state.sucess}</p>
            </div>
            <div className="errors">
                <p> {this.state.errors}</p>
            </div>
             <div className="audio-content-name">
                 <label htmlFor="name">Name: </label>
                  <input type="text" name="name" value={this.state.name} onChange={this.handleInputChange}    />
            </div>
             <div className="audio-content-author">
                 <label htmlFor="author">Author: </label>
                 <input type="text" name="author" value={this.state.author} onChange={this.handleInputChange}  />
            </div>
             <div className="audio-content-album">
                 <label htmlFor="album">Album: </label>
                 <input type="text" name="album" value={this.state.album} onChange={this.handleInputChange}  />
            </div>
             <div className="audio-content-year">
                 <label htmlFor="year">Year: </label>
                   <input type="text" name="year" value={this.state.year} onChange={this.handleInputChange} pattern="^[ 0-9]+$"  />
                   <div className="requirements">Field Year must be numeric</div>
            </div>
              <div className="audio-content-url">
                 <label htmlFor="url">URL: </label>
                   <input type="text" name="url" value={this.state.url} onChange={this.handleInputChange}  />
            </div>
            <div className="audio-submit">
                <input type="submit" onClick={this.handleAddAudio} ></input>
            </div>
        </div>
    );
  }
});


export default AudioAdd;