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
| expirationMonth | String | required, expiration month for card, example: "09" |
| expirationYear | String | required, expiration year for card, example: "2019" |
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