import React ,{PropTypes} from 'react';
import Auth from '../auth/Auth' 
import Router from 'react-router'

const Login = React.createClass({
    getInitialState:function() {
        return {
            login: "",
            password: "",
            errors: ""
        };
    },
    handleInput:function(event){
        var value = event.target.value;
        var name = event.target.name;
        this.setState({
            [name]: value
        });
    },
    handleSubmit:function(event){
        var xhr = new XMLHttpRequest();
        var body = '{"username":"'+this.state.login+'","password":"'+this.state.password+'"}';
        xhr.open("POST", '/api/login', true);
        xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
        xhr.setRequestHeader('Content-Type', 'application/json; charset=utf-8');
        console.log(xhr);
        xhr.addEventListener('load', () => {
            if (xhr.status === 200) {
                this.setState({
                    errors: ""
                });
                Auth.authenticateUser(xhr.getResponseHeader("X-Auth-Token"));
                this.context.router.push('/app');
                window.location.reload();
            } else {
                var errorResponse = JSON.parse(xhr.response);
                var error =  errorResponse.error + ": " + errorResponse.message;
                this.setState({
                    errors : error
                });
            }
        });
        xhr.send(body);  
    },
    render:function(){    
        return(
            <div className="auth-form">
              <div className="errors">
                 {this.state.errors}
              </div>
                <label htmlFor="login">Login: </label>
                <input type="text" name="login" 
                    value={this.state.login} 
                    onChange={this.handleInput}/>
                <label htmlFor="password">Password: </label>
                <input type="password" name="password"
                   value={this.state.password}
                    onChange={this.handleInput} />
                <input type="submit" value="Enter" 
                    onClick={this.handleSubmit} />
                
            </div>
        );
    },
                                
});
Login.contextTypes = {
  router: PropTypes.object.isRequired
};
export default Login;