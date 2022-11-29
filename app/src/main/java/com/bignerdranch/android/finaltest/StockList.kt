package com.bignerdranch.android.finaltest

import java.io.BufferedReader
import java.io.InputStreamReader

class StockList {
    val context = App.context
    private val stockList = mutableListOf<Stock>()

    init {
        // build stock list
        val csvfile = InputStreamReader(context?.assets!!.open("us_stocks.csv"))
        val reader = BufferedReader(csvfile)
        var line : String?
        while (reader.readLine().also {line = it} != null) {
            val row : List<String> = line!!.split(",")
            val stock = Stock(row[0], row[1])
            stockList.add(stock)
        }
    }
    fun getList() : MutableList<Stock> {
        return stockList
    }
}