import React from 'react'

const AudioSearch = React.createClass({
    getInitialState:function(){
        return{
         name: "",
         album: "",
         author: "",
         year: ""
        }
    },
    handleInputChange:function(event){
        var value = event.target.value;
        var name = event.target.name;
        this.setState({
            [name] : value
        });
    },
    handleClear:function(event){
        this.setState({
        name: "",
         album: "",
         author: "",
         year: ""
        });
    },
    
    handleSearchSubmit:function(event){
        var propArray = ["","","",""];
        if(this.state.name != ""){
            propArray[0] = "name="+this.state.name;
        }
        if(this.state.album != ""){
            propArray[1] = "album="+this.state.album;
        }
        if(this.state.author != ""){
            propArray[2] = "author="+this.state.author;
        }
        if(this.state.year != ""){
            propArray[3] = "year="+this.state.year;
        }
        var propString = "";
        for(let i=0;i<propArray.length;i++){
            if(propArray[i]!=""){
                if(propString==""){
                    propString = propString + propArray[i];
                }else{
                    propString = propString + "&" + propArray[i];
                }
            }
        }
        this.props.handleSearch(propString);     
    },
    render:function(){
        return(

            <div className="search-components">
               <input type="checkbox" id="spoiler" /> 
               <label htmlFor="spoiler">Search...</label>
               <div className="search-components-spoiler">
               <div className="search-name">
                    <label htmlFor="name">Name: </label>
                    <input name="name" type="text" value={this.state.name} onChange={this.handleInputChange}></input>
                </div>
                <div className="search-author">
                    <label htmlFor="author">Author: </label>
                    <input name="author" type="text" value={this.state.author} onChange={this.handleInputChange}></input>
                </div>
                <div className="search-album">
                    <label htmlFor="album">Album: </label>
                    <input name="album" type="text" value={this.state.album} onChange={this.handleInputChange}></input>
                </div>
                <div className="search-year">
                    <label htmlFor="year">Year: </label>
                    <input 
                        name="year"
                        type="text" 
                        pattern="^[ 0-9]+$"
                        value={this.state.year} 
                        onChange={this.handleInputChange} >
                    </input>
                    <div className="requirements">Field Year must be numeric</div>
                </div>
                <div>
                   <input name="find" value="Find" type="button" onClick={this.handleSearchSubmit}></input>
                   <input name="clear" value="Clear" type="button" onClick={this.handleClear}></input>
                </div>
            </div>
        </div>
        );
    }
    
});

export default AudioSearch;