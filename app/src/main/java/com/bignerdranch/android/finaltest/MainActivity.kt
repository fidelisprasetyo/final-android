package com.bignerdranch.android.finaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.*

private lateinit var countText: TextView

private var stockList = mutableListOf<Stock>()

private val commentList : List<String> = listOf(
    "SPY is going up",
    "AAPL going down",
    "AMD AMD AMD",
    "buy TSLA",
    "SPY",
    "SPY ALL THE WAY"
)

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        countText = findViewById(R.id.count_text)

        // build stock list
        val csvfile = InputStreamReader(assets.open("us_stocks.csv"))
        val reader = BufferedReader(csvfile)
        var line : String?
        while (reader.readLine().also {line = it} != null) {
            val row : List<String> = line!!.split(",")
            val stock = Stock(row[0], row[1])
            stockList.add(stock)
        }

        // where the actual counting happens
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
                else if(comment.equals(stock.ticker)) {   // only contains ticker
                    stock.count++
                }
            }
        }

        // get the top 10
        val tempList = stockList.toMutableList()
        var leaderboards = mutableListOf<Stock>()
        repeat(10) {
            var topStock = tempList.maxByOrNull{it.count}
            leaderboards.add(topStock!!)
            tempList.remove(topStock)
        }

        // for debugging purposes
        var displaytext : String = ""
        for(stuff in leaderboards) {
            displaytext = displaytext + stuff.ticker + ' ' + stuff.name + ' ' + stuff.count + '\n'
        }
        countText.text = displaytext

    }
}