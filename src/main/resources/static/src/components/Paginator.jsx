import React from 'react';

var Paginator = React.createClass({
    getInitialState:function(){
        return {
         currentPage: this.props.currentPage,
         totalPages:  this.props.totalPages,
         last:  this.props.last,
         first:  this.props.first,
         inputPage: this.props.currentPage,   
        };
    },
    getInitialState:function(){
     return {
         currentPage: 1,
         displayedAudio:[],
         inputPage: 1
     };
    },
    handlePageCorrectInput:function(event){
      var value = parseInt(event.target.value);
        if(value>this.props.totalPages){
            event.target.value = this.props.totalPages;
        }
         if(value<1){
            event.target.value = 1;
        }
        event.target.value = value; 
    },
    handlePageChange:function(event){
        event.preventDefault();
        var pageId = event.target.id
        this.props.handlePageChanged(pageId);  
    },
    handleFirstPage:function(event){
            this.props.handlePageChanged(1);  
    },
    handleLastPage:function(event){
            this.props.handlePageChanged(this.props.totalPages);
    },
    handlePrevPage:function(event){
        event.preventDefault();
        if(this.props.currentPage-1 > 0){
            this.props.handlePageChanged(this.props.currentPage-1);
        }
    },
    handleNextPage:function(event){
        event.preventDefault();
        if(this.props.currentPage+1 <=  this.props.totalPages){
            this.props.handlePageChanged(this.props.currentPage+1);
        }
    },
    render:function(){
        var pages = [], i=1;
        for(;i<this.props.totalPages+1;i++){
            if(i==this.props.currentPage){
                pages.push(<a href='#' key={i} id={i} className="active" onClick={this.handlePageChange}>{i}</a>);
            }else{
                pages.push(<a href='#'  key={i} id={i} onClick={this.handlePageChange}>{i}</a>);
            }
        }
        return (
            <div className="pagination">  
                  {pages}
                  <div className="page-manage">
                        <a href key="prev"  className="prev" onClick={this.handlePrevPage}>prev</a>
                      <a href='#' key="next"  className="next" onClick={this.handleNextPage}>next</a>
                 </div>

            </div>
        );
    }
});

export default Paginator;