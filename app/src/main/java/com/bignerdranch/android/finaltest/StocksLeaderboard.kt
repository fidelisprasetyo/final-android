package com.bignerdranch.android.finaltest

class StocksLeaderboard(commentList : List<String>, stockList : MutableList<Stock> = mutableListOf()) {
    private val leaderboards = mutableListOf<Stock>()
    init {
        for(comment in commentList) {
            for(stock in stockList) {
                if(comment.contains(stock.ticker + ' ')) {  // at the beginning
                    stock.count++
                }
                else if(comment.contains(' ' + stock.ticker)) {  // at the end
                    stock.count++
                }
                else if(comment.contains(' ' + stock.ticker + ' ')) {  // at the middle
                    stock.count++
                }
                else if(comment == stock.ticker) {   // only contains ticker
                    stock.count++
                }
            }
        }
        // get the top 10
        val tempList = stockList.toMutableList()
        repeat(10) {
            var topStock = tempList.maxByOrNull{it.count}
            leaderboards.add(topStock!!)
            tempList.remove(topStock)
        }
    }
    fun getLeaderboard() : MutableList<Stock> {
        return leaderboards
    }
}