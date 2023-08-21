import React, { Component } from 'react'
import UpdateBankForm from './UpdateBankForm';
import {Button, Form,Row,Col,InputGroup, Alert, Spinner, ListGroup, Badge} from 'react-bootstrap';

export default class UpdateBank extends Component {
    constructor(props){
        super(props);
        this.state = {
          validated:false,
          res:{},
          bankId:"",
          successMsg:"",
          failMsg:"",
          getResult:false,
          fail:false,
          isLoading:false
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

      if(this.state.bankId.length!== 0 && this.state.bankId>0){
        this.setState({ isLoading: true });
        event.preventDefault();
        this.setState({getResult: false});
        const url = new URL("http://localhost:8080/fetchBankById");
        const params = {id:this.state.bankId};
        url.search = new URLSearchParams(params).toString();
        fetch(url, { method: 'GET' })
        .then(res => res.json(), (error)=>{ this.setState({fail:true}); this.setState({ isLoading: false }); console.log("Error:"+error)})
        .then((data) => {
          console.log("res data", data);
          this.setState({ successMsg: data.successMsg});
          this.setState({ failMsg: data.failureMsg});
          this.setState({res:data.b});
          console.log(this.state.failMsg);
          console.log(this.state.successMsg);
          this.setState({ getResult: true });
          this.setState({ isLoading: false });
      },
          (error) => {this.setState({fail:true}); this.setState({ isLoading: false }); console.log("Data Error:", error) })
      .catch((error) => {this.setState({fail:true}); this.setState({ isLoading: false }); console.log("Catch Error:", error)})
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
          <div><h4>Find Bank By Id</h4>
          <br />
            <Form validated={validated} >
            <Row className="mb-3">
          <Col className='mt-2 mb-1' md={6} sm={8} xs={8}>
            <Form.Group controlId="validationCustom01">
              <Form.Control
                required
                type="number"
                placeholder="bank Id"
                name="bankId"
                min="1"
                onChange={this.changeText}
                autoFocus
              />
            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            <Form.Control.Feedback type="invalid">Please provide valid Bank Id</Form.Control.Feedback>
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
            <UpdateBankForm
                bankId = {this.state.res.bankId}
                name = {this.state.res.name}
                dateEstablished = {this.convertToDate(this.state.res.dateEstablished)}
                totalBranches = {this.state.res.totalBranches}
                websiteUrl = {this.state.res.websiteUrl}

            ></UpdateBankForm>
            
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
        )
      }
    }
