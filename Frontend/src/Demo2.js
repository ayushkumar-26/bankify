import React, { Component } from 'react'

export default class Demo2 extends Component {
  constructor(props){
    super(props);
  } 
  render() {
    return (
      <div>
        Name:{this.props.name}<br/>
        Country:{this.props.country}<br/>
        {this.props.children}
      </div>
    )
  }
}
