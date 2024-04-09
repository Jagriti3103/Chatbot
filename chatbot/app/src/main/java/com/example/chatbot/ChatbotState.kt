package com.example.chatbot

sealed interface ChatbotUiState{

    data object Ideal : ChatbotUiState
    data object Loading : ChatbotUiState
    data class Success(val ChatData: String): ChatbotUiState
    data class Error(val ChatError: String): ChatbotUiState
}