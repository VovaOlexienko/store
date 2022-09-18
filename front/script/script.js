const API_PRODUCT = 'data/products.json';

/* const img = document.querySelector('.image-product');
const description = document.querySelector('.description-product');
const price = document.querySelector('.price-product'); */

const getProduct = async (url) => {
  const response = await fetch(url);
  if (!response.ok) {
    const message = `An error has occured: ${response.status}`;
    throw new Error(message);
  }
  return await response.json();
};

getProduct(API_PRODUCT).then((data) => {
  img.src = data[0].img;
  description.innerText = data[0].description;
  price.innerText = `${data[0].price}₴`;
});

// NEW VERSION CREATES CARDS FOR CLASS
const cardWrapper = document.querySelector('.cards__wrapper');

class Cards {
  constructor(image, description, price) {
    this.image = image;
    this.description = description;
    this.price = price;
  }
}
