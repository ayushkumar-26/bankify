import React, { Component } from 'react';
import { Button, Form, Row, Col, InputGroup, Alert, Spinner, Badge } from 'react-bootstrap';
import * as formik from 'formik';
import * as Yup from 'yup';
import "./AddCustomer.css"
import { NavLink } from 'react-router-dom';

class UpdateCustomerForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      successMsg: "",
      failMsg: "",
      postResult: false,
      fail: false,
      isLoading: false,
    }
    this.schema = Yup.object().shape({
      name: Yup.string().required('Name is required'),
      email: Yup.string().email('Invalid email').required('Email is required'),
      dob: Yup.date()
        .required('Date of birth is required')
        .test('age', 'Age must be greater than 18', function (value) {
          const today = new Date();
          const birthDate = new Date(value);
          const age = today.getFullYear() - birthDate.getFullYear();
          return age >= 18;
        }),
      contactNumber: Yup.string()
        .matches(/^\d{10}$/, 'Phone number must be 10 digits')
        .required('Phone number is required'),
      accountNumber: Yup.string()
        .matches(/^\d{10,15}$/, 'Account number must be between 10 to 15 digits')
        .required('Account number is required'),
      gender: Yup.string().required('Choose an option'),
      terms: Yup.bool().required().oneOf([true], 'Terms must be accepted'),
      bankId: Yup.string().required('Bank Id is required'),
      city: Yup.string().required('City is required'),
    });
  }

  handleSubmit = (values) => {
    this.setState({ fail: false });
    this.setState({ postResult: false });
    this.handleClick(values);
    console.log(this.state.name);
  }
  handleClick = (values) => {
    console.log(values);
    var genders = "";
    if (values.gender === "male") genders = "M";
    if (values.gender === "female") genders = "F";
    if (values.gender === "other") genders = "O";
    if (values.name.length !== 0 &&
      values.city.length !== 0 &&
      values.email.length !== 0 &&
      values.contactNumber.length !== 0 &&
      values.accountNumber.length !== 0 &&
      values.dob.length !== 0) {
      this.setState({ isLoading: true });
      fetch("http://localhost:8080/updateCustomer", {
        method: 'POST',
        body: JSON.stringify({
          id: values.id,
          name: values.name,
          email: values.email,
          bankId: values.bankId,
          city: values.city,
          dob: values.dob,
          contactNumber: values.contactNumber,
          accountNumber: values.accountNumber,
          gender: genders,
        }),
        headers: { 'Content-Type': 'application/json' }
      })
        .then(res => res.json(), (error) => { this.setState({ fail: true }); this.setState({ isLoading: false }); console.log("Error:" + error) })
        .then((data) => {
          console.log("data function called!");
          this.setState({ successMsg: data.successMsg });
          this.setState({ failMsg: data.failureMsg });
          this.setState({ postResult: true });
          this.setState({ isLoading: false });
        },
          (error) => { this.setState({ fail: true }); this.setState({ isLoading: false }); console.log("Data Error:", error) })
        .catch((error) => { this.setState({ fail: true }); this.setState({ isLoading: false }); console.log("Catch Error:", error) })
    }
  }

  handleButtonClick = () => {
    this.formik.handleSubmit();
  };
  render() {
    const { Formik } = formik;
    return (
      <div className="add-customer-container">
        <div><h1>Update Customer</h1>
          <br />
          <Formik
            validationSchema={this.schema}
            onSubmit={this.handleSubmit}
            innerRef={(formik) => (this.formik = formik)}
            initialValues={{
              id: this.props.id,
              name: this.props.name,
              email: this.props.email,
              dob: this.props.dob,
              contactNumber: this.props.contactNumber,
              accountNumber: this.props.accountNumber,
              gender: this.props.gender,
              bankId: this.props.bankId,
              city: this.props.city,
              terms: false,
            }}
          >
            {({ handleSubmit, handleChange, handleBlur, values, touched, errors }) => (
              <Form noValidate className='add-customer-form'>
                <Row className="mb-3">
                  <Form.Group controlId="validationFormik01">
                    <Form.Label>Customer Id</Form.Label>

                    <Form.Control
                      type="text"
                      name="id"
                      value={values.id}
                      readOnly
                      disabled
                    />
                  </Form.Group>
                </Row>
                <Row className="mb-3">
                  <Form.Group controlId="validationFormik01">
                    <Form.Label>Name</Form.Label>
                    <InputGroup hasValidation>
                      <InputGroup.Text id="basic-addon1"><span class="material-symbols-outlined">person</span></InputGroup.Text>
                      <Form.Control
                        type="text"
                        name="name"
                        value={values.name}
                        onChange={handleChange}
                        isValid={touched.name && !errors.name}
                        isInvalid={touched.name && errors.name}
                      />
                      <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                      <Form.Control.Feedback type="invalid">
                        {errors.name}
                      </Form.Control.Feedback>
                    </InputGroup>
                  </Form.Group>
                  <Form.Group controlId="validationFormik02">
                    <Form.Label>Email</Form.Label>
                    <InputGroup>
                      <InputGroup.Text id="basic-addon1"><span class="material-symbols-outlined">
                        mail
                      </span></InputGroup.Text>
                      <Form.Control
                        type="email"
                        name="email"
                        onBlur={handleBlur}
                        value={values.email}
                        onChange={handleChange}
                        isValid={touched.email && !errors.email}
                        isInvalid={touched.email && errors.email}
                      />
                      <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
                      <Form.Control.Feedback type="invalid">
                        {errors.email}
                      </Form.Control.Feedback>
                    </InputGroup>
                  </Form.Group>
                </Row>

                <Row className="mb-3">
                  <Form.Group as={Col} md="6" controlId="validationFormikUsername">
                    <Form.Label>Contact Number</Form.Label>
                    <InputGroup hasValidation>
                      <InputGroup.Text id="basic-addon1"><span class="material-symbols-outlined">call</span></InputGroup.Text>
                      <Form.Control
                        type="number"
                        placeholder="Contact Number"
                        aria-describedby="inputGroupPrepend"
                        name="contactNumber"
                        value={values.contactNumber}
                        onChange={handleChange}
                        onBlur={handleBlur}
                        isValid={touched.contactNumber && !errors.contactNumber}
                        isInvalid={touched.contactNumber && errors.contactNumber}
                      />
                      <Form.Control.Feedback type="invalid">
                        {errors.contactNumber}
                      </Form.Control.Feedback>
                    </InputGroup>
                  </Form.Group>
                  <Form.Group as={Col} md="6" controlId="validationFormik03">
                    <Form.Label>City</Form.Label>
                    <InputGroup hasValidation>
                      <InputGroup.Text id="basic-addon1"><span class="material-symbols-outlined">location_on</span></InputGroup.Text>
                      <Form.Control
                        type="city"
                        placeholder="city"
                        name="city"
                        value={values.city}
                        onChange={handleChange}
                        onBlur={handleBlur}
                        isValid={touched.city && !errors.city}
                        isInvalid={touched.city && errors.city}
                      />
                      <Form.Control.Feedback type="invalid">
                        {errors.city}
                      </Form.Control.Feedback>
                    </InputGroup>
                  </Form.Group>
                </Row>

                <Row className="mb-3">
                  <Form.Group as={Col} md="6" controlId="validationFormik04">
                    <Form.Label>Gender</Form.Label>
                    <Form.Select
                      name="gender"
                      value={values.gender}
                      onChange={handleChange}
                      onBlur={handleBlur}
                      isInvalid={touched.gender && !!errors.gender}
                    >
                      <option value="" disabled>Choose...</option>
                      <option value="male">Male</option>
                      <option value="female">Female</option>
                      <option value="other">Other</option>
                    </Form.Select>
                    <Form.Control.Feedback type="invalid">
                      {errors.gender}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Form.Group as={Col} md="6" controlId="validationFormik05">
                    <Form.Label>DOB</Form.Label>
                    <Form.Control
                      type="date"
                      placeholder="dob"
                      name="dob"
                      value={values.dob}
                      onChange={handleChange}
                      onBlur={handleBlur}

                      isInvalid={touched.dob && errors.dob}
                    />
                    <Form.Control.Feedback type="invalid">
                      {errors.dob}
                    </Form.Control.Feedback>
                  </Form.Group>
                </Row>

                <Row className="mb-3">
                  <Form.Group as={Col} md="6" controlId="validationFormik03">
                    <Form.Label>Bank Id</Form.Label>
                    <Form.Control
                      type="number"
                      placeholder="Bank Id"
                      name="bankId"
                      value={values.bankId}
                      onChange={handleChange}
                      onBlur={handleBlur}
                      isValid={touched.bankId && !errors.bankId}
                      isInvalid={touched.bankId && errors.bankId}
                    />
                    <Form.Control.Feedback type="invalid">
                      {errors.bankId}
                    </Form.Control.Feedback>
                  </Form.Group>
                  <Form.Group as={Col} md="6" controlId="validationFormik03">
                    <Form.Label>Account Number</Form.Label>
                    <Form.Control
                      type="number"
                      placeholder="Account Number"
                      name="accountNumber"
                      value={values.accountNumber}
                      onChange={handleChange}
                      onBlur={handleBlur}
                      isValid={touched.accountNumber && !errors.accountNumber}
                      isInvalid={touched.accountNumber && errors.accountNumber}
                    />
                    <Form.Control.Feedback type="invalid">
                      {errors.accountNumber}
                    </Form.Control.Feedback>
                  </Form.Group>
                </Row>
                <Form.Group className="mb-3">
                  <Form.Check
                    required
                    name="terms"
                    label={
                      <span>
                        Agree to{' '}
                        <NavLink to="/terms" style={{ color: "white" }}>
                          terms and conditions
                        </NavLink>
                      </span>
                    }
                    onChange={handleChange}
                    isInvalid={touched.terms && errors.terms}
                    onBlur={handleBlur}
                    feedback={errors.terms}
                    feedbackType="invalid"
                    id="validationFormik0"
                  />
                </Form.Group>
                <Button variant='secondary' type="button" onClick={this.handleButtonClick} disabled={this.state.isLoading}>{this.state.isLoading ? <div><Spinner
                  as="span"
                  animation="border"
                  size="sm"
                  role="status"
                  aria-hidden="true"
                /> Loading...</div> : "Submit"}</Button>
              </Form>
            )}

          </Formik>
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
            (this.state.fail) ? (<Alert>Server error, try again later!</Alert>) : ""
          }
        </div>
      </div>
    );
  }
}


export default UpdateCustomerForm;
