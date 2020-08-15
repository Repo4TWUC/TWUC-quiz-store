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

class App extends React.Component {

  render() {
    return (
        <div className="App">
          <Router className="app">
            <Header />
            <Switch>
              <Route exact path="/" component={Store}/>
              <Route exact path="/order" component={Order}/>
              <Route path="/add" component={AddProduct} />
              <Route path="*" component={Store} />
            </Switch>
          </Router>
        </div>
    );
  }
}

export default App;
