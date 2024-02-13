(function(){
    'use strict';

    let elLogin = null;
    let elLogout = null;
    let elOpen = null;
    let elClose = null;
    let elBtn = null;
    let elSub = null;
    const elBody = document.querySelector('body');
    const user_id = sessionStorage.getItem('user_id');
    console.log(user_id);

    fetch('/claft-lether/footer/index.html')
    .then(response => response.text())
    .then(data =>{
        const tempElement = document.createElement('div');
        tempElement.className = 'footer_div';
        tempElement.innerHTML = data;
        const footerElement = tempElement;
        elBody.appendChild(footerElement);
    });

    fetch('/claft-lether/header/index.html')
    .then(response => response.text())
    .then(data => {
        const tempElement = document.createElement('div');
        tempElement.innerHTML = data;
        const headerElement = tempElement;
        elBody.insertBefore(headerElement, elBody.firstChild);

        elLogin = headerElement.querySelector('#login_user');
        elLogout = headerElement.querySelector('#logout_user');
        elOpen = headerElement.querySelector('.menu_open');
        elClose = headerElement.querySelector('.menu_close');
        elBtn = headerElement.querySelector('#menu_btn');
        elSub = headerElement.querySelector('.sub_menu');

        elBtn.addEventListener('click', handerClick);
        if(user_id != null) {
            loginImgChange();
        }
    });

    function handerClick(){
        elOpen.classList.toggle('active');
        elClose.classList.toggle('active'); 
        elSub.classList.toggle('active');
    };

    function loginImgChange() {
        elLogin.classList.toggle('none_img');
        elLogout.classList.toggle('none_img');
    }


})();