import React from 'react';
import{ NavLink } from "react-router-dom";
import './Header.scss'

class Header extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    return (
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
    );
  }
}

Header.propTypes = {};

export default Header;
