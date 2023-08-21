import React, { Component } from 'react'
import { Col, Container, ProgressBar, Row } from 'react-bootstrap'
import './AppFooter.css';
import { NavLink } from 'react-router-dom';
import {BsFacebook, BsInstagram, BsTwitter, BsYoutube} from 'react-icons/bs';
export default class AppFooter extends Component {
  render() {
    return (

      <div className="footer">
        <Container fluid>
        <Row>
          <Col sm={12} md={4}>
            <NavLink to="/terms" style={{color:"white"}}> 
          Terms and Conditions</NavLink>
        </Col>
        <Col sm={12} md={4}>
        <span class="material-icons" style={{ verticalAlign: "middle" }}>
          copyright
        </span>
        <span style={{ verticalAlign: "middle" }}>
          &nbsp;copyright Bankify 2023 | All rights reserved.
        </span>
        </Col >
        <Col sm={12} md={4}>
          <a href="#" className="icons"><BsFacebook/></a>
          <a href="#" className="icons"><BsInstagram/></a>
          <a href="#" className="icons"><BsTwitter/></a>
          <a href="#" className="icons"><BsYoutube/></a>
        </Col>
        </Row>
        </Container>
      </div>
    )
  }
}
