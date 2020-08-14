import React from 'react';
import GoodsList from "./GoodsList/GoodsList";

class Store extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      count: 0,
      goodsList: []
    };
  }

  render() {
    return (
        <div>
          <GoodsList
              goodsList={this.state.goodsList}
              onAddToCart={this.handleAddToCart.bind(this)}
          />
        </div>
    );
  }

  componentDidMount() {
    fetch('http://localhost:8080/ts/product')
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error('fetch products failed');
          }
        })
        .then((json) => {
          this.setState({
            count: 0,
            goodsList: json,
          });
        })
        .catch((e) => {
          console.error(e);
        });
  }

  handleAddToCart() {
    this.setState(
        Object.assign({}, this.state, {
          count: this.state.count + 1,
        })
    );
  }
}

Store.propTypes = {};

export default Store;
