package com.example.taskmanager.ui


import androidx.navigation.compose.NavHost
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.taskmanager.R
import com.example.taskmanager.model.Task
import com.example.taskmanager.ui.theme.getPriorityColor


enum class TaskScreen {
    Start,
    Add
}

@Composable
fun TaskManagerApp(
    navController: NavHostController = rememberNavController(),
    taskViewModel: TaskViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = TaskScreen.Start.name
    ) {

        composable(route =TaskScreen.Start.name ) {
            TaskManagerScreen(
                taskViewModel = taskViewModel,
                onAddTaskClick = {
                    navController
                }

            )
        }


        composable(route =TaskScreen.Add.name ) {


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskManagerScreen(
    modifier: Modifier = Modifier,
    taskViewModel: TaskViewModel = viewModel(),
    onAddTaskClick:() -> Unit

) {
    val taskUiState by taskViewModel.taskUiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar (
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium
                        )

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(

                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onAddTaskClick },

                ) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "Add Task")
            }
        }
    ) {innerPadding->




        LazyColumn(
            modifier
                .padding(innerPadding)
                .padding(14.dp)
        ) {
            items(taskUiState.itemList, key = { it.id }){task ->
                TaskCard(
                    task = task,
                    onCheckedChange = { newValue->

                        taskViewModel.toggleTaskCompletion(task,newValue)

                    }
                )


            }
        }
    }



}



@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    onCheckedChange: (Boolean) -> Unit,
    task: Task
) {
    Card(
        modifier = modifier.padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ){
            Box(
                modifier = Modifier
                    .width(6.dp)
                    .fillMaxHeight()
                    .background(getPriorityColor(task.priority)),
            )

            Spacer(modifier = Modifier.width(1.dp))

            Checkbox(
                checked = task.isCompleted,
                onCheckedChange = {newValue->
                    onCheckedChange(newValue)
                }
            )

            Column(modifier = Modifier
                .weight(1f)) {


                val textStyle = if (task.isCompleted) {
                    MaterialTheme.typography.titleLarge.copy(
                        textDecoration = TextDecoration.LineThrough,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
                    )
                } else {
                    MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }


                Text(
                    text = task.title,
                    style = textStyle
                )
                Text(
                    text = task.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant

                )
            }


        }
    }

}