import React from 'react';
import SingleGood from "./SingleGood";

class GoodsList extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { goodsList, onAddToCart } = this.props;
    if (goodsList.length === 0) {
      return <h1>还没有任何商品，快去添加吧</h1>
    }
    return goodsList.map((item, index) => {
      return (
        <SingleGood onAddToCart={onAddToCart} {...item} key={index} />
      );
    });
  }
}

export default GoodsList;
