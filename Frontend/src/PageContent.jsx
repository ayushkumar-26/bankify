import 'bootstrap/dist/css/bootstrap.min.css';
import React, { Component } from 'react';
import { Col, Container, Row } from 'react-bootstrap';
import { NavLink, Route, Routes } from 'react-router-dom';
import AddBank from './AddBank';
import AddCustomer from './AddCustomer';
import './App.css';
import FindAllBanks from './FindAllBanks';
import FindAllCustomers from './FindAllCustomers';
import FindBankById from './FindBankById';
import FindCustomerById from './FindCustomerById';
import FindCustomersWithName from './FindCustomersWithName';
import Home from './Home';
import PageNotFound from './PageNotFound';
import UpdateBank from './UpdateBank';
import UpdateCustomer from './UpdateCustomer';
import './index.css';
import terms from './terms';
export default class PageContent extends Component {
  render() {
    return (
      <div>
        <Container fluid>
          <Row>

            <Col md={3} lg={3} style={{ backgroundColor: "lightgrey", fontSize: "20px" }}>
              <ul className="d-none d-md-block">
                <li>
                  <NavLink activeClassName="active" to="/">Home</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/addBank">Add Bank</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/updateBank">Update Bank</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/fetchBankById">Find a Bank by Id</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/findAllBanks">Fetch All Banks</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/addCustomer">Add Customer</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/updateCustomer">Update Customer</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/fetchCustomerById">Find a Customer By Id</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/findCustomerWithName">Find a Customers By Name</NavLink>
                </li>
                <li>
                  <NavLink activeClassName="active" to="/findAllCustomers">Fetch all Customers</NavLink>
                </li>
              </ul>

              <nav aria-label="breadcrumb" className="d-md-none">
                <ol className="breadcrumb">
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/">Home</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/addBank">Add Bank</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/updateBank">Update Bank</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/fetchBankById">Find a Bank by Id</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/findAllBanks">Fetch All Banks</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/addCustomer">Add Customer</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/updateCustomer">Update Customer</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/fetchCustomerById">Find a Customer By Id</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/findCustomerWithName">Find a Customers By Name</NavLink>
                  </li>
                  <li className="breadcrumb-item">
                    <NavLink activeClassName="active" to="/findAllCustomers">Fetch all Customers</NavLink>
                  </li>
                </ol>
              </nav>
            </Col>

            <Col md={9} lg={9} className='currCol'>
              <Routes>
                <Route exact path="/" Component={Home} />
                <Route path="/addBank" Component={AddBank} />
                <Route path='/updateBank' Component={UpdateBank} />
                <Route path="/fetchBankById" Component={FindBankById} />
                <Route path='/findAllBanks' Component={FindAllBanks} />
                <Route path='/addCustomer' Component={AddCustomer} />
                <Route path='/updateCustomer' Component={UpdateCustomer} />
                <Route path='/fetchCustomerById' Component={FindCustomerById} />
                <Route path='/findCustomerWithName' Component={FindCustomersWithName} />
                <Route path='/findAllCustomers' Component={FindAllCustomers} />
                <Route path='/terms' Component={terms} />
                <Route path='*' Component={PageNotFound} />
              </Routes>
            </Col>

            {/* <Col md={3} lg={3} style={{ backgroundColor: "brown", paddingTop: "4em" }}>
          <Ad/>
          </Col> */}
          </Row>
        </Container>
      </div>
    )
  }
}
