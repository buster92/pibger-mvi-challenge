package pinger.challenge

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    val state by viewModel.state.collectAsState()

    Column {
        when {
            state.isLoading -> ContentWithProgress()
            state.data.isNotEmpty() -> MainScreenContent(
                state.data,
            )
        }
    }
}

@Composable
private fun MainScreenContent(
    todos: List<MainScreenItem>,
) {
    Box {
        LazyColumn(content = {
            itemsIndexed(todos) { index, item ->
                when (item) {
                    is MainScreenItem.MainScreenTodoItem -> {
                        MessageCard(item = item)
                    }
                }
            }
        })
    }
}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MessageCard(
    item: MainScreenItem.MainScreenTodoItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp)
            .clickable{ },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column (modifier = Modifier.padding(15.dp)) {
            Text(
                text = pluralStringResource(
                    id = R.plurals.repeated_string,
                    item.repeatCount,
                    item.repeatCount
                ),
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 16.sp
                )
            )

            Spacer(Modifier.height(16.dp))
            Text(
                text = item.pathText,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp
                )
            )
        }
    }
}

@Composable
private fun AddButton(
    onAddButtonClick: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = android.R.drawable.btn_plus),
            contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .align(Alignment.Center)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onAddButtonClick
                )
        )
    }
}

@Composable
private fun ContentWithProgress() {
    Surface(color = Color.LightGray) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}