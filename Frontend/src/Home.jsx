import React, { Component } from 'react'
import { Carousel } from 'react-bootstrap'

export default class Home extends Component {
  render() {
    return (
      <div>
        <br />
        <h2><a style={{fontFamily:"'Margarine', cursive" , color:"violet", fontSize:"4rem"}}>BANKiFY</a> &nbsp; Where banking transcends expectations.</h2>
      
        <br />
        <Carousel>
      <Carousel.Item>
        <img
          className="d-block w-100"
          style={{maxHeight: 500 }}
          src="https://ak.picdn.net/shutterstock/videos/4471070/thumb/1.jpg"
          alt="First slide"
          
        />
        <Carousel.Caption style={{backgroundColor:"rgba(49, 48, 48, 0.6)"}}>
          <h5>Trustworthy</h5>
          <p>user-centric approach ensures the highest level of security, safeguarding your financial information and offering personalized customer services.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          style={{maxHeight: 500 }}
          src="https://images.samsung.com/is/image/samsung/assets/in/support/home/contact/Contact_Us_KV_1440x640.jpg"          
          alt="Second slide"
        />
        <Carousel.Caption style={{backgroundColor:"rgba(49, 48, 48, 0.6)"}}>
          <h5>Efficient Account Management</h5>
          <p>Navigate through your financial journey seamlessly with Bankify’s intuitive tools, enabling streamlined account management.</p>
        </Carousel.Caption>
      </Carousel.Item>
      <Carousel.Item>
        <img
          className="d-block w-100"
          style={{maxHeight: 500 }}
          src="https://images.inc.com/uploaded_files/image/1920x1080/getty_497600893_328695.jpg"
          alt="Third slide"
        />
        <Carousel.Caption style={{backgroundColor:"rgba(49, 48, 48, 0.6)"}}>
          <h5>Innovation and Engagement at the Forefront</h5>
          <p >
          Bankify revolutionizes the digital landscape by enhancing how banks and customers engage, fostering mutual trust and elevating the modern banking experience.
          </p>
        </Carousel.Caption>
      </Carousel.Item>
    </Carousel>
      </div>
    )
  }
}
