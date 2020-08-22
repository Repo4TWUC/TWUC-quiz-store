import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Switch
} from "react-router-dom";
import _ from "lodash";

import Header from './components/Header/Header';
import Store from './components/Store/Store';
import ShopCart from './components/ShopCart/ShopCart';
import Order from './components/Order/Order';
import AddProduct from './components/AddProduct/AddProduct';
import ProductService from "./service/ProductService";

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      cartCount: 0,
      cart: [],
      goodsList: [],
      orderList: []
    }
  }

  render() {
    const { cart, goodsList, cartCount } = this.state;
    return (
        <div className="App">
          <Router className="app">
            <Header cart={this.state.cart} goodsList={this.state.goodsList} cartCount={this.state.cartCount} />
            <Switch>
              <Route exact path="/" >
                <Store goodsList={this.state.goodsList} onAddToCart={this.onAddToCart.bind(this)} />
              </Route>
              <Route exact path="/order">
                <Order orderList={this.state.orderList}/>
              </Route>
              <Route path="/add" >
                <AddProduct setProducts={this.setGoodsList.bind(this)} />
              </Route>
              <Route path="*" component={Store} />
            </Switch>
            <ShopCart {...{cart, cartCount, goodsList}} onClear={this.clearCart.bind(this)} onBuy={this.buyAll.bind(this)} />
          </Router>
        </div>
    );
  }

  clearCart() {
    this.setState({
      cart: [],
      cartCount: 0
    })
  }

  setGoodsList(goodsList) {
    this.setState({
      goodsList: goodsList
    })
  }

  buyAll() {
    const order = {
      id: 0,
      orderItems: this.state.cart
    }
    ProductService.buy(order)
        .then((order) => {
          this.setState({
            orderList: order
          })
        })
  }

  onAddToCart (productId) {
    const newCart = _.cloneDeep(this.state.cart);
    let curProduct = newCart.find(itm => itm.productId === productId);

    if (!curProduct) {
      curProduct = { productId, count: 0};
      newCart.push(curProduct)
    }

    curProduct.count ++;
    this.setState({
      cart: newCart,
      cartCount: this.state.cartCount + 1
    });
  }

  componentDidMount() {
    ProductService.getProduct()
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

  getOrderList() {
    ProductService.getOrders()
        .then(data => {
          this.setState({
            orderList: data
          })
        })
  }
}

export default App;
