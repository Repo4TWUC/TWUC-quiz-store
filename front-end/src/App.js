import React from 'react';
import {
  BrowserRouter as Router,
  Route,
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
          <Router className="app">
            <Header />
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
