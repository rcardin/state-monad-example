package org.rcardin.monad.state

object StocksApp {

  /**
    * A stocks portfolio, which associate a stock name to the quantity owned
    */
  type Stocks = Map[String, Double]

  /**
    * Buys an amount (dollars) of the stock with given {@code name}. Returns the number
    * of purchased stocks.
    * @param name The name of the stocks to buy
    * @param amount The amount in dollars to buy
    * @param portfolio The portfolio to use
    * @return The quantity of stocks purchased
    */
  def buy(name: String, amount: Double, portfolio: Stocks): (Double, Stocks) = ???

  /**
    * Sells a {@code quantity} of stocks of the given {@code name}. Returns the amount of
    * dollars earned by the selling operation.
    * @param name The name of the stocks to sell
    * @param quantity The quantity of stocks to sell
    * @param portfolio The portfolio to use
    * @return The earned amount
    */
  def sell(name: String, quantity: Double, portfolio: Stocks): (Double, Stocks) = ???
}
