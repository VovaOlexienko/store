import { counterCart } from './cart.js';
import { disableBtn } from './cart.js';
import { recordCartProducts } from './cart.js';
const API_PRODUCT = 'http://localhost/store/product';

const getProduct = async (url) => {
  const response = await fetch(url);
  if (!response.ok) {
    const message = `An error has occured: ${response.status}`;
    throw new Error(message);
  }
  return await response.json();
};

// NEW VERSION CREATES CARDS FOR CLASS
const cardWrapper = document.querySelector('.cards__wrapper');
const allClassObjects = [];

class Cards {
  constructor(image, description, price, id) {
    this.image = image;
    this.description = description;
    this.price = price;
    this.id = id;
    this.quantity = 1;
  }

  render() {
    allClassObjects[this.id] = {
      id: this.id,
      image: this.image,
      description: this.description,
      price: this.price,
      quantity: this.quantity,
    };
    const div = document.createElement('div');

    div.classList.add('card');

    div.innerHTML = `
      <img src=${this.image} alt="image-product" class="image-product" />
      <p class="description-product">${this.description}</p>
      <p class="price-product">${this.price}₴</p>
      <button class="btn_cart">add to cart</button>
      <span class="cart__status disable">in cart</span>
    `;
    cardWrapper.appendChild(div);
  }
}

async function resultOfRenderingCards() {
  await getProduct(API_PRODUCT).then((data) => {
    data.map(({ image, description, price, id }) => {
      new Cards(image, description, price, id).render();
    });
    const btns = document.querySelectorAll('.btn_cart');
    btns.forEach((item, i) => {
      item.addEventListener('click', () => {
        counterCart();
        recordCartProducts(i, allClassObjects);
        disableBtn(item, i);
      });
    });
  });
}

resultOfRenderingCards();
