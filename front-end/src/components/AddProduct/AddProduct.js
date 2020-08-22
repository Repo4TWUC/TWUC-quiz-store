import React from 'react';
import {Form, Input, InputNumber, Button, Select, notification} from 'antd';
import './AddProduct.scss'
import ProductService from '../../service/ProductService'


class AddProduct extends React.Component {
  constructor(props) {
    super(props);
    this.state = {};
    this.formRef = React.createRef();
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
            ref={this.formRef}
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
                  message: '请输入价格',
                },
              ]}
          >
            <InputNumber min={1} max={9999}/>
          </Form.Item>

          <Form.Item
              label="单位"
              name="unit"
              rules={[
                {
                  required: true,
                  message: '请选择单位',
                },
              ]}
          >
            <Select style={{ width: 120 }}>
              <Select.Option value="瓶">瓶</Select.Option>
              <Select.Option value="袋">袋</Select.Option>
            </Select>
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
    this.addNewProduct(values)
        .then(() => {
          notification['success']({
            message: 'Success',
            description: '添加商品成功'
          })
        })
        .catch(() => {
          notification['error']({
            message: 'Error',
            description: '添加商品失败，请重试'
          })
        })
  }

  addNewProduct(values) {
   return ProductService.addProduct(values)
        .then(data => {
          this.formRef.current.resetFields();
          this.props.setProducts(data);
          return Promise.resolve();
        })
        .catch(e => {
          console.error(e);
          return Promise.reject();
        })
  }
}

export default AddProduct;
