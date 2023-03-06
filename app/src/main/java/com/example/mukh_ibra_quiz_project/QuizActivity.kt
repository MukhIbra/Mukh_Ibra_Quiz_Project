package com.example.mukh_ibra_quiz_project

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.core.graphics.drawable.toDrawable
import com.example.mukh_ibra_quiz_project.model.Questions
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_quiz.*
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit

class QuizActivity : AppCompatActivity(), View.OnClickListener {


    private var index = 0

    private var questions: MutableList<Questions> = mutableListOf()

    private var correctAnswers = 0

    private lateinit var anim: Animation

    private lateinit var level: String

    private var button: AppCompatButton? = null

    private var isFinished: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        anim = AnimationUtils.loadAnimation(this, R.anim.shake)

        val bundle: Bundle? = intent.extras
        level = bundle!!.get("level") as String
        if (level == "Easy") loadQuestions1()
        else if (level == "Medium") loadQuestions2()
        else loadQuestions3()

        insert()

        pressAnswers()

        next.setOnClickListener {
            pressNext2()
        }

    }

    fun pressNext() {
        if (index == questions.size - 1) next.text = "Finish"
        if (index == questions.size) finish()
        else {
            btna.setBackgroundResource(R.drawable.btn_answer)
            btnb.setBackgroundResource(R.drawable.btn_answer)
            btnc.setBackgroundResource(R.drawable.btn_answer)
            insert()
            review()
        }
    }

    fun pressNext2() {
        if (!isFinished) {
            if (index == questions.size - 2) next.text = "Finish"
            if (index == questions.size - 1) {
                questions[index].choosen = button
                questions[index].choosenAnswer = button?.text.toString()
                button = null
                showDialog()
                return
            }
            questions[index].choosen = button
            questions[index].choosenAnswer = button?.text.toString()
            button = null
            index++

            btna.setBackgroundResource(R.drawable.btn_answer)
            btnb.setBackgroundResource(R.drawable.btn_answer)
            btnc.setBackgroundResource(R.drawable.btn_answer)
            insert()
        }else{
            if (index == questions.size - 1) next.text = "Finish"
            if (index == questions.size) {
                finish()
                return
            }
            btna.setBackgroundResource(R.drawable.btn_answer)
            btnb.setBackgroundResource(R.drawable.btn_answer)
            btnc.setBackgroundResource(R.drawable.btn_answer)
            insert()
            review()
        }

    }


    private fun pressAnswers() {
//        btna.setOnClickListener { pressAnswer(btna) }
//
//        btnb.setOnClickListener { pressAnswer(btnb) }
//
//        btnc.setOnClickListener { pressAnswer(btnc) }

        btna.setOnClickListener { pressAnswer2(btna) }

        btnb.setOnClickListener { pressAnswer2(btnb) }

        btnc.setOnClickListener { pressAnswer2(btnc) }
    }

    fun pressAnswer2(view: AppCompatButton) {
        btna.setBackgroundResource(R.drawable.btn_answer)
        btnb.setBackgroundResource(R.drawable.btn_answer)
        btnc.setBackgroundResource(R.drawable.btn_answer)
        view.setBackgroundResource(R.drawable.selected_button)
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        view.startAnimation(scaleAnimation)
        button = view

    }

    fun pressAnswer(view: AppCompatButton) {
        val scaleAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_animation)
        view.startAnimation(scaleAnimation)
