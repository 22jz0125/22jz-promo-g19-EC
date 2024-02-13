(function(){
    'use strict';

    const user_id = sessionStorage.getItem('user_id');
    const elList = document.querySelector('#orderlist');

    if(user_id == null) {
        location.href = '/claft-lether/EC_Login';
    }

    function decision_Delivary_status(delivary_number) {
        if (delivary_number == 0) {
            return delivary_number
        }
        else {
            return delivary_number - 1
        }
    }

    function delivary(delivary_number) {
        let status = decision_Delivary_status(delivary_number);
        if(status == 0) {
            return '発送準備中'
        }
        else if(status == 1) {
            return '発送済み'
        }
        else if(status == 2) {
            return '到着'
        }
        else {
            return '確認中'
        }
    }

    fetch(`/claft-lether/OrderList?user_id=${user_id}`)
    .then(res => res.json())
    .then(data => {
        console.log(data);
        for (const oneData of data) {
            let delivary_status = delivary(oneData.delivery_status);

            let li = `
                <li>
                    <a href="/claft-lether/order_detail?id=${oneData.item.id}">
                        <figure>
                            <img src="${oneData.item.imageURL}" alt="bag">
                        </figure>
                        <div>
                            <h3>${oneData.item.name}</h3>
                            <p>ご購入日   : ${oneData.created_at}</p>
                            <p>現在の状態 : ${delivary_status}</p>
                        </div>
                    </a>
                </li>
            `;
            elList.innerHTML += li;
        }
    });

})();