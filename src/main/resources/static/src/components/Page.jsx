import React from 'react';
import $ from "jquery";

import Paginator from './Paginator.jsx';
import AudioList from './AudioList.jsx';

import Auth from '../auth/Auth';

var Page = React.createClass({
    defaultUrl:function(){
        var buildUrl = this.props.url;
        if(this.props.size == null
           || this.props.size == undefined
           || parseInt(this.props.size) <= 0){
            if(this.props.searchParams == null 
               || this.props.searchParams == undefined
               || this.props.searchParams == "")
            {
                buildUrl=buildUrl + "page=";
            }
            else{
               buildUrl=buildUrl + this.props.searchParams;
               buildUrl=buildUrl + "&page=";
            }  
        }
        else{
             buildUrl=buildUrl + "size="+this.props.size;
             if(this.props.searchParams != null 
               && this.props.searchParams != undefined
               && this.props.searchParams != "")
            {
             buildUrl=buildUrl + "&" + this.props.searchParams;
            }
             buildUrl=buildUrl + "&page=";    
        }
        return buildUrl;
    },
    handlePageChanged:function(pageId){
        this.state = {
            url: this.defaultUrl()+pageId
        };
        this.apiRequest();
    },
    handlePageInput:function(event){
        var pageNum = event.target.previousSibling.value;
        this.state = {
            url: this.defaultUrl()+pageNum
        };
        this.apiRequest();
    },
    apiRequest:function(){
    $.ajax({
      url: this.state.url,
    beforeSend: function(request) {
        request.setRequestHeader("X-Auth-Token", Auth.getToken());
      },
      cache: true,
        dataType: 'json',
        success: function(data) {
            //if (this.isMounted()) {
                this.setState({
                    displayedAudio: data["content"],
                    currentPage: data["number"],
                    totalPages: data["totalPages"],
                    last: data["last"],
                    first: data["first"]
                });
          /*  }else{
                this.state.displayedAudio = data["content"];
                this.state.currentPage = data["number"];
                this.state.totalPages = data["totalPages"];
                this.state.last = data["last"];
                this.state.first = data["first"];
            }*/
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
         url: this.defaultUrl()+"1",
         currentPage: 1,
         displayedAudio:[],
     };
    },
    render:function(){
        return(
        <div>
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

export default Page;