//        selectedAnswers.add(view)
        if (index < questions.size - 1) {
            if (view.text == questions[index].answer) {
                runParty()
                correctAnswers++
            } else {
                view.startAnimation(anim)
            }
            index++
            insert()
        } else {
            if (view.text == questions[index].answer) {
                runParty()
                correctAnswers++
            }
            showDialog()
        }
    }

    private fun loadQuestions3() {
        questions.add(Questions("26^2 - 675= ?", "1", "72", "888", "1"))
        questions.add(Questions("x^2 - 4^3 = 960", "69", "10", "8", "10"))
        questions.add(Questions("5! - 3! = ?", "-82", "114", "82", "114"))
        questions.add(Questions("23x + 18/9 = 94", "-4", "4", "94", "4"))
        questions.add(Questions("12x + 33 = -15", "8", "-4", "23", "-4"))
        questions.add(Questions("26 + (39 % 10) = ?", "28.7", "29.9", "29", "29.9"))
        questions.add(Questions("2^10 / 2^3 = ?", "336", "128", "156", "128"))
        questions.add(Questions("if x/8 = 5 what is 8/x?", "0.50", "0.25", "0.2", "0.25"))
        questions.add(Questions("x^2 / 25 = 36", "30", "900", "45", "30"))
        questions.add(Questions("What is 10% of 360?", "3.6", "36", "90", "36"))
    }

    private fun loadQuestions1() {
        questions.add(Questions("186 - 29 = ?", "157", "234", "147", "157"))
        questions.add(Questions("1 + 45 = ?", "47", "34", "46", "46"))
        questions.add(Questions("16 * 9 = ?", "145", "144", "241", "144"))
        questions.add(Questions("36 / 9 = ?", "3", "324", "4", "4"))
        questions.add(Questions("6 + 9 / 3 = ?", "9", "5", "34", "9"))
        questions.add(Questions("3 / 3 = ?", "3", "1", "4", "1"))
        questions.add(Questions("78 - 3 = ?", "77", "75", "12", "75"))
        questions.add(Questions("8 - 3/3 = ?", "7", "5", "9", "7"))
        questions.add(Questions("82 / 2 = ?", "31", "34", "41", "41"))
        questions.add(Questions("52 + 2 = ?", "54", "64", "46", "54"))
    }

    private fun loadQuestions2() {
        questions.add(Questions("2x + 34 = 54", "10", "22", "8", "10"))
        questions.add(Questions("2345 + 1.5x = 2372", "19", "272", "18", "18"))
        questions.add(Questions("19 - 150/5 = ?", "-22", "-11", "22", "-11"))
        questions.add(Questions("23x + 18/9 = 94", "-4", "4", "94", "4"))
        questions.add(Questions("15x / 3 = 10", "4", "5", "2", "2"))
        questions.add(Questions("5x * 3 = 30", "2", "4", "12", "2"))
        questions.add(Questions("x * 3 = 60", "23", "24", "20", "20"))
        questions.add(Questions("x / 3 = 45", "135", "454", "212", "135"))
        questions.add(Questions("2x / 4 = 5", "5", "10", "12", "10"))
        questions.add(Questions("2x = 88", "52", "44", "27", "44"))
        questions.add(Questions("2x = 88-44", "22", "44", "26", "22"))
    }

    private fun insert() {
        Level.text = level
        tveasy.text = questions[index].question
        btna.text = questions[index].variantA
        btnb.text = questions[index].variantB
        btnc.text = questions[index].variantC
    }

    private fun runParty() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        konfettiView.start(party)
    }

    private fun restart() {
        btna.isClickable = true
        btnb.isClickable = true
        btnc.isClickable = true
        correctAnswers = 0
        index = 0
        next.text = "Next"
//        questions.shuffle()
        insert()
    }

    @SuppressLint("SetTextI18n")
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.activity_dialog)
        var unanswered_ = 0

        for (i in 0 until questions.size) {
            if (questions[i].choosenAnswer == questions[i].answer)
                correctAnswers++
            Log.d("correct",questions[i].choosenAnswer + " " + questions[i].answer)
            if (questions[i].choosen == null) unanswered_++
        }

        cwu.visibility = View.VISIBLE
        val correct = dialog.findViewById(R.id.correct) as TextView
        val wrong = dialog.findViewById(R.id.wrong) as TextView
        val unanswered = dialog.findViewById(R.id.unanswered) as TextView
        correct.text = correctAnswers.toString()
        unanswered.text = unanswered_.toString()
        wrong.text = (questions.size - correctAnswers - unanswered_).toString()

        val yesBtn = dialog.findViewById(R.id.menu) as AppCompatButton
        val noBtn = dialog.findViewById(R.id.restart) as AppCompatButton
        val goHome = dialog.findViewById(R.id.home) as AppCompatButton

        btna.setBackgroundResource(R.drawable.btn_answer)
        btnb.setBackgroundResource(R.drawable.btn_answer)
        btnc.setBackgroundResource(R.drawable.btn_answer)
        btna.isClickable = false
        btnb.isClickable = false
        btnc.isClickable = false
        yesBtn.setOnClickListener {
            index = 0
            insert()
            review()
            next.visibility = View.VISIBLE
            isFinished = true
            next.text = "Next"

            dialog.dismiss()
        }
        noBtn.setOnClickListener {
            restart()
            dialog.dismiss()
        }
        goHome.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        dialog.show()
    }

    fun review() {
        questions[index].choosen?.setBackgroundResource(R.drawable.btn_wrong_answer)
        if (btna.text == questions[index].answer) btna.setBackgroundResource(R.drawable.btn_correct_answer)
        else if (btnb.text == questions[index].answer) btnb.setBackgroundResource(R.drawable.btn_correct_answer)
        else btnc.setBackgroundResource(R.drawable.btn_correct_answer)
        if (questions[index].choosen == null) {
            cwu.text = "Unanswered"
            cwu.setTextColor(Color.DKGRAY)
        }else if (questions[index].choosen?.text.toString() == questions[index].answer) {
            cwu.text = "Correct"
            runParty()
            cwu.setTextColor(Color.GREEN)
        }else{
            cwu.text = "Wrong"
            cwu.setTextColor(Color.RED)
        }
        index++

    }


    override fun onClick(p0: View?) {
        val btn = findViewById<Button>(p0!!.id)
        index = btn.tag.toString().toInt()
        insert()
    }

}