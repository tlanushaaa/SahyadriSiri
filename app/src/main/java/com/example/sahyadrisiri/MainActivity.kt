package com.example.sahyadrisiri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        // REPORT BUTTON
        val btnReport = findViewById<Button>(R.id.btnReport)

        btnReport.setOnClickListener {

            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }

        // MAP BUTTON
        val btnMap = findViewById<Button>(R.id.btnMap)

        btnMap.setOnClickListener {

            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }

        // VIEW BUTTON
        val btnView = findViewById<Button>(R.id.btnView)

        btnView.setOnClickListener {

            val intent = Intent(this, ViewActivity::class.java)
            startActivity(intent)
        }
    }
}