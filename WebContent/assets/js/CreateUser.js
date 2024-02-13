(function () {
  'use strict';

  const elName = document.querySelector('#name');
  const email = document.querySelector("#email"); // IDセレクタを使用
  const password = document.querySelector("#password"); // IDセレクタを使用
  const login = document.querySelector('#button');


  login.addEventListener('click', (e) => {
    e.preventDefault(); // デフォルトのフォーム送信動作を防止
    sendData();
  });

  function transition() {
    console.log('sessionStorage : ' + sessionStorage.getItem('user_id'));
    location.href = `/claft-lether/index.html`;
  }

  function sendData() {
    const xhr = new XMLHttpRequest();
    const data = {
      name : elName.value,
      email : email.value,
      password : password.value
    };
    // console.log(data);
    xhr.open('POST', '/claft-lether/CreateUser', true);
    xhr.setRequestHeader('content-type', 'application/json;charset=UTF-8');
    xhr.send(JSON.stringify(data));

    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4) {
        if (xhr.status === 200) {
          // console.log(xhr.responseText);
          const response = JSON.parse(xhr.responseText);
          if (response >= 0) {
            console.log('user_id : ' + response);
            sessionStorage.setItem('user_id', response);
            transition();
          } else {
            console.log('登録できないお');
          }
        } else {
          console.error('通信エラーが発生しました。');
        }
      }
    };
  }

})();