var xhr = new XMLHttpRequest();
var url = "https://cors.io/?https://api.playground.klarna.com/checkout/v3/orders";
xhr.open("POST", url, true);
var YOUR_USERNAME = "PK67556_f9a01409e2db";
var YOUR_PASSWORD = "0d7XzqbACbGaGB4R";

xhr.setRequestHeader('Authorization', 'Basic ' + btoa(unescape(encodeURIComponent(YOUR_USERNAME + ':' + YOUR_PASSWORD))));
xhr.setRequestHeader('Access-Control-Allow-Origin','*');
xhr.setRequestHeader("Content-Type", "application/json");
xhr.onreadystatechange = function () {
    if (xhr.readyState === 4 && xhr.status === 200) {
        var json = JSON.parse(xhr.responseText);
        console.log(json.email + ", " + json.password);
    }
};
var data = JSON.stringify({  "purchase_country": "GB",
  "purchase_currency": "GBP",
  "locale": "en-GB",
  "order_amount": 50000,
  "order_tax_amount": 4545,
  "order_lines": [
      {
          "type": "physical",
          "reference": "19-402-USA",
          "name": "Red T-Shirt",
          "quantity": 5,
          "quantity_unit": "pcs",
          "unit_price": 10000,
          "tax_rate": 1000,
          "total_amount": 50000,
          "total_discount_amount": 0,
          "total_tax_amount": 4545
      }
    ],
  "merchant_urls": {
    "terms": "https://www.example.com/terms.html",
    "checkout": "https://www.example.com/checkout.html?order_id={checkout.order.id}",
    "confirmation": "https://www.example.com/confirmation.html?order_id={checkout.order.id}",
    "push": "https://www.example.com/api/push?order_id={checkout.order.id}"
  }});
xhr.send(data);