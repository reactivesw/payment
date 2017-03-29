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
| expirationMonth | String | | 
| expirationYear | String | |
| expired | boolean | |
| last4 | String | last 4 card number |
| token | String | braintree payment method token | 

### CreditCardDraft

| field name | field type | comments |
|-----|------|-----|
| number | String | credit card number |
| cardholderName | String | | 
| expirationMonth | String | | 
| expirationYear | String | |
| cvv | String | | 

### PaymentView

| field name | field type | comments |
|-----|------|-----|
| id | String | payment transaction id |
| version | Integer | | 
| customerId | String | |
| externalId | String | braintree transaction id |
| amountPlanned | Money |  How much money this payment intends to receive from the customer. The value usually matches the cart or order gross total. | 
| amountPaid | Money |  The amount of money that has been received from the customer. This value is updated during the financial process. |
| amountRefunded | Money |  The amount of money that has been refunded to the customer. |
| paymentStatus | PaymentStatus | |
| paymentMethodInfo | PaymentMethodInfo | |
| transactions | List<TransactionModel> | |

## 3. API 

### get all credit cards by customerId id

* URL : {service url}/credit-cards/{customerId}
* method : GET
* response : CreditCardView

### add credit card to customerId

* URL : {service url}/credit-cards/{customerId}
* method : PUT
* request : CreditCardDraft
* response : CreditCardView

### pay by customerId id and credit card id

* URL : {service url}/
* method : POST
* request param : 
  
  customerId - String
  amount - String
  creditCardId - String

* response : PaymentView
