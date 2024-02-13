(function(){
  'use strict';

  let url = new URL(window.location.href);
  let params = url.searchParams;
  const id = params.get('id');
  const count = params.get('count');

  const elName = document.querySelector('#purchase_name');
  const elPrice = document.querySelector('#purchase_price');
  const elCount = document.querySelector('#purchase_count');
  const elSum = document.querySelector('#purchase_sum');
  const elPostage = document.querySelector('#purchase_postage');
  const elAll = document.querySelector('#purchase_sum_all');
  const elTransitionbutton = document.querySelector('#purchase_transition');
  const elNewly = document.querySelectorAll('.newly_forms');
  const elCredit = document.querySelectorAll('.credit_forms');
  let sum = 0;
  let all = 0;
  let postage = 400;  //送料（一律予定）

  function transition(){  //画面遷移用メソッド
    let delivery_flg = {};    //入力内容判定配列
    let json_delivery_flg_obj = {}; //入力内容判定配列のJSON化用
    let json_shipment_obj = {};  //新規住所の値
    let json_payment_obj = {};   //クレジットカードの値 
    let delivery = document.getElementsByName('delivery_radio');
    for(let i = 0; i < delivery.length; i++) {
      if(delivery.item(i).checked) {  
        if('発送 ' == delivery.item(i).nextElementSibling.textContent ){  //発送時処理
          console.log(delivery.item(i).nextElementSibling.textContent);
          delivery_flg.Fdelivery = true;
          let shipment = document.getElementsByName('shipment_radio');
          for(let i = 0; i < shipment.length; i++) {
            if(shipment.item(i).checked) {
              if('住所プリセットを使用する ' == shipment.item(i).nextElementSibling.textContent ){  //住所プリセット時処理
                delivery_flg.Fshipment = true;
                const shipment_obj = null;
                json_shipment_obj = JSON.stringify(shipment_obj);
                console.log(shipment.item(i).nextElementSibling.textContent);
              }
              else {  //新規住所使用時処理
                delivery_flg.Fshipment = false;
                const shipment_obj = {
                  country: elNewly[0].value,
                  lastname: elNewly[1].value,
                  firstname: elNewly[2].value,
                  postnumber: elNewly[3].value,
                  address: elNewly[4].value
                }
                json_shipment_obj = JSON.stringify(shipment_obj);
                console.log(json_shipment_obj);
              }
            }
          }
        }
        else {  //店舗受取時処理
          delivery_flg.Fdelivery = false;
          delivery_flg.Fshipment = null;
          console.log(delivery.item(i).nextElementSibling.textContent);
        }
      }
    }

    let payment = document.getElementsByName('payment_radio');
    for(let i = 0; i < payment.length; i++) {
      if(payment.item(i).checked) {
        if('クレジットカード ' == payment.item(i).nextElementSibling.textContent) {
          delivery_flg.payment = true;
          const payment_obj = {
            card_number: elCredit[0].value,
            card_holder: elCredit[1].value,
            expiration_date: elCredit[2].value,
            security_code: elCredit[3].value
          }
          json_payment_obj = JSON.stringify(payment_obj);
          console.log(payment.item(i).nextElementSibling.textContent);
        }
        else {
          delivery_flg.payment = false;
          const payment_obj = null;
          json_payment_obj = JSON.stringify(payment_obj);
          console.log(payment.item(i).nextElementSibling.textContent);
        }
      }
    }

    console.log(delivery_flg);
    console.log(json_shipment_obj);
    console.log(json_payment_obj);
    
    json_delivery_flg_obj = JSON.stringify(delivery_flg);
    sessionStorage.setItem('json_delivery_flg_obj', json_delivery_flg_obj);
    sessionStorage.setItem('json_shipment_obj', json_shipment_obj);
    sessionStorage.setItem('json_payment_obj', json_payment_obj);
    window.location.href = `../purchase_info_confirmation?count=${count}&id=${id}`;
  }

  window.addEventListener('load', () => {
    fetch(`/claft-lether/Purchase?id=${id}`)
      .then(res => res.json())
      .then(data => {
        console.log(data);
        const item_obj = JSON.stringify(data);
        sessionStorage.setItem('json_item_obj', item_obj);
        sum = data[0].price * count;
        all = sum + postage;

        elName.textContent = data[0].name;
        elCount.textContent = count + '個';
        elPrice.textContent = data[0].price.toLocaleString() + '円';
        elSum.textContent = sum.toLocaleString() + '円';
        elPostage.textContent = postage + '円';
        elAll.textContent = all.toLocaleString() + '円';
      });
  });

  elTransitionbutton.onclick = transition;
})();