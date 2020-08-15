import React from 'react';
import { Form, Input, InputNumber, Button } from 'antd';
import './AddProduct.scss'
import ProductService from '../../service/ProductService'


class AddProduct extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
  }

  render() {
    const layout = {
      labelCol: { offset:5, span: 4 },
      wrapperCol: { span: 6 },
    };
    const tailLayout = {
      wrapperCol: { offset: 10, span: 5 },
    };
    return (
        <Form
            {...layout}
            name="basic"
            initialValues={{
              remember: true,
            }}
            onFinish={this.onFinish.bind(this)}
            className="add-form"
        >
          <Form.Item
              label="商品名称"
              name="name"
              rules={[
                {
                  required: true,
                  message: '请填写商品名称',
                },
              ]}
          >
            <Input />
          </Form.Item>

          <Form.Item
              label="单价"
              name="price"
              rules={[
                {
                  required: true,
                  message: 'Please input your password!',
                },
              ]}
          >
            <InputNumber min={1} max={9999}/>
          </Form.Item>

          <Form.Item {...tailLayout}>
            <Button type="primary" htmlType="submit">
              添加
            </Button>
          </Form.Item>
        </Form>
    );
  }

  onFinish (values) {
    this.addNewProduct(values);
  }

  addNewProduct(values) {
    ProductService.addProduct(values)
        .then(() => {
          console.log('success');
        },  (e) => {
          console.error(e);
        })
  }
}

export default AddProduct;
