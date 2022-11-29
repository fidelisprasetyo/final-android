package com.bignerdranch.android.finaltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

private lateinit var countText: TextView

private val commentList : List<String> = listOf(
    "SPY is going up",
    "AAPL is going down",
    "AMD AMD AMD",
    "buy TSLA",
    "SPY",
    "CALLS all the way"
)

class MainActivity : AppCompatActivity() {
    private var leaderboard = mutableListOf<Stock>()
    private var stockList = mutableListOf<Stock>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        stockList = StockList().getList()
        leaderboard = StocksLeaderboard(commentList, stockList).getLeaderboard()

        // for debugging purposes
        countText = findViewById(R.id.count_text)
        var displaytext : String = ""
        for(stuff in leaderboard) {
            displaytext = displaytext + stuff.ticker + ' ' + stuff.count + '\n'
        }
        countText.text = displaytext

    }
}
