(function(){
	'use strict';
	let url = new URL(window.location.href);
	let params = url.searchParams;
	const item_id = params.get('id');
	const count = params.get('count');

	const elName = document.querySelector('#purchase_name');
	const elPrice = document.querySelector('#purchase_price');
	const elCount = document.querySelector('#purchase_count');
	const elSum = document.querySelector('#purchase_sum');
	const elPostage = document.querySelector('#purchase_postage');
	const elAll = document.querySelector('#purchase_sum_all');
	const elshipment = document.querySelector('#confirmation_shipment');
	const elpayment = document.querySelector('#confirmation_payment');
	const elbtn = document.querySelector('#order_send_button');
	const json_delivery_flg_obj = sessionStorage.getItem('json_delivery_flg_obj');
	const json_shipment_obj = sessionStorage.getItem('json_shipment_obj');
	const json_payment_obj = sessionStorage.getItem('json_payment_obj');
	const json_item_obj = sessionStorage.getItem('json_item_obj');
	const user_id = sessionStorage.getItem('user_id');
	const delivery_flg = JSON.parse(json_delivery_flg_obj);
	const shipment_value = JSON.parse(json_shipment_obj);
	const payment_value = JSON.parse(json_payment_obj);
	const item_value = JSON.parse(json_item_obj);
	let sum = 0;
	let all = 0;
	let postage = 400;  //送料（一律予定）

	console.log(delivery_flg);
	console.log(shipment_value);
	console.log(payment_value);
	console.log(item_value);
	console.log(count);

	function sendData(){
		const xhr = new XMLHttpRequest();
		const data = {
			delivery_flg,
			shipment_value,
			payment_value,
			count,
			item_id,
			user_id
		};
		console.log(data);
		xhr.open('POST', '/claft-lether/Purchase', true);
		xhr.setRequestHeader('content-type', 'application/json;charset=UTF-8');
		xhr.send(JSON.stringify(data));
		xhr.onreadystatechange = function () {
			if (xhr.readyState === 4 && xhr.status === 200) {
				console.log(xhr.responseText);
				const response = JSON.parse(xhr.responseText);
                if (response === true || response === false) {
                    console.log(response + ' キターーーーーー');
                }
				else {
                    console.log('true, false以外が来た');
                }
				buttonClick(response);
            }
			else {
                console.error('通信エラーが発生しました。');
            }
		}
	}

	function buttonClick(decision){
		if(decision === true){
			location.href = `../purchase_complete/index.html?id=${item_id}&count=${count}`;
		}
		else {
			location.href = `../not_found/`;
		}
	}

	function createLi(createData) {
		let oneli = `
			<li>${createData}</li>
		`;
		return oneli;
	}

	function createShipmentList(data) {
		let li = '';
		li += createLi(data.country);
		li += createLi(data.lastname);
		li += createLi(data.firstname);
		li += createLi(data.postnumber);
		li += createLi(data.address);
		return li;
	}

	function createPaymentList(data) {
		let li = '';
		li += createLi(data.card_number);
		li += createLi(data.expiration_date);
		li += createLi(data.card_holder);
		li += createLi(data.security_code);
		return li;
	}

	window.addEventListener('load', () => {
		sum = item_value[0].price * count;
        all = sum + postage;
		elshipment.innerHTML += createShipmentList(shipment_value);
		elpayment.innerHTML += createPaymentList(payment_value);

		elName.textContent = item_value[0].name;
		elCount.textContent = count + '個';
		elPrice.textContent = item_value[0].price.toLocaleString() + '円';
		elSum.textContent = sum.toLocaleString() + '円';
		elPostage.textContent = postage + '円';
		elAll.textContent = all.toLocaleString() + '円';
	});



	elbtn.onclick = sendData;
})();
