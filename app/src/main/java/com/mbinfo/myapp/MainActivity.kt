package com.mbinfo.myapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.udojava.evalex.Expression
import kotlinx.android.synthetic.main.activity_feedback_activity.view.*
import kotlinx.android.synthetic.main.main.*
import me.grantland.widget.AutofitTextView
import net.objecthunter.exp4j.ExpressionBuilder
import net.objecthunter.exp4j.operator.Operator

var factorial: Operator = object :
    Operator(
        "!",
        1,
        true,
        PRECEDENCE_POWER + 1
    ) {
    override fun apply(vararg args: Double): Double {
        val arg = args[0].toInt()
        require(arg.toDouble() == args[0]) { "Operand for factorial has to be an integer" }
        require(arg >= 0) { "The operand of the factorial can not be less than zero" }
        var result = 1.0
        for (i in 1..arg) {
            result *= i.toDouble()
        }
        return result
    }
}

class MainActivity : AppCompatActivity() {
    var count: Int = 0
    var c: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
         initLayout()
    }

    private fun initLayout() {
        var Result = findViewById<AutofitTextView>(R.id.Result)
        var Expression = findViewById<AutofitTextView>(R.id.Expression)

        val result = ExpressionBuilder("3!")
            .operator(factorial)
            .build()
            .evaluate()
        // Hide the status bar.


        var parent = findViewById<RelativeLayout>(R.id.Relative)

        var animationDrawable = parent.background as AnimationDrawable?
        animationDrawable?.setEnterFadeDuration(20)
        animationDrawable?.setExitFadeDuration(2000)
        animationDrawable?.start()

        one.setOnClickListener {
            evaluateExpression("1", true)
            toastmessage()
        }
        two.setOnClickListener {
            evaluateExpression("2", true)
            toastmessage()
        }
        three.setOnClickListener {
            evaluateExpression("3", true)
            toastmessage()
        }
        four.setOnClickListener {
            evaluateExpression("4", true)
            toastmessage()
        }
        five.setOnClickListener {
            evaluateExpression("5", true)
            toastmessage()
        }
        six.setOnClickListener {
            evaluateExpression("6", true)
            toastmessage()
        }
        seven.setOnClickListener {
            evaluateExpression("7", true)
            toastmessage()
        }
        eight.setOnClickListener {
            evaluateExpression("8", true)
            toastmessage()
        }
        nine.setOnClickListener {
            evaluateExpression("9", true)
            toastmessage()
        }
        zero.setOnClickListener {
            evaluateExpression("0", true)
            toastmessage()
        }
        dot.setOnClickListener {
            evaluateExpression(".", true)
            toastmessage()
        }


        plus.setOnClickListener {
            evaluateExpression("+", false)
        }
        minus.setOnClickListener {
            evaluateExpression("-", false)
        }
        multiply.setOnClickListener {
            evaluateExpression("*", false)
        }
        divide.setOnClickListener {
            evaluateExpression("/", false)
        }
        open.setOnClickListener {
            evaluateExpression("(", false)
        }
        close.setOnClickListener {
            evaluateExpression(")", false)
        }
        tenpower.setOnClickListener {
            evaluateExpression("10^", false)
        }



        tenpower.setOnLongClickListener {
            Alertdialog()
            true
        }
        dot.setOnLongClickListener {
            evaluateExpression("%", false)
            true
        }
        zero.setOnLongClickListener {
            evaluateExpression("^", false)
            true
        }
        root.setOnLongClickListener {
            evaluateExpression("cbrt(", false)
            true
        }
        seven.setOnLongClickListener {
            evaluateExpression("asin(", false)
            true
        }
        eight.setOnLongClickListener {
            evaluateExpression("acos(", false)
            true
        }
        nine.setOnLongClickListener {
            evaluateExpression("atan(", false)
            true
        }
        four.setOnLongClickListener {
            evaluateExpression("log(", false)
            true
        }
        five.setOnLongClickListener {
            evaluateExpression("log2(", false)
            true
        }
        six.setOnLongClickListener {
            evaluateExpression("log10(", false)
            true
        }
        one.setOnLongClickListener {
            evaluateExpression("sin(", false)
            true
        }
        two.setOnLongClickListener {
            evaluateExpression("cos(", false)
            true
        }
        three.setOnLongClickListener {
            evaluateExpression("tan(", false)
            true
        }
        root.setOnClickListener {
            evaluateExpression("sqrt(", false)
        }

        open.setOnLongClickListener {
            evaluateExpression("!", false)
            true
        }
        close.setOnLongClickListener {
            evaluateExpression("π", true)
            true
        }

        clear_del.setOnClickListener {
            val string = Expression.text.toString()
            if (string.isNotEmpty()) {
                Expression.text = string.substring(0, string.length - 1)
            }
            Result.text = ""
            while (count < 1) {
                Toast.makeText(applicationContext, "Long press to Clear", Toast.LENGTH_SHORT).show()
                count++
            }
        }
        clear_del.setOnLongClickListener {
            Expression.text = ""
            Result.text = ""
            true
        }

        equal.setOnClickListener {
            try {
                val expression = ExpressionBuilder(Expression.text.toString())
                    .operator(factorial)
                    .build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    Result.text = longResult.toString()
                else
                    Result.text = result.toString()

            } catch (e: Exception) {
                Log.d("Exception", " message : " + e.message)
                val error = e.message.toString()
                Result.text = "0"
                Toast.makeText(applicationContext, "" + error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun Alertdialog() {
        val DialogView =
            LayoutInflater.from(this).inflate(R.layout.activity_feedback_activity, null)

        val mBuilder = AlertDialog.Builder(this)
            .setView(DialogView)
            .setTitle("Feedback")

        val mAlertDialog = mBuilder.show()

        DialogView.cancel.setOnClickListener {
            mAlertDialog.dismiss()
        }

        DialogView.send.setOnClickListener {
            mAlertDialog.dismiss()

            val email = DialogView.to_email.text.toString().trim()
            val subject = DialogView.subject.text.toString().trim()
            val feedback = DialogView.feedback.text.toString().trim()
            //////////////////////////////////
            val intent = Intent(Intent.ACTION_SEND)

            intent.data = Uri.parse("mailto:")
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
            intent.putExtra(Intent.EXTRA_SUBJECT, subject)
            intent.putExtra(Intent.EXTRA_TEXT, feedback)
            try {
                startActivity(Intent.createChooser(intent, "Choose Email Client..."))
            } catch (e: java.lang.Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun evaluateExpression(string: String, canClear: Boolean) {
        val Result = findViewById<AutofitTextView>(R.id.Result)
        val Expression = findViewById<AutofitTextView>(R.id.Expression)
        if (Result.text.isNotEmpty()) {
            Expression.text = ""
        }

        if (canClear) {
            Result.text = ""
            Expression.append(string)
        } else {
            Expression.append(Result.text)
            Expression.append(string)
            Result.text = ""

        }

    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure to exit?")
        builder.setCancelable(true)
        builder.setNegativeButton("NO", DialogInterface.OnClickListener { dialogInterface, i ->
            dialogInterface.cancel()
        })
        builder.setPositiveButton("Exit", DialogInterface.OnClickListener { dialogInterface, i ->
            finish()
        })
        builder.setNeutralButton(
            "Rate this App",
            DialogInterface.OnClickListener { dialogInterface, i ->
                dialogInterface.cancel()
            })
        val alertDialog = builder.create()
        alertDialog.show()
        alertDialog.getWindow()?.setBackgroundDrawableResource(R.color.myColor);


    }

    private fun toastmessage() {
        while (c < 1) {
            Toast.makeText(
                applicationContext,
                "Long press to access Advanced Functions",
                Toast.LENGTH_LONG
            ).show()
            c++
        }
    }
    }

