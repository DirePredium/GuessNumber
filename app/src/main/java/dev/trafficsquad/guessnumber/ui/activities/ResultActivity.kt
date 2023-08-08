package dev.trafficsquad.guessnumber.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.trafficsquad.guessnumber.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var bindingActivity : ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = ActivityResultBinding.inflate(layoutInflater);
        setContentView(bindingActivity.root)
        showResultInfo()
    }

    private fun showResultInfo() {
        bindingActivity.textResult.visibility = View.VISIBLE
        bindingActivity.textResult.text = intent.getStringExtra("resultText")
    }

    fun restartGame(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}