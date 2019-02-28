import { Component, OnInit } from '@angular/core';
import { PayPalConfig, ICreateOrderRequest } from 'ngx-paypal';

@Component({
  selector: 'app-donatee',
  templateUrl: './donatee.component.html',
  styleUrls: ['./donatee.component.scss']
})
export class DonateeComponent implements OnInit {

  constructor() { }

  public payPalConfig ? : PayPalConfig;

  showSuccess = false;
  showError = false;
  showCance = false;
 
  ngOnInit(): void {
      this.initConfig();
  }

  private initConfig(): void {
    this.payPalConfig = new PayPalConfig({
        currency: 'EUR',
        clientId: 'sb',
        createOrder: (data) => < ICreateOrderRequest > {
            intent: 'CAPTURE',
            purchase_units: [{
                amount: {
                    currency_code: 'EUR',
                    value: '9.99',
                    breakdown: {
                        item_total: {
                            currency_code: 'EUR',
                            value: '9.99'
                        }
                    }
                },
                items: [{
                    name: 'Enterprise Subscription',
                    quantity: '1',
                    category: 'DIGITAL_GOODS',
                    unit_amount: {
                        currency_code: 'EUR',
                        value: '9.99',
                    },
                }]
            }]
        },
        advanced: {
            updateOrderDetails: {
                commit: true
            }
        },
        style: {
            label: 'paypal',
            layout: 'vertical'
        },
        onApprove: (data, actions) => {
            console.log('onApprove - transaction was approved, but not authorized', data, actions);
            actions.order.get().then(details => {
                console.log('onApprove - you can get full order details inside onApprove: ', details);
            });

        },
        onClientAuthorization: (data) => {
            console.log('onClientAuthorization - you should probably inform your server about completed transaction at this point', data);
            //this.showSuccess = true;
        },
        onCancel: (data, actions) => {
            console.log('OnCancel', data, actions);
            //this.showCancel = true;

        },
        onError: err => {
            console.log('OnError', err);
            //this.showError = true;
        },
        onClick: () => {
            console.log('onClick');
            //this.resetStatus();
        },
    });
}
}
