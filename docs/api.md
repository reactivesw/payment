# Payment Service Rest API

## 1. Introduction

TODO

## 2. View Model

### CreditCardView

| field name | field type | comments |
|-----|------|-----|
| id | String | Credit Card Id in payment service |
| bin | String | first 6 card number |
| cardholderName | String | |
| cardType | String | The code-friendly representation of the card type: visa discover master-card american-express, etc. |
| customerId | String | customerId|
| expired | boolean | |
| last4 | String | last 4 card number |

### CreditCardDraft

| field name | field type | comments |
|-----|------|-----|
| number | String | credit card number, required |
| cardholderName | String | required |
| expirationMonth | String | required |
| expirationYear | String | required |
| cvv | String | required |
| customerId | String | required |

### PaymentView

| field name | field type | comments |
|-----|------|-----|
| id | String | payment transaction id |
| version | Integer | |
| customerId | String | |
| amountPlanned | Money |  How much money this payment intends to receive from the customer. The value usually matches the cart or order gross total. |
| amountPaid | Money |  The amount of money that has been received from the customer. This value is updated during the financial process. |
| amountRefunded | Money |  The amount of money that has been refunded to the customer. |
| paymentStatus | PaymentStatus | |
| transactions | List\<TransactionModel\> | |

### PayRequest

| field name | field type | comments |
|-----|------|-----|
| customerId | String | required, min size is 12 |
| amount | Money | required |
| creditCardId | String | required, min size is 12 |

## 3. API

### get all credit cards by customer id

* URL : {service url}/credit-cards/
* method : GET
* request param :

 | name | type | comments |
 |-|-|-|
 | customerId | String | required |

* response : List\<CreditCardView\>

### add credit card

* URL : {service url}/credit-cards/
* method : POST
* request : CreditCardDraft
* response : CreditCardView

### update credit card

TODO

* URL : {service url}/credit-cards/{creditCardId}
* method : PUT
* path variable :

   | name | type | comments |
   |-|-|-|
   | creditCardId | String | required |

* response : CreditCardView

### delete credit card

TODO

* URL : {service url}/credit-cards/{creditCardId}
* method : DELETE
* path variable :

  | name | type | comments |
  |-|-|-|
  | creditCardId | String | required |

* request param :

  | name | type | comments |
  |-|-|-|
  | version | Integer | required |

### pay by customerId id and credit card id

* URL : {service url}/
* method : POST
* request body :

  | name | type | comments |
  |-|-|-|
  | request | PayRequest | required |

* response : PaymentView
