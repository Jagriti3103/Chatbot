package com.example.chatbot

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatBotViewModel: ViewModel() {
    val list by lazy {
        mutableStateListOf<ChatData>()
    }
    private val genAI by lazy {
        GenerativeModel(
            // Use a model that's applicable for your use case (see "Implement basic use cases" below)
            modelName = "gemini-pro",
            apiKey = "AIzaSyBU1aFC5yo3izdskjPKZFBx0s-JNz1fGLw"
        )
    }
    fun sendMessage(message:String)= viewModelScope.launch{

        val chat = genAI.startChat()
        list.add(ChatData(message,ChatRoleEnum.USER.role))

        chat.sendMessage(
            content(ChatRoleEnum.USER.role) { text(message) }
        ).text?.let{
            list.add(ChatData(it, ChatRoleEnum.MODEL.role))
        }

    }

}