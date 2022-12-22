(function () {
    var stripe = Stripe('pk_test_51MFFB6HbjJts3zpD1fSV8oaaZ2oJDqUtR6cwQXT16RQzDHNAaBD50fGyThNmZ3YNgiY4yfAaHHGIGmU2sWMyl19b00w2a0diJS');

    var checkoutButton5 = document.getElementById('formpay:button5');
    var checkoutButton10 = document.getElementById('formpay:button10');
    var checkoutButton20 = document.getElementById('formpay:button20');
    var checkoutButton50 = document.getElementById('formpay:button50');
    var checkoutButton100 = document.getElementById('formpay:button100');
    var checkoutButton500 = document.getElementById('formpay:button500');
    checkoutButton5.addEventListener('click', function () {
        /*
         * When the customer clicks on the button, redirect
         * them to Checkout.
         */
        stripe.redirectToCheckout({
            lineItems: [{price: 'price_1MFFNIHbjJts3zpDTiK3cJBC', quantity: 1}],
            mode: 'payment',
            /*
             * Do not rely on the redirect to the successUrl for fulfilling
             * purchases, customers may not always reach the success_url after
             * a successful payment.
             * Instead use one of the strategies described in
             * https://stripe.com/docs/payments/checkout/fulfill-orders
             */
            successUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
            cancelUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
        })
                .then(function (result) {
                    if (result.error) {
                        /*
                         * If `redirectToCheckout` fails due to a browser or network
                         * error, display the localized error message to your customer.
                         */
                        var displayError = document.getElementById('error-message');
                        displayError.textContent = result.error.message;
                    }
                });
    });
    checkoutButton10.addEventListener('click', function () {
        /*
         * When the customer clicks on the button, redirect
         * them to Checkout.
         */
        stripe.redirectToCheckout({
            lineItems: [{price: 'price_1MFFNVHbjJts3zpDmnPssGjF', quantity: 1}],
            mode: 'payment',
            /*
             * Do not rely on the redirect to the successUrl for fulfilling
             * purchases, customers may not always reach the success_url after
             * a successful payment.
             * Instead use one of the strategies described in
             * https://stripe.com/docs/payments/checkout/fulfill-orders
             */
            successUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
            cancelUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
        })
                .then(function (result) {
                    if (result.error) {
                        /*
                         * If `redirectToCheckout` fails due to a browser or network
                         * error, display the localized error message to your customer.
                         */
                        var displayError = document.getElementById('error-message');
                        displayError.textContent = result.error.message;
                    }
                });
    });
    checkoutButton20.addEventListener('click', function () {
        /*
         * When the customer clicks on the button, redirect
         * them to Checkout.
         */
        stripe.redirectToCheckout({
            lineItems: [{price: 'price_1MFFNtHbjJts3zpD2YDIj75S', quantity: 1}],
            mode: 'payment',
            /*
             * Do not rely on the redirect to the successUrl for fulfilling
             * purchases, customers may not always reach the success_url after
             * a successful payment.
             * Instead use one of the strategies described in
             * https://stripe.com/docs/payments/checkout/fulfill-orders
             */
            successUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
            cancelUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
        })
                .then(function (result) {
                    if (result.error) {
                        /*
                         * If `redirectToCheckout` fails due to a browser or network
                         * error, display the localized error message to your customer.
                         */
                        var displayError = document.getElementById('error-message');
                        displayError.textContent = result.error.message;
                    }
                });
    });
    checkoutButton50.addEventListener('click', function () {
        /*
         * When the customer clicks on the button, redirect
         * them to Checkout.
         */
        stripe.redirectToCheckout({
            lineItems: [{price: 'price_1MFFNtHbjJts3zpD2YDIj75S', quantity: 1}],
            mode: 'payment',
            /*
             * Do not rely on the redirect to the successUrl for fulfilling
             * purchases, customers may not always reach the success_url after
             * a successful payment.
             * Instead use one of the strategies described in
             * https://stripe.com/docs/payments/checkout/fulfill-orders
             */
            successUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
            cancelUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
        })
                .then(function (result) {
                    if (result.error) {
                        /*
                         * If `redirectToCheckout` fails due to a browser or network
                         * error, display the localized error message to your customer.
                         */
                        var displayError = document.getElementById('error-message');
                        displayError.textContent = result.error.message;
                    }
                });
    });
    checkoutButton100.addEventListener('click', function () {
        /*
         * When the customer clicks on the button, redirect
         * them to Checkout.
         */
        stripe.redirectToCheckout({
            lineItems: [{price: 'price_1MFFO6HbjJts3zpD01sIY5Wk', quantity: 1}],
            mode: 'payment',
            /*
             * Do not rely on the redirect to the successUrl for fulfilling
             * purchases, customers may not always reach the success_url after
             * a successful payment.
             * Instead use one of the strategies described in
             * https://stripe.com/docs/payments/checkout/fulfill-orders
             */
            successUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
            cancelUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
        })
                .then(function (result) {
                    if (result.error) {
                        /*
                         * If `redirectToCheckout` fails due to a browser or network
                         * error, display the localized error message to your customer.
                         */
                        var displayError = document.getElementById('error-message');
                        displayError.textContent = result.error.message;
                    }
                });
    });
    checkoutButton500.addEventListener('click', function () {
        /*
         * When the customer clicks on the button, redirect
         * them to Checkout.
         */
        stripe.redirectToCheckout({
            lineItems: [{price: 'price_1MFFOGHbjJts3zpDgIyaASVR', quantity: 1}],
            mode: 'payment',
            /*
             * Do not rely on the redirect to the successUrl for fulfilling
             * purchases, customers may not always reach the success_url after
             * a successful payment.
             * Instead use one of the strategies described in
             * https://stripe.com/docs/payments/checkout/fulfill-orders
             */
            successUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
            cancelUrl: 'http://172.16.178.31:8080/Spendensammler-Web-App/faces/User/SpendenDanke.xhtml',
        })
                .then(function (result) {
                    if (result.error) {
                        /*
                         * If `redirectToCheckout` fails due to a browser or network
                         * error, display the localized error message to your customer.
                         */
                        var displayError = document.getElementById('error-message');
                        displayError.textContent = result.error.message;
                    }
                });
    });

})();