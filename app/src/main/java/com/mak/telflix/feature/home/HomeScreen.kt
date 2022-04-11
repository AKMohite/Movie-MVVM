package com.mak.telflix.feature.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun HomeScreen() {
    val viewModel: HomeViewModel = hiltViewModel()
    val state = viewModel.state
    ScreenContent(
        state = state,
        loadNextItems = viewModel::loadData
    )
}

@Composable
private fun ScreenContent(
    state: HomeState,
    loadNextItems: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.items.size) { index ->
            val item = state.items[index]
            if (index >= state.items.size - 1 && !state.endReached && !state.isLoading) {
                loadNextItems()
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = item.name,
                    fontSize = 20.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.padding(8.dp))
                Text(text = item.overview)
            }
        }

        item {
            if (state.isLoading) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(
    name = "Night Mode",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    group = "selected",
    showBackground = true
)
@Preview(
    name = "Day Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    group = "selected",
    showBackground = true
)
@Composable
private fun ScreenContentPreview() {
    ScreenContent(
        state = HomeState(),
        loadNextItems = {}
    )
}
