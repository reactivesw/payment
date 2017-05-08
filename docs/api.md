# Payment Service Rest API

## 1. Introduction

TODO

## 2. View Model

### CreditCardView

| field name | field type | comments |
|-----|------|-----|
| id | String | Credit Card Id in payment service |
| version | Integer | |
| customerId | String | customer id in our system |
| bin | String | first 6 card number |
| cardholderName | String | |
| cardType | String | The code-friendly representation of the card type: visa discover master-card american-express, etc. |
| expired | boolean | |
| last4 | String | last 4 card number |
| selected | boolean | this field is true means the credit card is set as default, one customer should have only one selected credit card |

### CreditCardDraft

| field name | field type | comments |
|-----|------|-----|
| number | String | required, credit card number |
| cardholderName | String | required, credit card hoder name |
| expirationMonth | String | required, expiration month for card, at least 2 characters example: "09" |
| expirationYear | String | required, expiration year for card, at least 4 characters, example: "2019" |
| cvv | String | required, a 3 or 4 digit card verification value assigned to credit cards|
| customerId | String | required, customer id in our system |

### DefaultCardRequest

| field name | field type | comments |
|-----|------|-----|
| customerId | String | required |
| creditCardId | String | required |
| version | Integer | required |

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

### 3.1 get all credit cards by customer id

* URL : {ip}/payments/credit-cards/
* method : GET
* request param :

 | name | type | comments |
 |-|-|-|
 | customerId | String | required |

* response : List\<CreditCardView\>

### 3.2 add credit card

* URL : {ip}/payments/credit-cards/
* method : POST
* request : CreditCardDraft
* response : List\<CreditCardView\>
* payload sample :

```json
{
    "number":"4111111111111111",
    "cardholderName":"davis",
    "expirationMonth":"09",
    "expirationYear":"2019",
    "cvv":"123",
    "customerId":"7626af08-9667-41a9-8832-1748eeec5de9"
}
```

### 3.3 set credit card as default

* URL : {ip}/payments/credit-cards/
* method : PUT
* request body :

   | name | type | comments |
   |-|-|-|
   | defaultCardRequest | DefaultCardRequest | required |

* response : List\<CreditCardView\>
* payload sample :

```json
{
  "customerId":"7626af08-9667-41a9-8832-1748eeec5de9",
  "creditCardId":"cb14e8b5-980c-4ba3-aa62-963347e52511",
  "version":1
}
```

### 3.4 delete credit card

* URL : {ip}/payments/credit-cards/{creditCardId}
* method : DELETE
* path variable :

  | name | type | comments |
  |-|-|-|
  | creditCardId | String | required |

* request param :

  | name | type | comments |
  |-|-|-|
  | version | Integer | required |

* response : List\<CreditCardView\>

### 3.5 pay by customerId id and credit card id

* URL : {ip}/payments/
* method : POST
* request body :

  | name | type | comments |
  |-|-|-|
  | request | PayRequest | required |

* response : PaymentView

## 4. Test Data

Test data is from [braintree doc](https://developers.braintreepayments.com/reference/general/testing/java#credit-card-numbers)

### 4.1 Test Credit Card Number

| credit card number | credit card type | comments |
|-----|-----|-------|
| 378282246310005 | American Express | CVV must be 4 digits for American Express. |
| 371449635398431 | American Express | CVV must be 4 digits for American Express. |
| 6011111111111117 | Discover |  CVV must be 3 digits. |
| 3530111333300000 | JCB| CVV must be 3 digits. |
| 6304000000000000 | Maestro | Wrong data, credit card type is not accepted by this merchant. |
| 5555555555554444 | Mastercard | CVV must be 3 digits. |
| 2223000048400011 | Mastercard | CVV must be 3 digits. |
| 4111111111111111 | Visa | CVV must be 3 digits. |
| 4005519200000004 | Visa | CVV must be 3 digits. |
| 4009348888881881 | Visa | CVV must be 3 digits. |
| 4012000033330026 | Visa | CVV must be 3 digits. |
| 4012000077777777 | Visa | CVV must be 3 digits. |
| 4012888888881881 | Visa | CVV must be 3 digits. |
| 4217651111111119 | Visa | CVV must be 3 digits. |
| 4500600000000061 | Visa | CVV must be 3 digits. |

### 4.2  Test Amounts

| Amount | Authorization Response | Settlement Response |
|-------|---------|--------|
| $0.01 - $1999.99 | Authorized | Settled |
| $2000.00 - $3000.99 |	Processor Declined with a processor response equal to the amount |	n/a |
| $3001.00 - $4000.99 | 	Authorized | 	Settled |
| $4001.00 - $4001.99 | 	Authorized | 	Settlement Declined on certain transaction types with a processor response equal to the amount; Settled on all others |
| $4002.00 - $4002.99 | 	Authorized | 	Settlement Pending on PayPal transactions with a processor response equal to the amount; Settled on all others |
| $4003.00 - $5000.99 | 	Authorized | 	Settlement Declined on certain transaction types with a processor response equal to the amount; Settled on all others |
| $5001.00 |	Gateway Rejected with a reason of Application Incomplete | 	n/a |
| $5001.01 |	Processor Declined on PayPal transactions with a 2038 processor response. Authorized on all others |	n/a on PayPal transactions; Settled on all others |
| $5001.02 |	Authorized | 	Processor Unavailable on certain transaction types with a processor response of 3000; Settled on all others |
| $5002.00 and up |	Authorized | 	Settled |
