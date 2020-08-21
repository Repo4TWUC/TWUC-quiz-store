import React from 'react';
import productImg from '../../assets/product_placeholder.png';
import './SingleGood.scss';

class SingleGood extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { name, price, onAddToCart, id } = this.props;
    return (
      <li>
        <img src={productImg} className="image-size" alt="good-img" />
        <span className="name">{name}</span>
        <div className="foot">
          <span>{price}元/瓶</span>
          <button className="add-cart-btn" onClick={() => {
            onAddToCart(id)
          }}>
            +
          </button>
        </div>
      </li>
    );
  }
}

export default SingleGood;
