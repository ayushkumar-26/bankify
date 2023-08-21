import React, { Component } from 'react'
import bankify from './bankify.png';
import logo from './Images/logo.png';
import { Container, Navbar, NavDropdown, Nav, Button, Modal } from 'react-bootstrap';
import { NavLink } from 'react-router-dom';
import './AppHeader.css';
import Login from './Login';
export default class AppHeader extends Component {
  constructor(props) {
    super(props);
    this.state = {
      show: false,
    }
  }
  handleClose = () => {
    this.setState({ show: false });
  }
  handleShow = () => {
    this.setState({ show: true });
  }
  click = (event) => {
    console.log("click is called");
    this.props.onLogout();
  }
  render() {
    if (this.state.isLogin === false) {
      return <Login></Login>
    }
    return (
      <div>
        <Navbar expand="lg" bg="dark" data-bs-theme="dark" style={{ minHeight: "80px", fontSize: "20px" }}>
          <Container fluid>
            <Navbar.Brand style={{ padding: "0%", fontSize: "30px", fontFamily: "'Margarine', cursive" }}><img src={logo} alt="bankify" className="logo" />BANKiFY</Navbar.Brand>
            <Navbar.Toggle aria-controls="basic-navbar-nav" style={{ position: "absolute", top: "15px", right: "15px" }} />
            <Navbar.Collapse id="basic-navbar-nav">
              <Nav className="me-auto">
                <Nav.Link ><NavLink className='navtabs' activeClassName="activeNav" to="/">Home</NavLink></Nav.Link>
                <Nav.Link href="#" id="fadeshow1" disabled>
                  |
                </Nav.Link>
                <NavDropdown className='navtabs' title="Add" id="basic-nav-dropdown">
                  <NavDropdown.Item className='item'>
                    <NavLink className="links" to="/addBank">Add Bank</NavLink> </NavDropdown.Item>
                  <NavDropdown.Item>
                    <NavLink className="links" to="/addCustomer">Add Customer</NavLink>
                  </NavDropdown.Item>
                </NavDropdown>
                <Nav.Link href="#" id="fadeshow1" disabled>
                  |
                </Nav.Link>
                <NavDropdown className='navtabs' title="Update" id="basic-nav-dropdown">
                  <NavDropdown.Item className='item'>
                    <NavLink className="links" to="/updateBank">Upadate Bank</NavLink> </NavDropdown.Item>
                  <NavDropdown.Item>
                    <NavLink className="links" to="/updateCustomer">Update Customer</NavLink>
                  </NavDropdown.Item>
                </NavDropdown>
                <Nav.Link href="#" id="fadeshow1" disabled>
                  |
                </Nav.Link>
                <NavDropdown className='navtabs' title="Find All" id="basic-nav-dropdown">
                  <NavDropdown.Item className='item'>
                    <NavLink className="links" to="/findAllBanks">Find All Bank</NavLink> </NavDropdown.Item>
                  <NavDropdown.Item>
                    <NavLink className="links" to="/findAllCustomers">Find All Customer</NavLink>
                  </NavDropdown.Item>
                </NavDropdown>
                <Nav.Link href="#" id="fadeshow1" disabled>
                  |
                </Nav.Link>
                <NavDropdown className='navtabs' title="Find By Id" id="basic-nav-dropdown">
                  <NavDropdown.Item className='item'>
                    <NavLink className="links" to="/fetchBankById">Find Bank By Id</NavLink> </NavDropdown.Item>
                  <NavDropdown.Item>
                    <NavLink className="links" to="/fetchCustomerById">Find Customer By Id</NavLink>
                  </NavDropdown.Item>
                </NavDropdown>
              </Nav>
            </Navbar.Collapse>
            <div style={{ justifyContent: "end", display: "flex" }}>
              <Navbar.Collapse className="justify-content-end">
                <Navbar.Text className='navtabs' style={{ color: "white" }}>
                  <Button type="button" onClick={this.handleShow}>Log Out</Button>
                </Navbar.Text>
              </Navbar.Collapse>
              <Navbar.Collapse className="justify-content-end">
                <Navbar.Text className='navtabs' style={{ color: "white" }}>
                  Signed in as: {this.props.name}
                </Navbar.Text>
              </Navbar.Collapse>
            </div>
          </Container>
        </Navbar>


        <Modal show={this.state.show} onHide={this.handleClose} >
          <Modal.Header closeButton className='modal-container'>
            <Modal.Title>Log Out</Modal.Title>
          </Modal.Header>
          <Modal.Body className='modal-body'>Are you sure, you want to log out?</Modal.Body>
          <Modal.Footer className='modal-container'>
            <Button variant="secondary" size='lg' onClick={this.handleClose}>
              No
            </Button>
            <Button variant="primary" size='lg' onClick={this.click}>
              Yes
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    )
  }
}
