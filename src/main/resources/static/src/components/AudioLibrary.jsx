import React from 'react';
import $ from "jquery";

import Paginator from './Paginator.jsx';
import AudioList from './AudioList.jsx';
import AudioSearch from './AudioSearch.jsx';

import Auth from '../auth/Auth';

var AudioLibrary = React.createClass({
    defaultUrl:function(){
        var buildUrl = this.props.url;
        if(this.props.size == null
           || parseInt(this.props.size) <= 0){
            if(this.state.searchParams == null 
               || this.state.searchParams == "")
            {
                buildUrl=buildUrl + "page=";
            }
            else{
               buildUrl=buildUrl + this.state.searchParams;
               buildUrl=buildUrl + "&page=";
            }  
        }
        else{
             buildUrl=buildUrl + "size="+this.props.size;
             if(this.state.searchParams != null 
               && this.state.searchParams != "")
            {
             buildUrl=buildUrl + "&" + this.state.searchParams;
            }
             buildUrl=buildUrl + "&page=";    
        }
        return buildUrl;
    },
    handlePageChanged:function(pageId){
        this.state.url= this.defaultUrl()+pageId;
        this.apiRequest();
    },
    handlePageInput:function(event){
        var pageNum = event.target.previousSibling.value;
        this.state.url=this.defaultUrl()+pageNum;
        this.apiRequest();
    },
    apiRequest:function(){
    $.ajax({
      url: this.state.url,
      cache: true,
        dataType: 'json',
        beforeSend: function(request) {
            request.setRequestHeader("X-Auth-Token", Auth.getToken());
        },
        success: function(data) {
                this.setState({
                    displayedAudio: data["content"],
                    currentPage: data["number"],
                    totalPages: data["totalPages"],
                    last: data["last"],
                    first: data["first"]
                });
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.state.url, status, err.toString());
      }.bind(this)
    });
    }, 
    componentDidMount: function() {
        this.apiRequest();
    },
    getInitialState:function(){
     return {
         url: this.props.url+"size="+this.props.size,
         currentPage: 1,
         displayedAudio:[],
         searchParams:""
     };
    },
    handleSearch:function(str){
         this.state.searchParams =str;
         this.state.url= this.defaultUrl()+"1";
        this.apiRequest();
    },
    render:function(){
        return(
        <div>
           <AudioSearch
             handleSearch = {this.handleSearch} />
            <Paginator
                currentPage={this.state.currentPage}
                totalPages={this.state.totalPages}
                last={this.state.last}
                first={this.state.first}
                handlePageChanged={this.handlePageChanged}
                handlePageInput={this.handlePageInput}
             />
             <AudioList
                 displayedAudio={this.state.displayedAudio}
              />
        </div>
        )
    }
});

export default AudioLibrary;