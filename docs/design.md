# Payment Design

## 1. Basic Features

## 2. Model Design

## 3. Workflow

## 4. Event Design

### 4.1 Event Consumer

#### 4.1.1 Model

#### 4.1.2 Subscription

* Topic name: `reactivesw-order-created`
* Subscription name: `payment-order-created`

Use gcloud command to create the subscription:

```bash
gcloud beta pubsub subscriptions create --topic reactivesw-order-created payment-order-created
```

#### 4.1.3 Workflow

1. get paymentMethodId, totalAmount and orderId from event message
2. validate orderId: if orderId is exist, acknowledge to blocker, stop this process
3. pay: pay the totalAmount by paymentMethodId, acknowledge to blocker
4. store orderId: if pay successful, save orderId
5. store payment event

### 4.2 Event Producer

#### 4.2.1 Model

* event data

| field name | field type | comment |
|---|---|---|
| orderId | String | |
| paymentId | String | |
| result | Boolean  | true when pay successful |
| message | String | |

#### 4.2.2 Topic

Topic name: `reactivesw-payment-payed`
Use gcloud command to create the topic:

```bash
gcloud beta pubsub topics create reactivesw-payment-payed
```

#### 4.2.3 Workflow

1. get list of event, when it's `Created` status or `Pending` status but created 1 minutes ago
2. convert list of event to event message
3. publish message
4. delete event