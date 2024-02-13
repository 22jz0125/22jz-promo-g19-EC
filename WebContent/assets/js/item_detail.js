(function () {
  'use strict';
  let url = new URL(window.location.href);
  let params = url.searchParams;
  const id = params.get('id');
  let maxcount = null;

  const elImg = document.querySelector('#item_img');
  const elName = document.querySelector('#item_name');
  const elCategory = document.querySelector('#item_category_name');
  const elNamePan = document.querySelector('#item_name_pan');
  const elDown = document.querySelector('#down_btn');
  const elUp = document.querySelector('#up_btn');
  const elCount = document.querySelector('#count');
  const elPrice = document.querySelector('#item_price');
  const elStock = document.querySelector('#item_stock');
  const elCart = document.querySelector('#item_cart');
  const elHeight = document.querySelector('#item_height');
  const elWidth = document.querySelector('#item_width');
  const elWeight = document.querySelector('#item_weight');
  const elMaterial = document.querySelector('#item_material');
  const elDescription = document.querySelector('#item_description');
  let stock;

  console.log(elImg);

  function transition() {
    const user_id = sessionStorage.getItem('user_id');
    if (user_id != null) {
      let count = document.getElementById('count').value;
      if(count > maxcount) {
        count = maxcount;
      }
      else if(count < 0) {
        count = 0;
      }
      location.href = `../purchase_info/index.html?id=${id}&count=${count}`;
    }
    else {
      location.href = '/claft-lether/EC_Login';
    }
  }

  function decitionCategory_id(caName) {
    if (caName == 'バッグ') {
      return 1;
    }
    else if (caName == '財布') {
      return 2
    }
    else if (caName == 'スマホケース') {
      return 3
    }
    else if (caName == '小物') {
      return 4
    }
    else if (caName == '衣類') {
      return 5
    }
    else if (caName == 'ベルト') {
      return 6
    }
    else {
      return 0;
    }
  }

  elDown.addEventListener('click', (e) => {
    if (count.value > 1) {
      count.value--;
    }
  });

  elUp.addEventListener('click', (e) => {
    if (count.value < stock)
      count.value++;
  });

  window.addEventListener('load', () => {
    fetch(`/claft-lether/ItemList?id=${id}`)
      .then(res => res.json())
      .then(data => {
        console.log(data);
        console.log(data[0].name);
        const cId = '../item_list/?category_id=' + decitionCategory_id(data[0].category_name);
        elImg.setAttribute('src', data[0].imageURL);
        elName.textContent = data[0].name;
        elNamePan.textContent = data[0].name;
        elCategory.textContent = data[0].category_name;
        elCategory.setAttribute('href', cId);
        elPrice.textContent = data[0].price.toLocaleString();
        elStock.textContent = data[0].stock.toLocaleString() + '個';
        elCount.max = data[0].stock;
        maxcount = data[0].stock;
        elHeight.textContent = data[0].height_size + 'cm';
        elWidth.textContent = data[0].width_size + 'cm';
        elWeight.textContent = data[0].weight + 'g';
        elMaterial.textContent = data[0].material;
        elDescription.textContent = data[0].description;
        stock = data[0].stock;
      });
  });

  elCart.onclick = transition;
})();