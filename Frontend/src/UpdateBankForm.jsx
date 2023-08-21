import React, { Component } from 'react'
import {Button, Form,Row,Col,InputGroup, Alert, Spinner, Badge} from 'react-bootstrap';
export default class UpdateBankForm extends Component {
    constructor(props) {
        console.log("1");
        super(props);
        this.state = {
            validated: false,
            bankId:props.bankId,
            name: props.name,
            dateEstablished:props.dateEstablished,
            totalBranches:props.totalBranches,
            websiteUrl:props.websiteUrl,
            successMsg:"",
            failMsg:"",
            postResult:false,
            fail:false,
            isLoading: false,
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
       this.setState({postResult:false});
    
      if(this.state.name.length !==0 && this.state.dateEstablished.length!==0  && this.state.totalBranches.length!==0 && this.state.websiteUrl!=null && this.state.websiteUrl.length!==0 ){
        console.log(this.state.dateEstablished);
        this.setState({ isLoading: true });
        fetch("http://localhost:8080/updateBank", {
          method:'POST',
          body: JSON.stringify({
            bankId:this.state.bankId,
            name:this.state.name, 
            websiteUrl:this.state.websiteUrl, 
            dateEstablished:this.state.dateEstablished, 
            totalBranches:this.state.totalBranches}),
          headers: {'Content-Type': 'application/json'}
        })
        .then(res => res.json(), (error)=>{ this.setState({fail:true}); this.setState({ isLoading: false}); console.log("Error:"+error)})
        .then((data) => {
          console.log("data function called!");
          this.setState({ successMsg: data.successMsg});
          this.setState({ failMsg: data.failureMsg});
          this.setState({ postResult: true });
          this.setState({ isLoading: false });
      },
        (error) => {this.setState({fail:true}); this.setState({ isLoading: false }); console.log("Data Error:", error) })
      .catch((error) => {this.setState({fail:true}); this.setState({ isLoading: false }); console.log("Catch Error:", error)})
      }
    }
      changeText = (event)=>{
        this.setState({[event.target.name]:event.target.value})
      }
     
      render() {
        const today = new Date().toISOString().split('T')[0];
        const { validated, isLoading, isDateValid} = this.state;
        return (
          <div className="add-bank-container">
          <div><h1>Update Bank</h1>
          <br />
            <Form validated={validated} className='add-bank-form'>
        
            <Row className="mb-3">
            <Form.Group  controlId="validationCustom01">
            <Form.Label>Bank Id</Form.Label>
              <Form.Control
                type="text"
                defaultValue={this.state.bankId}
                name="bankId"
                readOnly
                disabled
              />
            </Form.Group>
            </Row>

          <Row className="mb-3">
            <Form.Group  controlId="validationCustom01">
            <Form.Label>Bank Name</Form.Label>
            <InputGroup hasValidation>
        <InputGroup.Text id="basic-addon1"><span className="material-symbols-outlined">account_balance</span></InputGroup.Text>
              <Form.Control
                required
                type="text"
                defaultValue={this.state.name}
                placeholder="bank name"
                name="name"
                onChange={this.changeText}
              />
            {/* <Form.Control.Feedback>Looks good!</Form.Control.Feedback> */}
            <Form.Control.Feedback type="invalid">Please provide valid Bank Name</Form.Control.Feedback>
            </InputGroup>
            </Form.Group>
            </Row>
    
            <Row className="mb-3">
            <Form.Group controlId="validationCustom04">
              <Form.Label>Website</Form.Label>
              <InputGroup hasValidation>
        <InputGroup.Text id="basic-addon1"><span class="material-symbols-outlined">link</span></InputGroup.Text>
              <Form.Control
                required
                type="url"
                placeholder="example-> https://www.rbi.org.in/"
                defaultValue={this.state.websiteUrl}
                name="websiteUrl"
                onChange={this.changeText}
              />
              <Form.Control.Feedback type="invalid">
              Please provide valid URL.</Form.Control.Feedback> 
             </InputGroup>
            </Form.Group>
          </Row>
    
          <Row className="mb-3">
            <Form.Group as={Col} md="6" controlId="validationCustom03">
            <Form.Label>Total Branches</Form.Label>
              <Form.Control
                required
                type="number"
                name="totalBranches"
                defaultValue={this.state.totalBranches}
                min="0"
                onChange={this.changeText}
              />
            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            <Form.Control.Feedback type="invalid">Please provide valid Total Branches</Form.Control.Feedback>
            </Form.Group>
            <Form.Group as={Col} md="6" controlId="validationCustom02">
              <Form.Label>Date Of Establish</Form.Label>
              <Form.Control
                required
                type="date"
                placeholder="Establish Date"
                defaultValue={this.state.dateEstablished}
                name="dateEstablished"
                max={today}
                onChange={this.changeText}
              />
              <Form.Control.Feedback type="invalid">
              Please provide valid date.</Form.Control.Feedback> 
            </Form.Group>
          </Row>
    
          <Button variant='secondary' type="button"  onClick={this.click} disabled={isLoading}>{isLoading ? <div><Spinner
              as="span"
              animation="border"
              size="sm"
              role="status"
              aria-hidden="true"
            /> Loading...</div>: "Submit"}</Button>
        </Form>   
        {
              (this.state.postResult) ? (<div>
            <div className='message'>
              <Badge pill bg="success">
              {this.state.successMsg}
            </Badge>
            </div>
            <div className='message'>
              <Badge pill bg="danger">
              {this.state.failMsg}
            </Badge>
            </div>
            </div>) : ""
          }
        {
            (this.state.fail) ? (<Alert>Server error, try again later!</Alert>): ""
        }
          </div>
          </div>
        )
      }
    }