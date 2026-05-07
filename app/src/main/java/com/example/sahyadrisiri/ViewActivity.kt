package com.example.sahyadrisiri

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.sahyadrisiri.R

class ViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view)

        val reportsContainer = findViewById<LinearLayout>(R.id.reportsContainer)
        val clearBtn = findViewById<Button>(R.id.clearBtn)

        val sharedPref = getSharedPreferences("WaterData", MODE_PRIVATE)
        val reports = sharedPref.getString("reports", "") ?: ""

        if (reports.isNotEmpty()) {

            val reportLines = reports.split("\n")

            for (line in reportLines.reversed()) {

                if (line.isNotBlank()) {

                    val card = CardView(this)
                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )

                    params.setMargins(0, 0, 0, 24)

                    card.layoutParams = params
                    card.radius = 24f
                    card.cardElevation = 8f
                    card.setContentPadding(24, 24, 24, 24)

                    val textView = TextView(this)

                    textView.text = line
                    textView.textSize = 18f
                    textView.setLineSpacing(1.2f, 1.2f)

                    when {
                        line.contains("polluted", true) ||
                                line.contains("dirty", true) -> {
                            textView.setTextColor(Color.parseColor("#C62828"))
                        }

                        line.contains("clean", true) -> {
                            textView.setTextColor(Color.parseColor("#2E7D32"))
                        }

                        else -> {
                            textView.setTextColor(Color.BLACK)
                        }
                    }

                    card.addView(textView)

                    reportsContainer.addView(card)
                }
            }

        } else {

            val emptyText = TextView(this)
            emptyText.text = "No reports available"
            emptyText.textSize = 18f

            reportsContainer.addView(emptyText)
        }

        clearBtn.setOnClickListener {

            sharedPref.edit().clear().apply()

            recreate()
        }
    }
}