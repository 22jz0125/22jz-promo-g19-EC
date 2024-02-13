(function () {
  'use strict';
  const elUserName = document.querySelector('#userName');
  const elUserEmail = document.querySelector('#userEmail');
  const elUserLogout = document.querySelector('#button')
  const user_id = sessionStorage.getItem('user_id');
  console.log(elUserName);
  console.log(elUserEmail);
  // console.log(elUserAddress);

  if(user_id == null) {
    location.href = '/claft-lether/EC_Login';
  }
  else {
    userdata();
  }

  function transition() {
    location.href = `/claft-lether/index.html`;
  }

  elUserLogout.addEventListener('click', () => {
    // fetch('/claft-lether/Logout')
    //   .then(res => res.json())
    //   .then(data => { });
    sessionStorage.removeItem('user_id');
    transition();
  });
    

  function userdata() {
    fetch(`/claft-lether/Users?user_id=${user_id}`)
    .then(res => res.json())
    .then(data => {
      console.log(data);
      elUserName.textContent = data.name;
      elUserEmail.textContent = data.email;
  });
  }



})();