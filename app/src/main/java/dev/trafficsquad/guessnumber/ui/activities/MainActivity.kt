package dev.trafficsquad.guessnumber.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import dev.trafficsquad.guessnumber.R
import dev.trafficsquad.guessnumber.data.GuessResult
import dev.trafficsquad.guessnumber.data.RandomNumberApi
import dev.trafficsquad.guessnumber.databinding.ActivityMainBinding
import dev.trafficsquad.guessnumber.exceptions.GuessNumberExceptionHandler
import dev.trafficsquad.guessnumber.ui.filters.NumberInputFilter
import dev.trafficsquad.guessnumber.ui.viewmodels.GuessNumberViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: GuessNumberViewModel
    private var activityException : Exception?

    init {
        Thread.setDefaultUncaughtExceptionHandler(GuessNumberExceptionHandler())
        activityException = null
        try {
            viewModel = GuessNumberViewModel(RandomNumberApi())
        } catch (e: Exception) {
            activityException = e
            e.printStackTrace()
        }
    }

    lateinit var bindingActivity : ActivityMainBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = ActivityMainBinding.inflate(layoutInflater);
        setContentView(bindingActivity.root)

        if(activityException != null) {
            showException(activityException as Exception)
            finish()
        }
        val editText = bindingActivity.textGuessNumber
        editText.filters = arrayOf(NumberInputFilter(1, 100))
    }

    fun answerGuessNumber(view: View) {
        val guessNumber = bindingActivity.textGuessNumber.text.toString().toIntOrNull()
        if(guessNumber == null || guessNumber < 1 || guessNumber > 100) {
            bindingActivity.textGuessNumber.error = "Enter an integer number between 1 and 100"
            return
        }
        val guessResult = viewModel.checkGuess(guessNumber)
        if(guessResult == GuessResult.CORRECT) {
            openResultActivity(getString(R.string.good_result))
            return
        }
        if(!viewModel.areAnyAttempts()) {
            openResultActivity(getString(R.string.bad_result))
            return
        }
        if(guessResult == GuessResult.TOO_HIGH) {
            bindingActivity.textGuessMessage.visibility = View.VISIBLE
            bindingActivity.textGuessMessage.text = getString(R.string.too_high_result, viewModel.getAttempts())
            return
        }
        bindingActivity.textGuessMessage.visibility = View.VISIBLE
        bindingActivity.textGuessMessage.text = getString(R.string.too_low_result, viewModel.getAttempts())
    }

    private fun openResultActivity(resultText : String) {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("resultText", resultText)
        startActivity(intent)
        finish()
    }

    private fun showException(e: Exception) {
        val toast = Toast.makeText(
            applicationContext,
            getString(R.string.exception_message, e.message), Toast.LENGTH_SHORT
        )
        toast.show()
    }
}