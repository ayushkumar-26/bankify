import React, { Component } from 'react'
import {Button, Form,Row,Col,InputGroup, Alert, Spinner, ListGroup, Table, Badge} from 'react-bootstrap';
export default class FindCustomerWithName extends Component {

    constructor(props){
        super(props);
        this.state = {
          validated:false,
          res:[],
          name:"",
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

      if(this.state.name.length!== 0){
        this.setState({ isLoading: true });
        event.preventDefault();
        this.setState({getResult: false});
        const url = new URL("http://localhost:8080/findCustomerWithName");
        const params = {name:this.state.name};
        url.search = new URLSearchParams(params).toString();
        fetch(url, { method: 'GET' })
        .then(res => res.json(), (error)=>{ this.setState({fail:true}); this.setState({ isLoading: false }); console.log("Error:"+error)})
        .then((data) => {
          this.setState({ successMsg: data.successMsg});
          this.setState({ failMsg: data.failureMsg});
          this.setState({res:data.listBankCustomerCustomized});
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
    render() {

        const { validated, isLoading} = this.state;
        return (
          <div><h4>Find Customer By Name</h4>
          <br />
            <Form validated={validated} >
          <Row className="mb-3">
            <Col className='mt-2 mb-1' md={6}>
            <Form.Group controlId="validationCustom01">
              <Form.Control
                required
                type="text"
                placeholder="Customer Name"
                name="name"
                onChange={this.changeText}
                autoFocus
              />
            <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            <Form.Control.Feedback type="invalid">Please provide valid Name</Form.Control.Feedback>
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
            <Table striped bordered hover variant="dark">
                            <thead>
                                <tr>
                                  <th>Customer ID:</th><th>Name:</th><th>Bank ID:</th><th>Bank Name:</th><th>Account No.:</th>
                                </tr>
                            </thead>
                                <tbody>
                            {this.state.res.map((contact, i) =>
                                <tr key={i} >
                                    <td>{contact.customerId}</td>
                                    <td>{contact.customerName}</td>
                                    <td>{contact.bankId}</td>
                                    <td>{contact.bankName}</td>
                                    <td>{contact.accountNumber}</td>
                            
                                </tr>
                            )}
                            </tbody>
                        </Table></div> 
                        : 
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
