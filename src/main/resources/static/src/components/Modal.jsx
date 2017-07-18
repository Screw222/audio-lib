import React from 'react';
import $ from "jquery";

class Modal extends React.Component {
  render() {
    // Render nothing if the "show" prop is false
    if(!this.props.show) {
      return null;
    }
    return (
      <div className="modal-backdrop" onClick={this.props.onClose}>
        <div className="modal-window" >
          {this.props.children}
          <div className="modal-footer">
          </div>
        </div>
      </div>
    );
  }
}

Modal.propTypes = {
  onClose: React.PropTypes.func.isRequired,
  show: React.PropTypes.bool,
  children: React.PropTypes.node
};

export default Modal;