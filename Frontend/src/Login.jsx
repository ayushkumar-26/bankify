import React, { Component } from 'react';
import { Form, Button, Alert, Row, Col } from 'react-bootstrap';
import { Navigate, Route, Routes, redirect, BrowserRouter as Router } from 'react-router-dom';
import './Login.css'; // Import your custom CSS file for styling
import Main from './Main';
import { isPresent } from '../src/credentials'
import { ToastContainer, toast } from 'react-toastify';
import logo from './Images/logo.png';
class Login extends Component {
  constructor(props) {
    super(props);
    this.state = {
      username: '',
      password: '',
      error: '',
      validated: false,
    };
  }

  handleInputChange = (event) => {
    const { name, value } = event.target;
    this.setState({
      [name]: value,
      isLogin: false,
    });
  };

  handleLogin = (event) => {
    const form = event.currentTarget;
    event.preventDefault();
    event.stopPropagation();
    this.setState({ validated: true });

    const { username, password } = this.state;


    if (username.length !== 0 && password.length !== 0) {
      if (isPresent(username, password)) {
        // Successful login logic
        this.setState({ isLogin: true });
        this.setState({ error: '' });
        toast.success('Login Successful!', {
          position: "top-right",
          autoClose: 2000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
          theme: "dark",
        })
        this.props.onLogin(this.state.username);
        //alert('Login successful');
      } else {
        this.setState({ error: 'Invalid username or password' });
      }
    }
    else this.setState({ error: '', isLogin: false });


  };

  render() {
    const { username, password, error, validated } = this.state;
    // if(this.state.isLogin){ 

    // console.log("yes");
    // return <Main userName={username}></Main>
    return (
      <div className="login-container">

        <div className='login-content'>
          <div style={{ padding: "0%", fontSize: "80px", fontFamily: "'Margarine', cursive", margin: "20px" }}><img src={logo} alt="bankify" style={{ width: "120px" }} />BANKiFY</div>
          <div>
            <Form noValidate validated={validated} className="login-form" onSubmit={this.handleLogin}>
              <h2>Login</h2>
              {error && <div style={{ color: "red" }}>{error} </div>}
              <Form.Group controlId="username" className='fields'>
                <Form.Label>Username</Form.Label>
                <Form.Control
                  type="text"
                  name="username"
                  value={username}
                  onChange={this.handleInputChange}
                  required
                />
                <Form.Control.Feedback type='invalid'>User name cannot be empty.</Form.Control.Feedback>
              </Form.Group>
              <Form.Group controlId="password" className='fields'>
                <Form.Label>Password</Form.Label>
                <Form.Control
                  type="password"
                  name="password"
                  value={password}
                  onChange={this.handleInputChange}
                  required
                />
                <Form.Control.Feedback type='invalid'>Password cannot be empty.</Form.Control.Feedback>
              </Form.Group>
              <Row><Col className='fields'><a href="#">Forget password?</a></Col></Row>
              <Button variant="primary" type="submit" >
                Login
              </Button>
              <Row><Col className='fields' style={{ textAlign: "center" }}>don't have an account? <a href="#">sign up</a></Col></Row>
            </Form>
          </div>
        </div>
      </div>
    );
  }
}

export default Login;
