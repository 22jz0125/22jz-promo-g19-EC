(function(){
  'use strict';
  console.log("接続接続接続接続");

  let url = new URL(window.location.href);
  let params = url.searchParams;
  const category_id = params.get('category_id');
  const elCategory = document.querySelector('#item_category');

  const elItemList = document.querySelector('#lineup_item');
  

  
  window.addEventListener('load', () =>{
    fetch(`/claft-lether/ItemList?category_id=${category_id}`)
    .then(res => res.json())
    .then(data => {
      console.log(data);
      elCategory.textContent = data[0].category_name;
      for (const oneData of data) {
        let li = `
            <li>
                <a href="../item_detail?id=${oneData.id}">
                <figure><img src="${oneData.imageURL}" alt="バッグ1サンプル"></figure>
                <p>${oneData.name}</p>
                <p>${oneData.price.toLocaleString()}円</p>
                </a>
            </li>
		`;
        elItemList.innerHTML += li;
      }
    });
  });
})();