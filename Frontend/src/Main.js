import 'bootstrap/dist/css/bootstrap.min.css';
import React, { Component } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import './App.css';
import AppFooter from './AppFooter';
import AppHeader from './AppHeader';
import Login from './Login';
import PageContent from './PageContent';
import './index.css';
export default class Main extends Component {
  constructor(props) {
    super(props);
    this.state = {
      userName: localStorage.getItem('userName') || "",
      Login: localStorage.getItem('Login') === 'true',
    }
  }
  handleLogin = (user) => {
    localStorage.setItem('userName', user);
    this.setState({ userName: user });
    localStorage.setItem('Login', 'true');
    this.setState({ Login: true });
  }
  handleLogout = () => {
    localStorage.setItem('Login', 'false');
    this.setState({ Login: false });
  }
  render() {
    return (
      <div className="page-container">
        {
          (this.state.Login) ?
            <Router>
              <AppHeader name={this.state.userName} onLogout={this.handleLogout}></AppHeader>
              <PageContent></PageContent>
              <ToastContainer />
              <AppFooter></AppFooter>
            </Router>
            :
            <Login onLogin={this.handleLogin}></Login>}
      </div>
    )
  }
}
