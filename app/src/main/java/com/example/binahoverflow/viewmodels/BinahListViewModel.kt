package com.example.binahoverflow.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.binahoverflow.data.BinahAdapterItem
import com.example.binahoverflow.data.QuestionAdapterItem
import com.example.binahoverflow.repository.QuestionsRepository
import kotlinx.coroutines.launch

class BinahListViewModel : ViewModel() {


    private val questionsRepository = QuestionsRepository()

    private var listData: MutableLiveData<List<QuestionAdapterItem>> = MutableLiveData()


    fun getQuestions(): MutableLiveData<List<QuestionAdapterItem>> {

        listData = MutableLiveData()

        viewModelScope.launch {
            listData.value = questionsRepository.getQuestion()
        }


        return listData
    }


    fun filterAnswered() {
        viewModelScope.launch {
            listData.value = questionsRepository.getQuestion().filter { it.isQuestionAnswered() }
        }
    }

    fun filterUnAnswered() {
        viewModelScope.launch {
            listData.value = questionsRepository.getQuestion().filter { !it.isQuestionAnswered() }
        }
    }

}