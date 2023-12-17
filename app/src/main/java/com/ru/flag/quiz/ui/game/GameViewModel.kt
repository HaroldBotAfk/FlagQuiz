package com.ru.flag.quiz.ui.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.ru.flag.quiz.R
import com.ru.flag.quiz.data.models.FlagCard
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class GameViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GameRepository(application)

    private val trueButtonNumberLiveData = MutableLiveData<Int>()
    private val falseButtonNumberLiveData = MutableLiveData<Int>()
    private val neutralButtonNumberLiveData = MutableLiveData<Int>()
    private val showQuestionLiveData = MutableLiveData<FlagCard>()
    private val isEnabledButtonsLiveData = MutableLiveData<Boolean>()
    private val resultNavigateLiveData = MutableLiveData<Int>()
    private val quantityLiveData = MutableLiveData<String>()
    private val toastLiveData = MutableLiveData<Int>()

    private var trueAnswer = 0
    private var counter = 0
    private var cardList = emptyList<FlagCard>()


    val trueButtonNumber: LiveData<Int>
        get() = trueButtonNumberLiveData

    val falseButtonNumber: LiveData<Int>
        get() = falseButtonNumberLiveData

    val neutralButtonNumber: LiveData<Int>
        get() = neutralButtonNumberLiveData

    val showQuestion: LiveData<FlagCard>
        get() = showQuestionLiveData

    val isEnabledButtons: LiveData<Boolean>
        get() = isEnabledButtonsLiveData

    val resultNavigate: LiveData<Int>
        get() = resultNavigateLiveData

    val quantity: LiveData<String>
        get() = quantityLiveData

    val toast: LiveData<Int>
        get() = toastLiveData

    fun prepareGame() {
        try {
            viewModelScope.launch {
                cardList = repository.createFlagCardList()
                showQuestionLiveData.postValue(cardList[counter])
            }
        } catch (e: Throwable) {
            toastLiveData.postValue(R.string.error)
        }
    }

    //(1) Отключение кнопок -> (2) проверка ответа + итерация -> (3) окрашивание кнопки ->
    //(4) показ правильного ответа (delay) -> (5) проверка на размер списка
    fun checkAnswer(numberButton: Int, userAnswer: String) {
        viewModelScope.launch {
            isEnabledButtonsLiveData.postValue(false)  //(1)

            val indexTrueAnswer = cardList[counter].listAnswer.indexOf(cardList[counter].answer)

            if (cardList[counter].answer == userAnswer) {  //(2)
                trueButtonNumberLiveData.postValue(numberButton)  //(3)
                trueAnswer++
                counter++
            } else {
                falseButtonNumberLiveData.postValue(numberButton)  //(3)
                trueButtonNumberLiveData.postValue(indexTrueAnswer + 1)  //(3)
                counter++
            }

            delay(1000)  //(4)

            if (counter != cardList.size) {  //(5)
                neutralButtonNumberLiveData.postValue(indexTrueAnswer + 1)
                delay(1)  //без delay не успевает обработать (без понятия почему)
                neutralButtonNumberLiveData.postValue(numberButton)

                showQuestionLiveData.postValue(cardList[counter])
                quantityLiveData.postValue("${counter + 1}/20")
                isEnabledButtonsLiveData.postValue(true)
            } else {
                resultNavigateLiveData.postValue(trueAnswer)
            }
        }
    }
}