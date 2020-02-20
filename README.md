# Credit Card Interest Service

- Calculates credit card interest for a person. Returns total interest per person and wallet/credit card interest depending on api called

## URL

- /interest/customerAndCreditCard

- /interest/customerAndWallet

## Data Params

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

- /customerAndCreditCard
- Code: 200
- Content:
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

- /customerAndWallet
- Code: 200
- Content:
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

<img src= "/SashaCodingProjects/credit-card-interest-service/images/testcase1.png" width= 60% length= 60%>

<img src= "/images/testCase2.png" width= 60% length= 60%>

<img src= "/images/testCase3.png" width= 60% length= 60%>