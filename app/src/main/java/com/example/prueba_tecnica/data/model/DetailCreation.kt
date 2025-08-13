package com.example.prueba_tecnica.data.model

data class DetailCreation(
    val answers: List<Answer>,
    val section: String,
    val question: String,
    val questionnaire: String,
    val typeQuestionId: Int
)

data class Answer(
    val id: Int,
    val score: String,
    val title: String,
    val iconUrl: String,
    val selected: Boolean
)
