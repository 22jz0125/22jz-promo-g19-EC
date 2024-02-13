(function () {
  'use strict';
  let url = new URL(window.location.href);
  let params = url.searchParams;
  const id = params.get('id');

  const elorderPurchaseDate = document.querySelector('#order_purchase_date');
  const elName = document.querySelector('#order_item_name');
  const elPrice = document.querySelector('#order_item_price');
  const elCount = document.querySelector('#order_item_count');
  const elSum = document.querySelector('#order_sum');
  const elPostage = document.querySelector('#order_postage');
  const elAllSum = document.querySelector('#order_all_sum');
  const user_id = sessionStorage.getItem('user_id');
  const elDelivary_status = document.querySelectorAll('.del');

  console.log("item_id : " + id);
  console.log("user_id : " + user_id);
  // console.log('del     : ' + delivary_status[0].textContent);

  if (user_id == null) {
    location.href = '/claft-lether/EC_Login';
  }

  function decision_Delivary_status(delivary_number) {
    if(delivary_number == 0) {
      return delivary_number
    }
    else {
      return delivary_number - 1
    }
  }

  fetch(`/claft-lether/OrderList?item_id=${id}&user_id=${user_id}`)
    .then(res => res.json())
    .then(data => {
      console.log(data);
      // console.log(data[0].created_at.getFullYear());
      // console.log(elDelivary_status[decision_Delivary_status(data[0].delivery_status)].textContent);
      elorderPurchaseDate.textContent = data[0].created_at;
      elName.textContent = data[0].item.name;
      elCount.textContent = data[0].count + '個';
      elPrice.textContent = data[0].item.price.toLocaleString() + '円';
      elSum.textContent = (data[0].item.price * data[0].count).toLocaleString() + '円';
      elPostage.textContent = '400円';
      elAllSum.textContent = (data[0].item.price * data[0].count + 400).toLocaleString() + '円'
      elDelivary_status[decision_Delivary_status(data[0].delivery_status)].classList.add("state");
    });
})();