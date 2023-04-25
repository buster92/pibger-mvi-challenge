package pinger.challenge

import androidx.compose.runtime.Immutable

@Immutable
sealed class MainScreenUiEvent : UiEvent {
    data class ShowData(val items: List<MainScreenItem>) : MainScreenUiEvent()
}

@Immutable
data class MainScreenState(
    val isLoading: Boolean,
    val data: List<MainScreenItem>,
) : UiState {

companion object {
        fun initial() = MainScreenState(
            isLoading = true,
            data = emptyList(),
        )
    }

override fun toString(): String {
        return "isLoading: $isLoading, data.size: ${data.size}"
    }
}

sealed class MainScreenItem {
    data class MainScreenTodoItem(
        val repeatCount: Int,
        val pathText: String,
    ) : MainScreenItem()
}