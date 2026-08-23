package com.rrb.selfprac.features.tutor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class Message(val text: String, val isUser: Boolean)

@HiltViewModel
class TutorViewModel @Inject constructor(
    private val model: GenerativeModel
) : ViewModel() {

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    fun sendMessage(text: String) {
        viewModelScope.launch {
            _messages.value += Message(text, true)
            _isLoading.value = true
            try {
                val response = model.generateContent(content { text(text) })
                _messages.value += Message(response.text ?: "Sorry, no response.", false)
            } catch (e: Exception) {
                _messages.value += Message("Error: ${e.message}", false)
            } finally {
                _isLoading.value = false
            }
        }
    }
}
