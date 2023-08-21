import React, { Component } from 'react'
import './App.css';
import Demo2 from './Demo2';
export default class Demo extends Component {
  constructor(){
    super();
    this.state = {
      name:"a",
      name2:"a",
      age:12 }
    }
  changeText = (event)=>{
    this.setState({name:event.target.value})
  }
  click = (event)=>{
    this.setState({name2:event.target.name})
  }
  render() {
    
    return (
      
      <React.Fragment>
        <input type = "text" value={this.state.name} placeholder='Enter the Name' onChange={this.changeText}/>
        <input type = "button" name={this.state.name} value="Submit" onClick={this.click}/>
       
        <div>
        Dynamic Name: {this.state.name}<br></br>
        On click Name: {this.state.name2}
        </div>
        <Demo2 name ={this.state.name} country="India">
          this is the text written in demo, that is sent to demo2.
        </Demo2>
       </React.Fragment>
       
    )
  }
}
