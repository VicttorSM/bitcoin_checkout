# bitcoin_checkout

This project is part of a test to evaluate my Java programming skills.

## The objective
A developer must be able to buy and sell bitcoins through the program.
- if he's selling, the price of the bitcoin must be added to his balance (in case he doesn't have the bitcoin, the transaction should not be computed)
- if he's buying it must be decreased (in case he doesn't have enough money in his account, the operation should not be computed).
- the interface with the user must be made in the command prompt.

## About the program
The user starts with a certain amount of money to be able to start transactions.
#### The commands work as follows:
- bitcoin add [id] [value] (in order to add a new coin to the possible selling and buying list)
- bitcoin buy [id] [quant]
- bitcoin sell [id] [quant]
- quit

## Documentation
The documentation can be found [here](/dist/javadoc/index.html).

## Licence
This project is licensed under the MIT license - see [LICENSE.md](/LICENSE) for further information.
