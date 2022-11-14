import {counterCart, recordCartProducts, disableBtn} from './cart.js';
const API_PRODUCT = 'http://localhost/store/product';
const search = document.querySelector('.search-txt');
const searchBtn = document.querySelector('.search-btn');

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
  constructor(image, description, price, id, index) {
    this.image = image;
    this.description = description;
    this.price = price;
    this.id = id;
    this.quantity = 1;
    this.index = index;
  }

  render() {
    allClassObjects[this.index] = {
      id: this.id,
      image: this.image,
      description: this.description,
      price: this.price,
      quantity: this.quantity,
      index: this.index,
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

async function resultOfRenderingCards(url) {
  await getProduct(url).then((data) => {
    data.map(({ image, description, price, id }, index) => {
      new Cards(image, description, price, id, index).render();
    });
    const btns = document.querySelectorAll('.btn_cart');
    console.log(allClassObjects);
    btns.forEach((item, i) => {
      item.addEventListener('click', () => {
        counterCart();
        recordCartProducts(i, allClassObjects);
        disableBtn(item, i);
      });
    });
  });
}

resultOfRenderingCards(API_PRODUCT);

const sendSeacrhRequest = async (url, data) => {
  const response = await fetch(url, {
    method: 'POST',
    body: data,
    headers: {
      'Content-Type': 'application/json',
    },
  });
  if (!response.ok) {
    throw new Error(`Ошибка по адресу${url}, статус ошибки${response}!`);
  }
  return await response.json();
};

const hadleSearch = (e) => {
  e.preventDefault();
  const searchData = {
    searchOptions: `${search.value}`,
  };
  sendSeacrhRequest('http://localhost/store/search', JSON.stringify(searchData)).then(
      (data) => {
        data.map(({ image, description, price, id }, index) => {
          new Cards(image, description, price, id, index).render();
        });
        const btns = document.querySelectorAll('.btn_cart');
        console.log(allClassObjects);
        btns.forEach((item, i) => {
          item.addEventListener('click', () => {
            counterCart();
            recordCartProducts(i, allClassObjects);
            disableBtn(item, i);
          });
        });
      }
  );
  search.value = '';
  cardWrapper.innerHTML = '';
};
searchBtn.addEventListener('click', hadleSearch);

document.querySelector('.name').addEventListener('click', () => {
  location.reload();
});
