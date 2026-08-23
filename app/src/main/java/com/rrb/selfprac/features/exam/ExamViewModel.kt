package com.rrb.selfprac.features.exam

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rrb.selfprac.data.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExamUiState(
    val questions: List<Question> = emptyList(),
    val currentQuestionIndex: Int = 0,
    val selectedAnswers: Map<Int, Int> = emptyMap(),
    val markedForReview: Map<Int, Boolean> = emptyMap(),
    val timeLeftSeconds: Int = 5400, // 90 mins
    val isHindi: Boolean = false
)

@HiltViewModel
class ExamViewModel @Inject constructor() : ViewModel() {

    private val _uiState = MutableStateFlow(ExamUiState())
    val uiState = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        // Load dummy data
        _uiState.value = _uiState.value.copy(
            questions = listOf(
                Question(
                    id = "1",
                    text_en = "What is the square root of 144?",
                    text_hi = "144 का वर्गमूल क्या है?",
                    options_en = listOf("10", "11", "12", "14"),
                    options_hi = listOf("10", "11", "12", "14"),
                    correct_option_index = 2,
                    subject = "Maths"
                )
            )
        )
        startTimer()
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.timeLeftSeconds > 0) {
                delay(1000)
                _uiState.value = _uiState.value.copy(
                    timeLeftSeconds = _uiState.value.timeLeftSeconds - 1
                )
            }
        }
    }

    fun nextQuestion() {
        if (_uiState.value.currentQuestionIndex < _uiState.value.questions.size - 1) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = _uiState.value.currentQuestionIndex + 1
            )
        }
    }

    fun prevQuestion() {
        if (_uiState.value.currentQuestionIndex > 0) {
            _uiState.value = _uiState.value.copy(
                currentQuestionIndex = _uiState.value.currentQuestionIndex - 1
            )
        }
    }

    fun selectAnswer(optionIndex: Int) {
        val newAnswers = _uiState.value.selectedAnswers.toMutableMap()
        newAnswers[_uiState.value.currentQuestionIndex] = optionIndex
        _uiState.value = _uiState.value.copy(selectedAnswers = newAnswers)
    }

    fun toggleLanguage() {
        _uiState.value = _uiState.value.copy(isHindi = !_uiState.value.isHindi)
    }

    fun toggleMarkForReview() {
        val newMarked = _uiState.value.markedForReview.toMutableMap()
        val currentIndex = _uiState.value.currentQuestionIndex
        newMarked[currentIndex] = !(newMarked[currentIndex] ?: false)
        _uiState.value = _uiState.value.copy(markedForReview = newMarked)
    }

    override fun onCleared() {
        timerJob?.cancel()
        super.onCleared()
    }
}
