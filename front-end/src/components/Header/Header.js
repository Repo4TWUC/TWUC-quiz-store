import React from 'react';
import { NavLink } from "react-router-dom";
import './Header.scss'
import 'antd/dist/antd.css'

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
        <header className="page-header">
          <nav>
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
          </nav>
        </header>
    );
  }
}

export default Header;
