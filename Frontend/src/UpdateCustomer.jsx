import React, { Component } from 'react'
import UpdateCustomerForm from './UpdateCustomerForm';
import {Button, Form,Row,Col,InputGroup, Alert, Spinner, ListGroup, Badge} from 'react-bootstrap';
export default class UpdateCustomer extends Component {

    constructor(props){
        super(props);
        this.state = {
          validated:false,
          res:{},
          id:"",
          successMsg:"",
          failMsg:"",
          getResult:false,
          fail:false,
          isLoading:false,
          genders:""
        }
      }
    
    click =(event)=>{
        const form = event.currentTarget;
      if (form.checkValidity() === false) {
        event.preventDefault();
        event.stopPropagation();
      }
    
       this.setState({validated:true});
       this.setState({fail:false});
       this.setState({getResult:false});

      if(this.state.id.length!== 0){
        this.setState({ isLoading: true });
        event.preventDefault();
        this.setState({getResult: false});
        const url = new URL("http://localhost:8080/fetchCustomerById");
        const params = {id:this.state.id};
        url.search = new URLSearchParams(params).toString();
        fetch(url, { method: 'GET' })
        .then(res => res.json(), (error)=>{ this.setState({fail:true}); this.setState({ isLoading: false }); console.log("Error:"+error)})
        .then((data) => {
          this.setState({ successMsg: data.successMsg});
          this.setState({ failMsg: data.failureMsg});
          this.setState({res:data.c});
          console.log(this.state.failMsg);
          console.log(this.state.successMsg);
          this.setState({ getResult: true });
          this.setState({ isLoading: false });

          if(data.c.gender==="M") this.setState({genders:"male"});
          if(data.c.gender==="F") this.setState({genders:"female"});
          if(data.c.gender==="O") this.setState({genders:"other"});
      },
          (error) => {this.setState({ isLoading: false }); console.log("Data Error:", error) })
      .catch((error) => {this.setState({ isLoading: false }); console.log("Catch Error:", error)})
      }
    }
    
    changeText = (event)=>{
      this.setState({[event.target.name]:event.target.value});
    }
    convertToDate = (dateArray)=>{
        const [year,month,day] = dateArray;
        return `${year}-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}`;
    };
    render() {

        const { validated, isLoading} = this.state;
        return (
          <div><h4>Find Customer By Id</h4>
          <br />
            <Form validated={validated} >
          <Row className="mb-3">
            <Col className='mt-2 mb-1' md={6}>
            <Form.Group controlId="validationCustom01">
              <Form.Control
                required
                type="number"
                placeholder="Customer Id"
                name="id"
                min="0"
                onChange={this.changeText}
                autoFocus
              />
            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            <Form.Control.Feedback type="invalid">Please provide valid Customer Id</Form.Control.Feedback>
            </Form.Group>
            </Col>
            <Col className='mt-2 ' md={6}>
              <Button variant='secondary' type="submit"  onClick={this.click} disabled={isLoading}>{isLoading ? <div><Spinner
              as="span"
              animation="border"
              size="sm"
              role="status"
              aria-hidden="true"
            /> Loading...</div>: "Submit"}</Button>
              </Col>
          </Row>
          
        </Form>
        <br />
        <br />
        {
              (this.state.getResult) ? (
              (this.state.successMsg) ? <div>
           <div className='message'>
              <Badge pill bg="success">
              {this.state.successMsg}
            </Badge>
            </div>
            <UpdateCustomerForm
                 id = {this.state.res.id}
                 name = {this.state.res.name} 
                 email = {this.state.res.email}
                 dob = {this.convertToDate(this.state.res.dob)}
                 contactNumber={this.state.res.contactNumber}
                 accountNumber={this.state.res.accountNumber}
                 
                 gender={this.state.genders}
                 city = {this.state.res.city}
                 bankId = {this.state.res.bankId}
            ></UpdateCustomerForm>
            
            
              </div> : 
              <div className='message'>
              <Badge pill bg="danger">
              {this.state.failMsg}
            </Badge>
            </div>
              ) : ""
          }
        {
            (this.state.fail) ? (<Alert>Server error, try again later!</Alert>): ""
        }
          </div>
        );
      }
    }
