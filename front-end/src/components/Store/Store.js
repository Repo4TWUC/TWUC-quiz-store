import React from 'react';
import GoodsList from "./GoodsList";
import './Store.scss';

class Store extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
    };
  }

  render() {
    const {onAddToCart, goodsList} = this.props;
    return (
        <div className={"store"}>
          <GoodsList
              goodsList={goodsList}
              onAddToCart={ onAddToCart }
          />
        </div>
    );
  }
}

Store.propTypes = {};

export default Store;
