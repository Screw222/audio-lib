'use strict';

Object.defineProperty(exports, '__esModule', {
    value: true
});

function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { 'default': obj }; }

var _react = require('react');

var _react2 = _interopRequireDefault(_react);

var _reactRouter = require('react-router');

var AudioContainer = _react2['default'].createClass({
    displayName: 'AudioContainer',

    render: function render() {
        return _react2['default'].createElement(
            'div',
            null,
            this.props.children
        );
    }
});

exports['default'] = AudioContainer;
module.exports = exports['default'];