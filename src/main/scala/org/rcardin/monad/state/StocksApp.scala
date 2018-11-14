package org.rcardin.monad.state

object StocksApp {

  val Prices: Map[String, Double] = Map("AMZN" -> 1631.17, "GOOG" -> 1036.05, "TSLA" -> 346.00)

  /**
    * A stocks portfolio, which associate a stock name to the quantity owned
    */
  type Stocks = Map[String, Double]
  type Transaction[A] = Stocks => (A, Stocks)

  /**
    * Buys an amount (dollars) of the stock with given `name`. Returns the number
    * of purchased stocks.
    *
    * @param name The name of the stocks to buy
    * @param amount The amount in dollars to buy
    * @return The quantity of stocks purchased
    */
  def buy(name: String, amount: Double): Transaction[Double] = portfolio => {
    val purchased = amount / Prices(name)
    val owned = portfolio(name)
    (purchased, portfolio + (name -> (owned + purchased)))
  }

  /**
    * Sells a `quantity` of stocks of the given `name`. Returns the amount of
    * dollars earned by the selling operation.
    *
    * @param name The name of the stocks to sell
    * @param quantity The quantity of stocks to sell
    * @return The earned amount
    */
  def sell(name: String, quantity: Double): Transaction[Double] = portfolio => {
    val revenue = quantity * Prices(name)
    val owned = portfolio(name)
    (revenue, portfolio + (name -> (owned - quantity)))
  }

  /**
    * Sells all stocks called `from`, and with the revenue buys stocks called `to`.
    * Returns the quantity of stock sold and the quantity of stocks purchased.
    *
    * @param from Stocks to be sold
    * @param to Stocks to be purchased
    * @return The quantity of stock sold and the quantity of stocks purchased
    */
  def move(from: String, to: String): Transaction[(Double, Double)] = portfolio => {
    val originallyOwned = portfolio(from)
    val (revenue, newPortfolio) = sell(from, originallyOwned)(portfolio)
    val (purchased, veryNewPortfolio) = buy(to, revenue)(newPortfolio)
    ((originallyOwned, purchased), veryNewPortfolio)
  }
}
