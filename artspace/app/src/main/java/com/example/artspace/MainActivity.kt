package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtspaceTheme

class MainActivity : ComponentActivity() {

    val image_list = listOf<ImageData>(
        ImageData(R.drawable.cat, "Cat", "Google Images", 2022),
        ImageData(R.drawable.sky, "Sky", "Google Images", 2019),
        ImageData(R.drawable.tiger1, "Tiger", "Google Images", 2014),
        ImageData(R.drawable.mountain2, "Mountain", "Google Images", 2021)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtspaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArtSpaceScreen(image_list = image_list)
//                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun ArtSpaceScreen(image_list: List<ImageData>) {
    var displayIndex: Int by remember { mutableStateOf(0) }
    val currentImage: ImageData = image_list.get(displayIndex)
    Column(modifier = Modifier.fillMaxWidth(),  verticalArrangement =  Arrangement.SpaceBetween) {
        ImageViewComponent(imageData = currentImage)
        DisplayController(
            onPreviousClick = {
                displayIndex = if (displayIndex == 0) image_list.size - 1 else displayIndex - 1
            },
            onNextClick = {
                displayIndex = if (displayIndex == image_list.size - 1) 0 else displayIndex + 1
            }
        )
    }

}

@Composable
fun ImageViewComponent(imageData: ImageData, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 2.dp, color = Color.Black, shape = RectangleShape)
        ) {
            Image(
                painter = painterResource(id = imageData.resID),
                contentScale = ContentScale.FillWidth,
                contentDescription = imageData.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(elevation = 1.dp, shape = RectangleShape, clip = true)
                .padding(10.dp),
        ) {
            Text(text = imageData.name, fontSize = 24.sp)
            Row() {
                Text(text = imageData.author, fontWeight = FontWeight.Bold)
                Text(text = String.format(" (%d)", imageData.year))
            }
        }
    }
}

@Composable
fun DisplayController(onPreviousClick: () -> Unit, onNextClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = { onPreviousClick() },
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
        ) {
            Text("Previous")
        }
        Button(
            onClick = { onNextClick() },
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    val sampleData: ImageData = ImageData(R.drawable.cat, "Cat", "Google Images", 2022)
    ArtspaceTheme {
        ImageViewComponent(imageData = sampleData)
    }
}