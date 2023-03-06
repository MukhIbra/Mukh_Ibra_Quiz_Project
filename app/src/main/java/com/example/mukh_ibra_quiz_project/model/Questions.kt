package com.example.mukh_ibra_quiz_project.model

import androidx.appcompat.widget.AppCompatButton

data class Questions(
    val question: String,
    val variantA: String,
    val variantB: String,
    val variantC: String,
    val answer: String,
    var choosen: AppCompatButton? = null,
    var choosenAnswer: String = ""
)
