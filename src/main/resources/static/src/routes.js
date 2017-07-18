import AppContainer from './components/AppContainer.jsx';
import AudioLibContainer from './components/AudioLibContainer.jsx';
import Login from './components/Login.jsx';
import AudioPreview from './components/AudioPreview.jsx';
import AudioEdit from './components/AudioEdit.jsx';
import AudioAdd from './components/AudioAdd.jsx';

import Auth from './auth/Auth';


const routes = {
  component: AppContainer,
  path : "/",
  childRoutes: [
    {
      path: '/app',
        //component:App,
      getComponent: (location, callback) => {
        if (Auth.isUserAuthenticated()) {
          callback(null, AudioLibContainer);
        } else {
          callback(null, Login);
        }
      }
    },
     {
      path: 'audio/edit(/:audioId)',
      getComponent: (location, callback) => {
        if (Auth.isUserAuthenticated()) {
          callback(null, AudioEdit);
        } else {
          callback(null, Login);
        }
      }
    },
    {
      path: '/audio/preview(/:audioId)',
      getComponent: (location, callback) => {
        if (Auth.isUserAuthenticated()) {
          callback(null, AudioPreview);
        } else {
          callback(null, Login);
        }
      }
    },
      {
        path:  '/audio/add',
        getComponent: (location, callback) => {
        if (Auth.isUserAuthenticated()) {
          callback(null, AudioAdd);
        } else {
          callback(null, Login);
        }
      }
      },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/logout',
      onEnter: (nextState, replace) => {
        Auth.deauthenticateUser();
        replace('/login');
      }
    }

  ]
};
export default routes;