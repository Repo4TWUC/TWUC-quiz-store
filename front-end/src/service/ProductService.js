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
    return await fetch(`${url}/product`, getPostOptions(product)).then(res => res.json());
  },

  async getProduct() {
    return await fetch(`${url}/product`).then(res => res.json());
  },

  async buy(cart) {
    return await fetch(`${url}/order`, getPostOptions(cart)).then(res => res.json());
  },

  async getOrders() {
    return await fetch(`${url}/order`).then(res => res.json());
  }
}
