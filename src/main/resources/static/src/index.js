import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, hashHistory } from 'react-router';
import routes from './routes.js';



ReactDOM.render((<Router history={hashHistory} routes={routes} />),
    document.getElementById('mount-point'));