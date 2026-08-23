package com.rrb.selfprac.features.exam

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExamScreen(
    viewModel: ExamViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    var showCalculator by remember { mutableStateOf(false) }

    val currentQuestion = state.questions.getOrNull(state.currentQuestionIndex)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Time: ${formatTime(state.timeLeftSeconds)}") },
                actions = {
                    Text("HI/EN")
                    Switch(
                        checked = state.isHindi,
                        onCheckedChange = { viewModel.toggleLanguage() }
                    )
                }
            )
        },
        bottomBar = {
            BottomAppBar {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    TextButton(onClick = { viewModel.prevQuestion() }) {
                        Text("PREVIOUS")
                    }
                    Button(
                        onClick = { viewModel.toggleMarkForReview() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (state.markedForReview[state.currentQuestionIndex] == true)
                                Color.Magenta else MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text("MARK FOR REVIEW")
                    }
                    Button(onClick = { viewModel.nextQuestion() }) {
                        Text("SAVE & NEXT")
                    }
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showCalculator = true }) {
                Icon(Icons.Default.Calculate, contentDescription = "Calculator")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            currentQuestion?.let { q ->
                Text("Question ${state.currentQuestionIndex + 1}", style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(if (state.isHindi) q.text_hi else q.text_en, style = MaterialTheme.typography.bodyLarge)
                Spacer(modifier = Modifier.height(24.dp))
                
                val options = if (state.isHindi) q.options_hi else q.options_en
                options.forEachIndexed { index, option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        RadioButton(
                            selected = state.selectedAnswers[state.currentQuestionIndex] == index,
                            onClick = { viewModel.selectAnswer(index) }
                        )
                        Text(option)
                    }
                }
            }
        }
    }

    if (showCalculator) {
        AlertDialog(
            onDismissRequest = { showCalculator = false },
            confirmButton = {
                TextButton(onClick = { showCalculator = false }) { Text("Close") }
            },
            title = { Text("Scientific Calculator") },
            text = { Text("Calculator UI here...") }
        )
    }
}

private fun formatTime(seconds: Int): String {
    val m = seconds / 60
    val s = seconds % 60
    return "%02d:%02d".format(m, s)
}
