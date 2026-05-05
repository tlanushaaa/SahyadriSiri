package com.example.sahyadrisiri

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ReportActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)

        val locationInput = findViewById<EditText>(R.id.locationInput)
        val statusInput = findViewById<EditText>(R.id.statusInput)
        val submitBtn = findViewById<Button>(R.id.submitBtn)

        submitBtn.setOnClickListener {

            val location = locationInput.text.toString().trim()
            val status = statusInput.text.toString().trim()

            if (location.isEmpty() || status.isEmpty()) {
                locationInput.error = "Enter location"
                statusInput.error = "Enter water status"
            } else {

                val sharedPref = getSharedPreferences("WaterData", MODE_PRIVATE)
                val oldData = sharedPref.getString("reports", "") ?: ""

                val time = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault()).format(Date())
                val newEntry = "$location - $status ($time)"
                val updatedData = if (oldData.isEmpty()) {
                    newEntry
                } else {
                    "$oldData\n$newEntry"
                }

                val editor = sharedPref.edit()
                editor.putString("reports", updatedData)
                editor.apply()

                val intent = Intent(this, ViewActivity::class.java)
                startActivity(intent)
            }
        }
    }
}