import React from 'react';
import {Button, Affix, Dropdown, Table} from "antd";
import { ShoppingCartOutlined } from '@ant-design/icons';
import './ShopCart.scss'

class ShopCart extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const { cart, goodsList, cartCount } = this.props;
    const cartDetail = cart.length ? this.mapCartToEle(cart, goodsList) : (<span>There is nothing</span>);
    return (
        <Affix style={{ position: 'absolute', bottom: '5vh', right: '5vw'}}>
          <Dropdown overlay={cartDetail} placement={"topRight"}>
            <Button type={"primary"} style={{backgroundColor: 'blue'}} shape={"circle"} size={"large"}>
              <ShoppingCartOutlined />
              {cartCount}
            </Button>
          </Dropdown>
        </Affix>
    );
  }

  mapCartToEle(cart, goodsList) {
    const {onClear, onBuy} = this.props;
    const columns = [
      { title: '商品', dataIndex: 'name', key: 'name', width: 100 },
      { title: '数量', dataIndex: 'count', key: 'count', width: 100 },
      {
        title: '',
        dataIndex: '',
        key: 'buy',
        render: (_, $, index) => {
          if (index === cart.length - 1)
            return (<Button type="primary" onClick={onBuy} size={"small"}>Buy Now</Button>)
          return <span/>
        }
      },
      {
        title: '',
        dataIndex: '',
        key: 'clear',
        render: (_, $, index) => {
          if (index === cart.length - 1)
            return ( <Button type="primary" onClick={onClear} size={"small"}>Clear</Button> )
          return <span />
        }
      },
    ];
    const data = cart.map(itm => {
      const curGood = goodsList.find(good => good.id === itm.productId)
      return {
        name: curGood.name,
        count: itm.count,
      }
    });

    return ( <Table columns={columns} dataSource={data} style={{padding: 20}} bordered pagination={false} /> )
  }
}

export default ShopCart;
