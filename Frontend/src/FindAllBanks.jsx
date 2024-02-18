import { hover } from '@testing-library/user-event/dist/hover';
import './index.css';
import React, { Component } from 'react'

import { Table, Button, Placeholder, Badge } from 'react-bootstrap';

export default class FindAllBanks extends Component {
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
        fetch("http://localhost:8080/findAllBanks", { method: 'GET' }).then(res => res.json(), (error) => { this.setState({ fail: true }); console.log("Errorrr:", error) })
            .then((data) => {
                this.setState({ responseData: data.listBank });
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
                        <Table striped bordered hover variant='dark'>
                            <thead>
                                <tr><th>Bank ID:</th><th>Name:</th><th>Website:</th><th>Date Established:</th><th>Total Branches:</th></tr>
                            </thead>
                            <tbody>
                                {this.state.responseData && this.state.responseData.map((contact, i) =>
                                    <tr key={i}>
                                        <td>{contact.bankId}</td>
                                        <td>{contact.name}</td>
                                        <td><a href={contact.websiteUrl}>{contact.websiteUrl}</a></td>
                                        <td>{new Date(contact.dateEstablished).toLocaleDateString()}</td>
                                        <td>{contact.totalBranches}</td>
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
