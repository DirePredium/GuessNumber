package dev.trafficsquad.guessnumber.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import dev.trafficsquad.guessnumber.databinding.ActivityStartBinding

class StartActivity : AppCompatActivity() {
    lateinit var bindingActivity : ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = ActivityStartBinding.inflate(layoutInflater);
        setContentView(bindingActivity.root)
    }

    fun startFame(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}