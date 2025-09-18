package com.example.lab_week_02_assignments

import android.app.Activity
import android.content.Intent
import android.graphics.Color // Will be removed as unused after change
import android.os.Bundle
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import android.widget.TextView
import android.widget.Toast // Will be removed as unused after change
// com.google.android.material.textfield.TextInputEditText will be removed as unused
// androidx.activity.enableEdgeToEdge will be removed as unused
import androidx.appcompat.app.AppCompatActivity
// androidx.core.view.ViewCompat will be removed as unused
// androidx.core.view.WindowInsetsCompat will be removed as unused
import androidx.core.graphics.toColorInt

class ResultActivity : AppCompatActivity() {
    companion object {
        private const val COLOR_KEY = "COLOR_KEY"
        private const val ERROR_KEY = "ERROR_KEY"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val prevButton = findViewById<Button>(R.id.submit_button)
        prevButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        if(intent != null){
            val colorCode = intent.getStringExtra(COLOR_KEY)
            val backgroundScreen =
                findViewById<ConstraintLayout>(R.id.background_screen)
            try {
                backgroundScreen.setBackgroundColor("#$colorCode".toColorInt())
            }
            catch (ex: IllegalArgumentException){
                Intent().let{
                        errorIntent ->
                    errorIntent.putExtra(ERROR_KEY, true)
                    setResult(Activity.RESULT_OK, errorIntent)
                    finish()
                }
            }
            val resultMessage =
                findViewById<TextView>(R.id.color_code_result_message)
            resultMessage.text = getString(R.string.color_code_result_message,
                colorCode?.uppercase())
        }
    }
}