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
        dataIndex: '',
        key: 'del',
        render: () => {
            return (<Button type="primary" size={"small"}>Buy Now</Button>)
        }
      },
    ];
    const data = [];
    return (<Table columns={columns} dataSource={data} bordered pagination={false} />);
  }
}

export default Order;
