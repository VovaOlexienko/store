// popup events
const popupBg = document.querySelector('.popup__bg');
const closePopupButton = document.querySelector('.close-popup');
const totalPrice = document.querySelector('.total-price');
const btnRegistration = document.querySelector('.btn__registration');
const registModal = document.querySelector('.popup_calc_end');

btnRegistration.classList.add('disable');

const cart = document.querySelector('.cart');
cart.addEventListener('click', (e) => {
  e.preventDefault();
  popupBg.classList.add('active'); // Добавляем класс 'active' для фона
  document.body.style.overflow = 'hidden';
  if (cartProducts.length) {
    cartProducts.map(({ image, description, price, id, quantity }) => {
      new CartProducts(image, description, price, id, quantity).render();
    });
    btnRegistration.classList.remove('disable');
    showModalRegist();
    showTotalPrice();
    counterPriceMinus();
    counterPricePlus();
    clickRemove();
  }
});

closePopupButton.addEventListener('click', () => {
  popupBg.classList.remove('active'); // Добавляем класс 'active' для фона
  document.body.style.overflow = '';
  productCartWrapper.innerHTML = '';
});

// calculcate total price
const calculcateTotalPrice = () => {
  return cartProducts.reduce((acc, el) => {
    return acc + el.price;
  }, 0);
};
const showTotalPrice = () => {
  totalPrice.innerText = `${calculcateTotalPrice()}₴`;
};

// Remove item from cart oClick close button
const clickRemove = () => {
  document.querySelectorAll('.basket-delete').forEach((item, index) => {
    item.addEventListener('click', () => {
      minusCounterCart();
      enableBtn(cartProducts[index].id);
      cartProducts[index].price =
        cartProducts[index].price / cartProducts[index].quantity;
      cartProducts[index].quantity = 1;
      productCartWrapper.innerHTML = ''; // clear|update cart
      const updatedArray = cartProducts.filter((_, i) => i !== index); // create upadated array after click on close btn
      cartProducts.splice(0, cartProducts.length, ...updatedArray); // update cartProducts array
      cartProducts.map(({ image, description, price, id, quantity }) => {
        // render array
        new CartProducts(image, description, price, id, quantity).render();
      });
      if (!cartProducts.length) btnRegistration.classList.add('disable');
      showTotalPrice();
      counterPricePlus();
      counterPriceMinus();
      clickRemove();
      console.log(cartProducts);
    });
  });
};

const counterPricePlus = () => {
  document.querySelectorAll('.cart_plus').forEach((item, index) => {
    if (cartProducts[index].quantity < 5) {
      item.addEventListener('click', () => {
        cartProducts[index].price =
          cartProducts[index].price +
          cartProducts[index].price / cartProducts[index].quantity; // 1500= 1500 + 1500*2
        cartProducts[index].quantity++;
        productCartWrapper.innerHTML = '';
        cartProducts.map(({ image, description, price, id, quantity }) => {
          // render array
          new CartProducts(image, description, price, id, quantity).render();
        });
        showTotalPrice();
        clickRemove();
        counterPriceMinus();
        counterPricePlus();
      });
    }
  });
};

const counterPriceMinus = () => {
  document.querySelectorAll('.cart_minus').forEach((item, index) => {
    if (cartProducts[index].quantity >= 2) {
      item.addEventListener('click', () => {
        cartProducts[index].price =
          cartProducts[index].price -
          cartProducts[index].price / cartProducts[index].quantity;
        cartProducts[index].quantity--;
        productCartWrapper.innerHTML = '';
        cartProducts.map(({ image, description, price, id, quantity }) => {
          // render array
          new CartProducts(image, description, price, id, quantity).render();
        });
        showTotalPrice();
        clickRemove();
        counterPricePlus();
        counterPriceMinus();
      });
    }
  });
};

// CART
//
//

const cartCount = document.querySelector('.cart__counter');
let count = 0;
cartCount.innerText = count;

export const counterCart = () => {
  cartCount.innerText = ++count;
};
const minusCounterCart = () => {
  cartCount.innerText = --count;
};

const cartProducts = [];

export const recordCartProducts = (index, array) => {
  cartProducts.push(array[index]);
  console.log(cartProducts);
};

const productCartWrapper = document.querySelector('.product-cart__wrapper');

class CartProducts {
  constructor(image, description, price, id, quantity) {
    this.image = image;
    this.description = description;
    this.price = price;
    this.id = id;
    this.quantity = quantity;
  }

  render() {
    const div = document.createElement('div');

    div.classList.add('product-cart');

    div.innerHTML = `
      <div class="product-cart_block">
      <img src=${this.image} alt="image-product" class="image-cart" />
      <div class="description-cart"><p>${this.description}</p></div>
      </div>
      <div class="cart-price_block">
        <div class="cart-counter_block">
          <a class="cart_minus">-</a> 
          <div class="cart_num">${this.quantity}</div>
          <a class="cart_plus">+</a>
        </div>
        <p class="price-cart">${this.price}₴</p>
        <img src='img/close.png' alt="image-product" class="basket-delete" />
        
      </div>
    `;
    productCartWrapper.appendChild(div);
  }
}

export const disableBtn = (item, index) => {
  item.classList.add('disable');
  document.querySelectorAll('.cart__status')[index].classList.remove('disable');
};

const enableBtn = (id) => {
  document.querySelectorAll('.btn_cart')[id].classList.remove('disable');
  document.querySelectorAll('.cart__status')[id].classList.add('disable');
};

// Registration modal

const showModalRegist = () => {
  if (!btnRegistration.classList.contains('disable')) {
    btnRegistration.addEventListener('click', () => {
      registModal.classList.add('active');
      popupBg.classList.remove('active');
      productCartWrapper.innerHTML = '';
    });
    closeModalRegist();
    handleBtnRegistr();
  }
};
const closeModalRegist = () => {
  document
    .querySelector('.popup_calc_end_close')
    .addEventListener('click', () => {
      registModal.classList.remove('active');
    });
};
const sendData = async (url, data) => {
  const response = await fetch(url, {
    method: 'POST',
    body: data,
    headers: {
      'Content-Type': 'application/json'
    }
  });
  if (!response.ok) {
    throw new Error(`Ошибка по адресу${url}, статус ошибки${response}!`);
  }
  return await response.json();
};

const handleBtnRegistr = () => {
  document.querySelector('.btn-block').addEventListener('click', (e) => {
    e.preventDefault();
    const check = {
      products: [...cartProducts],
      totalPrice: calculcateTotalPrice(),
      clientInfo: {
        name: document.querySelector('.check_name').value,
        surname: document.querySelector('.check_surname').value,
        email: document.querySelector('.check_email').value,
        phone: document.querySelector('.check_phone').value,
      },
    };
    const checkJSON = JSON.stringify(check);
    console.log(checkJSON);
    sendData('http://localhost/store/order', checkJSON);
    document.querySelector('.popup_form').classList.add('disable');
    document.querySelector('.popup_calc_end_close').classList.add('disable');
    document.querySelector('.order_done').classList.add('active');
  });
};
