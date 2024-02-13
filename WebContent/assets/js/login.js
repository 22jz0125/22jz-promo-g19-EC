(function () {
  'use strict';

  const email = document.querySelector("#e-mail"); // IDセレクタを使用
  const password = document.querySelector("#password"); // IDセレクタを使用
  const login = document.querySelector('#button');
  console.log(email);

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
      email: email.value,
      password: password.value
    };
    // console.log(data);
    xhr.open('POST', '/claft-lether/Login', true);
    xhr.setRequestHeader('content-type', 'application/json;charset=UTF-8');
    xhr.send(JSON.stringify(data));

    xhr.onreadystatechange = function () {
      if (xhr.readyState === 4) {
        if (xhr.status === 200) {
          const responseText = xhr.responseText;
          console.log('サーバーレスポンス: ', responseText);
          const response = JSON.parse(xhr.responseText);
          console.log(response);
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

  function buttonClick(response) {
    // レスポンスを処理するロジックを追加
    // 例えば、成功した場合は別のページにリダイレクトするなど
    if (response === true) {
      transition();
    } else {
      console.log('ログインに失敗しました');
    }
  }
})();