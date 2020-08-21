const url = 'http://localhost:8080/ts';
const getPostOptions = (data) => {
  const dataStr = JSON.stringify(data);
  return {
    method: 'POST',
    body: dataStr,
    cache: 'no-cache',
    headers: {
      'content-type': 'application/json'
    }
  }
}

export default {
  async addProduct(product) {
    const data = JSON.stringify(product);
    return await fetch(`${url}/product`, getPostOptions(data)).then(res => res.json());
  },

  async getProduct() {
    return await fetch(`${url}/product`).then(res => res.json());
  },

  async buy(cart) {
    return await fetch(`${url}/buy/product`, getPostOptions(cart)).then(res => res.json());
  },

  async getOrders() {
    return await fetch(`${url}/orders`).then(res => res.json());
  }
}
