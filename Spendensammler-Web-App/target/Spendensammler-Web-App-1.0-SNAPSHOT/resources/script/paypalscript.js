
paypal.Buttons({

        // Sets up the transaction when a payment button is clicked

        createOrder: (data, actions) => {

          return actions.order.create({

            purchase_units: [{

              amount: {

                value: document.getElementById("formpay:betrag").value // Can also reference a variable or function

              }

            }]

          });

        },

        // Finalize the transaction after payer approval

        onApprove: (data, actions) => {

          return actions.order.capture().then(function(orderData) {

            // Successful capture! For dev/demo purposes:

            console.log('Capture result', orderData, JSON.stringify(orderData, null, 2));

            const transaction = orderData.purchase_units[0].payments.captures[0];

           
             const element = document.getElementById('paypal-button-container');

             

            element.innerHTML = '<h3>Thank you for your payment!</h3>';
            document.getElementById("formpay:bezahlt").value ='bezahlt';
            document.getElementById("formpay:bezahltaktion").click();

          });

        }

      }).render('#paypal-button-container');
