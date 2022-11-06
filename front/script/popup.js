const popupBg = document.querySelector('.popup__bg');
const closePopupButton = document.querySelector('.close-popup');

const cart = document.querySelector('.cart');
cart.addEventListener('click', (e) => {
  e.preventDefault();
  popupBg.classList.add('active'); // Добавляем класс 'active' для фона
  document.body.style.overflow = 'hidden';
});

closePopupButton.addEventListener('click', () => {
  popupBg.classList.remove('active'); // Добавляем класс 'active' для фона
  document.body.style.overflow = '';
});