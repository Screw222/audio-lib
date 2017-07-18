import React , { PropTypes }from 'react'
import { Link,IndexLink  } from 'react-router';
import Auth from '../auth/Auth';

const AppContainer = React.createClass({
    getInitialState:function(){
        return {
        isAuth: false,
        currentUser: "",
        isAdmin: false
        }
    },
   childContextTypes : {
        isAdmin: React.PropTypes.bool
    },
    getChildContext() {
        return {
            isAdmin: this.state.isAdmin,
        };
    },
    /**
  * Get current user if he is auth
  */
    getCurrentUser:function(){
      if (Auth.isUserAuthenticated){
           var xhr = new XMLHttpRequest();
           xhr.open("GET", '/api/users/current', true);
           xhr.setRequestHeader("X-Requested-With", "XMLHttpRequest");
           xhr.setRequestHeader("X-Auth-Token", Auth.getToken());
           xhr.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
           xhr.addEventListener('load', () => {
            if (xhr.status === 200) {
                if(xhr.response!=null || xhr.response!=undefined){
                   var userResponse = JSON.parse(xhr.response.toString());
                    if(userResponse.username!=null || userResponse.username!=undefined){
                        this.setState({
                            currentUser: userResponse.username,
                            isAdmin: userResponse.isAdmin    
                        }); 
                    }
                }
            } else {
                 var resp = xhr.response;
                    if(resp==null || resp==undefined || resp==""){
                        this.props.errorHandler("Failed to get current user.");
                }else{  
                    var errorResponse = JSON.parse(xhr.response);
                    var error =  errorResponse.error + ": " + errorResponse.message;
                    console.log(errorResponse.error +": " + errorResponse.message);                   
                }  
            }
        });
        xhr.send();  
      }
    },
    componentDidMount:function(){
        this.getCurrentUser();
    },
    render:function(){
        return (
            <div>
              <div className="navigation">
                   <ul>
                       <li>
                          <IndexLink to="/app">Audio Library</IndexLink>
                       </li>
                        {Auth.isUserAuthenticated() ? (
                        <li className="navigation-right">
                          <Link to="/logout">Log out</Link>
                        </li>)
                       :(<li className="navigation-right">
                          <Link to="/login">Log in</Link>
                        </li>)}
                   </ul>
            </div>
        {(this.props.children==null || this.props.children==undefined)
                    ? (<div className="welcome-page">
                           <h1>WELCOME</h1>
                        {Auth.isUserAuthenticated()
                            ? (<div>
                            <p>Thank You!</p>
                            <Link className="button-big" to="/app">Let's start</Link>
                            <Link className="button-big" to="/logout">Log Out</Link>
                            </div>)
                        :(<div>
                        <p>Please, Authorize!</p>
                        <Link className="button-big" to="/login">Log In</Link></div>)}
                        </div>)
        :(this.props.children)}
        </div>
        );
    }
});

export default AppContainer;

/*  {Auth.isUserAuthenticated() ? (
                        <li className="navigation-right">
                          <div className="navigation-user">
                              Hello, {this.state.currentUser}
                          </div>
                        </li>):""}*/