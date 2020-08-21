import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  Switch
} from "react-router-dom";
import Header from './components/Header/Header';
import Store from './components/Store/Store';
import Order from './components/Order/Order';
import AddProduct from './components/AddProduct/AddProduct';
import _ from "lodash";
import ProductService from "./service/ProductService";
import {Button, Affix, Dropdown, Table} from "antd";
import { ShoppingCartOutlined } from '@ant-design/icons';

class App extends React.Component {
  constructor() {
    super();
    this.state = {
      cartCount: 0,
      cart: [],
      goodsList: []
    }
  }

  render() {
    const { cart, goodsList, cartCount } = this.state;
    const cartDetail = cart.length ? this.mapCartToEle(cart, goodsList) : (<span>There is nothing</span>);
    return (
        <div className="App">
          <Router className="app">
            <Header cart={this.state.cart} goodsList={this.state.goodsList} cartCount={this.state.cartCount} />
            <Switch>
              <Route exact path="/" >
                <Store goodsList={this.state.goodsList} onAddToCart={this.onAddToCart.bind(this)} />
              </Route>
              <Route exact path="/order" component={Order}/>
              <Route path="/add" component={AddProduct} />
              <Route path="*" component={Store} />
            </Switch>
            <Affix style={{ position: 'absolute', bottom: '5vh', right: '5vw' }}>
              <Dropdown overlay={cartDetail} placement={"topRight"}>
                <Button type={"primary"} style={{backgroundColor: 'blue'}} shape={"circle"} size={"large"}>
                  <ShoppingCartOutlined />
                  {cartCount}
                </Button>
              </Dropdown>
            </Affix>,
          </Router>
        </div>
    );
  }

  mapCartToEle(cart, goodsList) {
    const columns = [
      { title: 'Name', dataIndex: 'name', key: 'name' },
      { title: 'Count', dataIndex: 'count', key: 'count' },
      {
        title: '',
        dataIndex: '',
        key: 'buy',
        render: (_, $, index) => {
          if (index === cart.length - 1)
            return (<Button type="primary" size={"small"}>Buy
              Now</Button>)
          return <span/>
        }
      },
      {
        title: '',
        dataIndex: '',
        key: 'clear',
        render: (_, $, index) => {
          if (index === cart.length - 1)
            return ( <Button type="primary" size={"small"}>Clear</Button> )
          return <span />
        }
      },
    ];
    const data = cart.map(itm => {
      const curGood = goodsList.find(good => good.id === itm.id)
      return {
        name: curGood.name,
        count: itm.count,
      }
    });

    return ( <Table columns={columns} dataSource={data} bordered pagination={false} /> )
  }

  onAddToCart (productId) {
    const newCart = _.cloneDeep(this.state.cart);
    let curProduct = newCart.find(itm => itm.id === productId);

    if (!curProduct) {
      curProduct = {id: productId, count: 0};
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
}

export default App;
