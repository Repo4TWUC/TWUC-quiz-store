import React from 'react';
import SingleGood from "./SingleGood";

class GoodsList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { goodsList, onAddToCart } = this.props;
    return goodsList.map((key, index) => {
      return (
        <SingleGood onAddToCart={onAddToCart} {...goodsList} key={index} />
      );
    });
  }
}

export default GoodsList;
