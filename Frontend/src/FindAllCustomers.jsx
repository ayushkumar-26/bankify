import './index.css';
import React, { Component } from 'react'

import { Table, Button, Placeholder, Badge } from 'react-bootstrap';

export default class FindAllCustomers extends Component {
    constructor() {
        super();
        this.state = {
            responseData: [],
            result: false,
            getResult: false,
            msg: "",
            fail: false
        }
    }
    findAll = () => {
        this.setState({ getResult: false });
        fetch("http://localhost:8080/findAllCustomers", { method: 'GET' }).then(res => res.json(), (error) => { this.setState({ fail: true }); console.log("Errorrr:", error) })
            .then((data) => {
                this.setState({ responseData: data.listCustomer });
                this.setState({ msg: data.successMsg });
                this.setState({ result: true });
                this.setState({ getResult: true });
                this.setState({ fail: false });
            },
                (error) => { this.setState({ fail: true }); console.log("Data Error:", error) })
            .catch((error) => { this.setState({ fail: true }); console.log("Catch Error:", error) })
            .finally("finally")
    }
    render() {


        return (
            <div>
                <div className="d-grid gap-2">
                    <Button variant="primary" size='lg' onClick={this.findAll}>Click Me</Button>
                </div>
                <br />
                <br />
                {
                    (this.state.getResult) ? (<div><div className='message'>
                        <Badge pill bg="success">
                            {this.state.msg}
                        </Badge>
                    </div>

                        <Table striped bordered hover variant="dark">
                            <thead>
                                <tr><th>Customer ID:</th><th>Name:</th><th>City:</th><th>Bank ID:</th><th>Account No.:</th>
                                    <th>DOB:</th><th>Gender:</th><th>Email:</th><th>Contact Number:</th></tr>
                            </thead>
                            <tbody>
                                {this.state.responseData && this.state.responseData.map((contact, i) =>
                                    <tr key={i} >
                                        <td>{contact.id}</td>
                                        <td>{contact.name}</td>
                                        <td>{contact.city}</td>
                                        <td>{contact.bankId}</td>
                                        <td>{contact.accountNumber}</td>
                                        <td>{new Date(contact.dob).toLocaleDateString()}</td>
                                        <td>{contact.gender}</td>
                                        <td>{contact.email}</td>
                                        <td>{contact.contactNumber}</td>
                                        {/* <td>{contact.createdAt}</td> */}
                                    </tr>
                                )}
                            </tbody>
                        </Table></div>)

                        : ""}
                {
                    (this.state.fail) ? (<div>
                        <Placeholder as="p" animation="glow">
                            <Placeholder xs={12} />
                        </Placeholder>
                        <Placeholder as="p" animation="glow">
                            <Placeholder xs={12} />
                        </Placeholder>
                        <Placeholder as="p" animation="glow">
                            <Placeholder xs={12} />
                        </Placeholder>
                        <Placeholder as="p" animation="glow">
                            <Placeholder xs={12} />
                        </Placeholder>
                        <Placeholder as="p" animation="glow">
                            <Placeholder xs={12} />
                        </Placeholder>
                        <Placeholder as="p" animation="glow">
                            <Placeholder xs={12} />
                        </Placeholder>

                    </div>) : ""
                }

            </div>

        )
    }
}

// {
//     this.state.responseData.listCustomer.map((Customers, i) =>
//         <div key={i}>
//             <p>Customer Name: {Customers.name}</p>
//         </div>
//     )}
