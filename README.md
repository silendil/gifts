# gifts
Test application with REST API service for store and get simple gift cards structure.

# Use REST API
REST API available at host "/giftcards"
# Creation
For create card you should use endpoint "POST /giftcards" with body 
{"amount" : 20, //field should be positive, after delimiter you can leave 2 or 1 digit
"currency" : "EUR", // acceptable values are EUR, USD and BYN
"expiration" : "2021.03.01 00:00:00", // application use next format of date and time  "yyyy.MM.dd HH:mm:ss". If you do not want input time, use 00:00:00
"description" : "new card" // this field is optional
}

This request returns next (or similar) answer:
{
    "id": 3,
    "amount": 20.0,
    "currency": "EUR",
    "expiration": "2021.03.01 00:00:00",
    "description": null
}

# Getting all values
For getting all cards you should use endpoint "GET /giftcards"
It returns nex answer
[
    {
        "id": 1,
        "amount": 20.0,
        "currency": "EUR",
        "expiration": "2021.03.01 00:00:00",
        "description": "new card"
    },
    {
        "id": 2,
        "amount": 25.0,
        "currency": "USD",
        "expiration": "2021.4.01 00:00:00",
        "description": null
    },
    {
        "id": 3,
        "amount": 10.0,
        "currency": "BYN",
        "expiration": "2021.03.02 00:00:00",
        "description": "Some"
    }
]

# Getting card by id
You can get card by id use endpoint "GET /giftcards/{id}"
It returns next answer:
{
    "id": 1,
    "amount": 20.0,
    "currency": "EUR",
    "expiration": "2021.03.01 00:00:00",
    "description": "new card"
}
if card with used id is exist, or
{
    "timestamp": "2021-02-04T12:50:22.1494671",
    "message": "Card with id = 5 is not found",
    "details": []
}
if not exist.

# Filter by amount
Also you can filter cards by amount use "GET /giftcards?amount={amount}"
This request returns only cards, which expiration date after current and which amount more then used.
