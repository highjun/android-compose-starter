package com.example.coursegrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.coursegrid.data.DataSource
import com.example.coursegrid.ui.theme.CoursegridTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursegridTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CourseCardGrid(topic_list = DataSource.topics)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun CourseCardPreview() {
    CoursegridTheme {
        CourseCard(topic = Topic(R.string.architecture, 124, R.drawable.architecture))
    }
}

@Composable
fun CourseCardGrid(topic_list: List<Topic>) {
    LazyVerticalGrid(columns = GridCells.Fixed(2),
    verticalArrangement = Arrangement.spacedBy(8.dp),
    horizontalArrangement = Arrangement.spacedBy(8.dp)){
        items(topic_list){
            topic -> CourseCard(topic = topic)
        }
    }
}


@Composable
fun CourseCard(topic: Topic, modifier: Modifier = Modifier) {
    Card{
        Row{
            Box{
                Image(
                    painter = painterResource(id = topic.drawableResID),
                    contentDescription = stringResource(id = topic.nameResID),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .width(68.dp).height(68.dp)
                        .aspectRatio(1f)
                )
            }
            Column(modifier = Modifier.height(68.dp).padding(top = 4.dp, start = 8.dp,end = 8.dp, bottom = 4.dp)) {
                Text(text = stringResource(id = topic.nameResID))
                Spacer(modifier = Modifier.height(8.dp))
                Row(){
                    Icon(Icons.Default.Favorite, contentDescription = "favorite")
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = topic.recommended.toString())
                }
            }

        }
    }
}