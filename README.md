# Credit Card Interest Service

Can calculate: 
   - total interest per person
   - total interest per wallet
   - interest by credit card
    
## Endpoints/APIs

- /interest/customerAndCreditCard

- /interest/customerAndWallet

## Required Request Body

```json
[
	{
		"id": 1,
		"firstName": "James", 
		"lastName": "Bond",
		"wallets": [
			{
				"id": 1,
				"creditCards": [
					{
						"id": 1,
						"type": "VISA",
						"balance": 100
					},
					{
						"id": 2,
						"type": "MASTERCARD",
						"balance": 100
					},
					{
						"id": 3,
						"type": "DISCOVER",
						"balance": 100
					}
				]
			}
		]
	}
]
```

## Success Response:

1. `/interest/customerAndCreditCard`

```json
[
  {
    "customerId": 1,
    "firstName": "James",
    "lastName": "Bond",
    "totalInterest": 16.00,
    "interestByType": {
      "creditCard": {
        "1": 10.00,
        "2": 5.00,
        "3": 1.00
      }
    }
  }
]
```

2. `/interest/customerAndWallet`

```json
[
  {
    "customerId": 1,
    "firstName": "James",
    "lastName": "Bond",
    "totalInterest": 16.00,
    "interestByType": {
      "wallet": {
        "1": 11.00,
        "2": 5.00
      }
    }
  }
]
```

## Examples with Insomnia

![test case 1](https://github.com/sjlarson92/credit-card-interest-service/blob/master/images/testCase1.png)

![test case 2](https://github.com/sjlarson92/credit-card-interest-service/blob/master/images/testCase2.png)

![test case 3](https://github.com/sjlarson92/credit-card-interest-service/blob/master/images/testCase3.png)