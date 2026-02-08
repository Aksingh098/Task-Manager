package com.example.taskmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.taskmanager.model.Task
import com.example.taskmanager.model.fakeTasks
import com.example.taskmanager.ui.theme.TaskManagerTheme
import com.example.taskmanager.ui.theme.getPriorityColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalTextStyle
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.text.style.TextDecoration

private  const val TAG = "MainActivity"
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskManagerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TaskManagerApp(modifier= Modifier)

                }
            }
        }
    }
}

@Composable
fun TaskManagerApp(modifier: Modifier = Modifier) {

    val tasks = rememberSaveable() {fakeTasks.toMutableStateList()}
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {  },

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
            items(tasks){task ->
                TaskCard(
                    task = task,
                    onCheckedChange = { newValue->

                        val index = tasks.indexOf(task)
                        tasks[index] = task.copy(isCompleted = newValue)

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
                    LocalTextStyle.current.copy(textDecoration = TextDecoration.LineThrough,
                        color = Color.Gray.copy(alpha = 0.6f))
                } else {
                    LocalTextStyle.current
                }


                Text(
                    text = task.title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    style = textStyle
                )
                Text(
                    text = task.description,
                    fontSize = 18.sp,
                    color = Color.Gray

                    )
            }


        }
    }

}
