import React from 'react';
import {Button, Table} from 'antd';

class Order extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const columns = [
      { title: 'Name', dataIndex: 'name', key: 'name' },
      { title: 'Price', dataIndex: 'price', key: 'price' },
      { title: 'Count', dataIndex: 'count', key: 'count' },
      { title: 'Unit', dataIndex: 'unit', key: 'unit' },
      {
        title: 'Operate',
        dataIndex: 'action',
        key: 'del',
        render: (text, record) => {
          if (text === 'true')
            return (<Button type="primary" danger size={"small"} onClick={() => {
              this.props.onDelete(record.key)
            }}>Delete</Button>)
          else
            return (<span/>);
        }
      },
    ];
    const data = this.transformOrder(this.props.orderList);
    return (<Table columns={columns} dataSource={data} bordered pagination={false} />);
  }

  transformOrder(orderList) {
    return orderList.map(order => {
      return {
        key: order.id,
        name: `Order: ${order.id}`,
        children: order.orderItems.map(item => {
          const curGood = this.props.goodsList.find(good => good.id === item.productId)
          return {
            name: curGood.name,
            price: curGood.price,
            count: item.count,
            unit: curGood.unit,
            action: 'false'
          }
        }),
        action: 'true'
      }
    })
  }
}

export default Order;
