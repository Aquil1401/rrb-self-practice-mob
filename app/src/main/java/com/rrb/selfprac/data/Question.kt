package com.rrb.selfprac.data

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: String,
    val text_en: String,
    val text_hi: String,
    val options_en: List<String>,
    val options_hi: List<String>,
    val correct_option_index: Int,
    val subject: String,
    val year: String? = null
)
