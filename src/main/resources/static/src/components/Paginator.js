'use strict';

Object.defineProperty(exports, '__esModule', {
    value: true
});

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { 'default': obj }; }

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var Paginator = _react2['default'].createClass({
    displayName: 'Paginator',

    getInitialState: function getInitialState() {
        return {
            currentPage: this.props.currentPage,
            totalPages: this.props.totalPages,
            last: this.props.last,
            first: this.props.first,
            inputPage: this.props.currentPage
        };
    },
    getInitialState: function getInitialState() {
        return {
            currentPage: 1,
            displayedAudio: [],
            inputPage: 1
        };
    },
    handlePageCorrectInput: function handlePageCorrectInput(event) {
        var value = parseInt(event.target.value);
        if (value > this.props.totalPages) {
            event.target.value = this.props.totalPages;
        }
        if (value < 1) {
            event.target.value = 1;
        }
        event.target.value = value;
    },
    handlePageChange: function handlePageChange(event) {
        event.preventDefault();
        var pageId = event.target.id;
        this.props.handlePageChanged(pageId);
    },
    handleFirstPage: function handleFirstPage(event) {
        this.props.handlePageChanged(1);
    },
    handleLastPage: function handleLastPage(event) {
        this.props.handlePageChanged(this.props.totalPages);
    },
    handlePrevPage: function handlePrevPage(event) {
        event.preventDefault();
        if (this.props.currentPage - 1 > 0) {
            this.props.handlePageChanged(this.props.currentPage - 1);
        }
    },
    handleNextPage: function handleNextPage(event) {
        event.preventDefault();
        if (this.props.currentPage + 1 <= this.props.totalPages) {
            this.props.handlePageChanged(this.props.currentPage + 1);
        }
    },
    render: function render() {
        var pages = [],
            i = 1;
        for (; i < this.props.totalPages + 1; i++) {
            if (i == this.props.currentPage) {
                pages.push(_react2['default'].createElement(
                    'a',
                    { href: '#', key: i, id: i, className: 'active', onClick: this.handlePageChange },
                    i
                ));
            } else {
                pages.push(_react2['default'].createElement(
                    'a',
                    { href: '#', key: i, id: i, onClick: this.handlePageChange },
                    i
                ));
            }
        }
        return _react2['default'].createElement(
            'div',
            { className: 'pagination' },
            pages,
            _react2['default'].createElement(
                'div',
                { className: 'page-manage' },
                _react2['default'].createElement(
                    'a',
                    { href: true, key: 'prev', className: 'prev', onClick: this.handlePrevPage },
                    'prev'
                ),
                _react2['default'].createElement(
                    'a',
                    { href: '#', key: 'next', className: 'next', onClick: this.handleNextPage },
                    'next'
                )
            )
        );
    }
});
/*
render:function(){
        var rows = [], i=2;
        for(;i<this.state.totalPages;i++){
            if(i==this.state.currentPage){
                rows.push(<li key={i} className="active" onClick={this.handleFirstPage}>{i}</li>);
            }else{
                rows.push(<li key={i} onClick={this.handleFirstPage}>{i}</li>);
            }
        }
        return (
            <div className="pagination">
              <ul>
                  <li key="first" onClick={this.handleFirstPage}><a>1</a></li>       
                  <li key="last"
                      onClick={this.handleLastPage} >
                      {this.props.totalPages>0?this.props.totalPages:1}
                  </li> 
                  <li key="prev" onClick={this.handlePrevPage}><a>prev</a></li>
                  <li key="next"  onClick={this.handleNextPage}><a>pnext</a></li>
                </ul>
            </div>
        );
    }*/

exports['default'] = Paginator;
module.exports = exports['default'];