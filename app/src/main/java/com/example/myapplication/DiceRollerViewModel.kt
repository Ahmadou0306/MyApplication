package com.example.myapplication

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class DiceRollerViewModel:ViewModel() {

    fun handleUiEvent(event: UiEvent) {
        when (event) {
            is UiEvent.OnRollClicked -> getDiceImage()
        }
    }
    private fun getDiceImage() {
        val image = diceImages()
         _uiState.value = with(_uiState.value) {
             copy(
             diceImage = image,
             )
            }
    }
   private fun diceImages()= listOf<Int>(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6,
    ).shuffled().first()

   data class DiceRollerUIState(
       @DrawableRes val diceImage:Int,
       @StringRes  val labelBtn:Int,
    )
    sealed interface UiEvent {
        object OnRollClicked : UiEvent
    }

    private val _uiState = MutableStateFlow(
        DiceRollerUIState(
     R.drawable.empty_dice,
            R.string.app_name,
        )
    )


    val uiState: StateFlow<DiceRollerUIState> = _uiState.asStateFlow()
}