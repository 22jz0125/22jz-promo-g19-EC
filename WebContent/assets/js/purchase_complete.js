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
    const elGo = document.querySelector('#go_top');
    let sum = 0;
    let all = 0;
    let postage = 400;  //送料（一律予定）

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

    function buttonClick(){
        location.href = `../index.html`;
    }

    elGo.onclick = buttonClick;
})();