import React from 'react';
import {
  BrowserRouter as Router,
  Route,
  NavLink,
  Switch
} from "react-router-dom";
import Header from './components/Header/Header';
import Store from './components/Store';
import Order from './components/Order/Order';
import AddProduct from './components/AddProduct/AddProduct';

class App extends React.Component {

  render() {
    return (
        <div className="App">
          <Header />
          <Router className="app">
            <header className="page-header">
              <NavLink
                  exact
                  to="/"
                  activeClassName="active"
              >商城</NavLink>
              <NavLink
                  to="/order"
                  activeClassName="active"
              >订单</NavLink>
              <NavLink
                  to="/add"
                  activeClassName="active"
              >添加商品</NavLink>
            </header>
            <Switch>
              <Route exact path="/" component={Store}/>
              <Route exact path="/products" component={Order}/>
              <Route path="/products/:id" component={AddProduct} />
              <Route path="*" component={Store} />
            </Switch>
          </Router>
        </div>
    );
  }
}

export default App;
