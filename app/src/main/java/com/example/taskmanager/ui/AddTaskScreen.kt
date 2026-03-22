package com.example.taskmanager.ui

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskmanager.R
import com.example.taskmanager.model.Priority
import com.example.taskmanager.ui.theme.TaskManagerTheme
import com.example.taskmanager.ui.theme.getPriorityColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskScreen(
    taskViewModel: TaskViewModel = viewModel(),
    onSave: (String, String, Priority) -> Unit,
    onCancel: () -> Unit

){
    val addTaskUiState by taskViewModel.taskUiState.collectAsState()


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Add Task",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) {innerPadding  ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            ElevatedCard() {
                Column() {
                    OutlinedTextField(
                        value = addTaskUiState.title,
                        onValueChange = {taskViewModel.addTitle(it)},
                        label = {Text(stringResource(R.string.task_title))},
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()

                    )
                    Spacer(modifier = Modifier.padding(10.dp))
                    OutlinedTextField(
                        value = addTaskUiState.description,
                        onValueChange = {
                            taskViewModel.addDescription(it)
                        },
                        label = {Text(stringResource(R.string.description))},
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()

                    )
                    Spacer(modifier = Modifier.padding(10.dp))

                    PrioritySelector(
                        selectedPriority = addTaskUiState.selectedpriority,
                        onPrioritySelected = {
                            taskViewModel.updatePriority(priority = it)
                        }
                    )

                    Spacer(modifier = Modifier.padding(10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        TextButton(onClick = { /* Handle Cancel */ }) {
                            Text(stringResource(R.string.cancel_button))
                        }

                        Spacer(modifier = Modifier.width(8.dp))


                        OutlinedButton(onClick = { /* Handle Continue */ }) {
                            Text(stringResource(R.string.continue_button))
                        }

                        Spacer(modifier = Modifier.width(8.dp))


                        Button(
                            onClick = { onSave(
                                addTaskUiState.title,
                                addTaskUiState.description,
                                addTaskUiState.selectedpriority
                            ) },
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
                        ) {
                            Text(stringResource(R.string.save_button))
                        }
                    }



                }
            }
        }



    }

}

@Composable
private fun PrioritySelector(
    selectedPriority: Priority,
    onPrioritySelected: (Priority) -> Unit

){
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Text("Select Priority:")
        Spacer(modifier = Modifier.width(8.dp))
        for(priority in Priority.entries){
            Button(
                onClick = {
                    onPrioritySelected(priority)
                },
                modifier = Modifier.size(40.dp),
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp) ,
                border = if(selectedPriority == priority) BorderStroke(2.dp, MaterialTheme.colorScheme.onSurface) else null,
                colors = ButtonDefaults.buttonColors(
                    containerColor = getPriorityColor(priority),
                )
            ) {

            }
            Spacer(Modifier.width(4.dp))
        }
    }


}

//@Preview(showBackground = true)
//@Composable
//fun PrioritySelectorPreview() {
//    // Wrapping in your custom theme ensures colors and Inter fonts show up correctly
//    TaskManagerTheme {
//        Surface(color = MaterialTheme.colorScheme.background) {
//            AddTaskScreen()
//        }
//    }
//}