const url = 'http://localhost:8080/ts';

export default {
  async addProduct(product) {
    const data = JSON.stringify(product);
    const options = {
      method: 'POST',
      body: data,
      cache: 'no-cache',
      headers: {
        'content-type': 'application/json'
      }
    }
    return await fetch(`${url}/product`, options).then(res => res.json());
  }
}
