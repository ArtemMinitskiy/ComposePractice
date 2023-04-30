package com.example.composepractice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.composepractice.ui.theme.ComposePracticeTheme
import com.example.composepractice.ui.theme.Purple700
import com.example.composepractice.ui.theme.Teal200

//https://metanit.com/kotlin/jetpack/1.3.php

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme(false) {
                // A surface container using the 'background' color from the theme
//                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//                    Greeting("Android")
//                }
                setLazyColumn()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposePracticeTheme {
        setLazyColumn()
//        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
//            Greeting("Android")
//        }
    }
}

@Composable
fun setLazyColumn() {
    val list = listOf("A", "B", "C", "D") + ((0..100).map { it.toString() })
    LazyColumn(modifier = Modifier.fillMaxHeight()) {
        items(items = list, itemContent = { item ->
            Log.d("COMPOSE", "This get rendered $item")
            when (item) {
                "A" -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
                "B" -> {
                    Button(onClick = {}) {
                        Text(text = item, style = TextStyle(fontSize = 80.sp))
                    }
                }
                "C" -> {
                    //Do Nothing
                }
                "D" -> {
                    Text(text = item)
                }
                else -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
            }
        })
    }
}

@Composable
fun setLazyRow() {
    val list = listOf("A", "B", "C", "D") + ((0..100).map { it.toString() })
    LazyRow(modifier = Modifier.fillMaxHeight()) {
        items(items = list, itemContent = { item ->
            Log.d("COMPOSE", "This get rendered $item")
            when (item) {
                "A" -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
                "B" -> {
                    Button(onClick = {}) {
                        Text(text = item, style = TextStyle(fontSize = 80.sp))
                    }
                }
                "C" -> {
                    //Do Nothing
                }
                "D" -> {
                    Text(text = item)
                }
                else -> {
                    Text(text = item, style = TextStyle(fontSize = 80.sp))
                }
            }
        })
    }
}

@Composable
fun setLazyVerticalGrid() {
    val list = (1..10).map { it.toString() }

    LazyVerticalGrid(
        columns = GridCells.Adaptive(128.dp),
//        columns = GridCells.Fixed(2),

        // content padding
        contentPadding = PaddingValues(
            start = 12.dp,
            top = 16.dp,
            end = 12.dp,
            bottom = 16.dp
        ),
        content = {
            items(list.size) { index ->
                Card(
                    backgroundColor = Color.Red,
                    modifier = Modifier
                        .padding(4.dp)
                        .fillMaxWidth(),
                    elevation = 8.dp,
                ) {
                    Text(
                        text = list[index],
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    )
}


@Composable
fun setImage() {
    Column {
//        AsyncImage(
//            model = "https://avatars.githubusercontent.com/u/52722434?s=200&v=4",
//            contentDescription = null
//        )
        Image(
            bitmap = ImageBitmap.imageResource(R.drawable.image),
            contentDescription = null
        )
//        AsyncImage(
//            model = ImageRequest.Builder(LocalContext.current)
//                .data("https://miro.medium.com/v2/resize:fit:1400/1*iLQTWoBb1zMnl-SWdHmzvw.jpeg")
//                .crossfade(true)
//                .build(),
//            placeholder = painterResource(R.drawable.image),
//            contentDescription = stringResource(R.string.app_name),
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.clip(CircleShape)
//        )
    }
}

@Composable
fun setSelected() {
    val colors = listOf(
        ColorData("красный", Color.Red),
        ColorData("зеленый", Color.Green),
        ColorData("синий", Color.Blue))
    val selectedOption = remember { mutableStateOf(colors[0]) }
    Column(modifier =Modifier.padding(20.dp)) {
        Box(Modifier
            .padding(10.dp)
            .size(100.dp)
            .background(color = selectedOption.value.color))

        colors.forEach { colorData ->
            val selected = selectedOption.value == colorData
            Row(modifier = Modifier.selectable(
                selected = selected,
                onClick = { selectedOption.value = colorData }),
                verticalAlignment = Alignment.CenterVertically
            ){
                Box(Modifier
                    .padding(8.dp)
                    .size(60.dp)
                    .background(color = colorData.color)
                    .border(width = if (selected) {
                        2.dp
                    } else {
                        0.dp
                    }, color = Color.Black)
                )
                Text(text = colorData.title, fontSize = 22.sp)
            }
        }
    }
}

data class ColorData(val title:String, val color: Color)

@Composable
fun setCheckBox() {
    val checkedState = remember { mutableStateOf(true) }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it },
        modifier = Modifier.padding(5.dp),
        colors  = CheckboxDefaults.colors(checkedColor = Color(0xff, 0xb6, 0xc1), checkmarkColor = Color.Red)
    )
}

@Composable
fun setTextField() {
    val message = remember{mutableStateOf("")}

    Column {
        Text(message.value, fontSize = 28.sp)
        TextField(
            value = message.value,
            textStyle = TextStyle(fontSize=25.sp),
            onValueChange = {newText -> message.value = newText},
            placeholder = { Text("Hello Work!") },
            colors = TextFieldDefaults.textFieldColors(textColor=Color.Red, backgroundColor = Color.LightGray)
        )
    }
}

@Composable
fun setButton() {
    val label = remember{mutableStateOf("Click")}
    Button(onClick = {label.value = "Hello"},
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red, contentColor = Color.Black),
        border = BorderStroke(3.dp, Color.DarkGray)){
        Text(label.value, fontSize = 25.sp)
    }
}

//Modifier.fillMaxWidth(): растягивает компонент по всей ширине контейнера
//Modifier.fillMaxHeight(): растягивает компонент по всей высоте контейнера
//Modifier.fillMaxSize(): растягивает компонент по всей длине и ширине контейнера
//В качестве параметра модификаторы Modifier.fillMaxWidth(), Modifier.fillMaxHeight() и Modifier.fillMaxSize() принимают множитель, который устанавливает, какую часть от размеров контейнера займет компонент.
//Это значение имеет тип Float и находится в диапазоне от 0.0 до 1.0.
@Composable
fun Greeting(name: String) {
    val myColor = Color(red = 0xF1, green = 0xAA, blue = 0x55, alpha = 0xFF)
    Text(text = "Hello $name!",
        style = TextStyle(
            fontSize = 22.sp,
            color = myColor
        ),
        modifier = Modifier
            .background(Purple700)
            .padding(20.dp)
//            .fillMaxSize(0.5f)
            .fillMaxHeight(0.5f)
            .fillMaxWidth(0.3f)
//            .width(200.dp).height(200.dp)
            .padding(30.dp)
    )
}

@Composable
fun Greeting2() {
    Text("Hello", fontSize=22.sp,
        modifier = Modifier
            .fillMaxSize()
            //отступы перед закрашенной областью
            .padding(20.dp)
            .background(color = Color.Yellow)
            //отступы перед текстом
            .padding(30.dp)
            //сдвиг по горизонтали и вертикали
            .offset(x = 30.dp, y = 50.dp)
    )
}

//Modifier.horizontalScroll
//state: представляет объект ScrollState и описывает состояние полосы прокрутки
//enabled: значение типа Boolean, которое указывает, будет ли прокрутка доступна. По умолчанию имеет значение true
//flingBehavior: представляет объект FlingBehavior и описывает поведение при завершении прокрутки. По умолчанию имеет значение ScrollableDefaults.flingBehavior
//reverseScrolling: устанавливает направление. При значении true прокрутка идет в обратном направлении. По умолчанию имеет значение false
@Composable
fun HorizontalScroll() {
    Text(
        "What is Lorem Ipsum?\n" +
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\n" +
                "...............",
        fontSize=22.sp,
        modifier = Modifier
            .background(color = Color.Yellow)
            .horizontalScroll(ScrollState(0))
    )
}
@Composable
fun VerticalScroll() {
    Text(
        "What is Lorem Ipsum?\n" +
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.\n" +
                "\n" +
                "...............",
        fontSize=22.sp,
        modifier = Modifier
            .background(color = Color.Yellow)
            .verticalScroll(rememberScrollState())
    )
}

@Composable
fun Click() {
    val count = remember{mutableStateOf(0)}

    Text("Clicks: ${count.value}",
        fontSize = 28.sp,
        modifier = Modifier.clickable( onClick = { count.value += 1 })
    )
}

//Box является наиболее простым контейнером, позволяя позиционировать вложенное содержимое.
//modifier: объект Modifier, который позволяет настроить внешний вид и поведение компонента с помощью модификаторов
//contentAlignment: объект Alignment, который устанавливает расположение компонента. По умолчанию имеет значение Alignment.TopStart (расположение вначале контейнера в верхнем углу)
//propagateMinConstraints: значение типа Boolean, который указывает, надо ли применять к содержимому ограничения по минимальным размерам. По умолчанию равно false (ограничения не применяются)
//content: объект интерфейса BoxScope, который представляет вложенное содержимое
@Composable
fun setBox() {
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)) {
        Text("Hello", style = TextStyle(fontSize = 22.sp))
    }
}

//Column позволяет выстроить вложенные компоненты в столбик. Функция Column принимает четыре параметра:
//modifier: объект Modifier, который позволяет настроить внешний вид и поведение компонента
//verticalArrangement: объект Arrangement.Vertical, который устанавливает выравнивание компонента по вертикали. По умолчанию имеет значение Arrangement.Top (расположение в верху)
//horizontalAlignment: объект Alignment.Horizontal, который устанавливает выравнивание компонента по горизонтали. По умолчанию имеет значение Alignment.Start (расположение в начале - слева для языков с левосторонним письмом и справа для языков с правосторонним письмом)
//content: объект интерфейса BoxScope, который представляет вложенное содержимое
@Composable
fun setColumn() {
    Column {
        Column {
            Text("Hello", style = TextStyle(fontSize = 22.sp))
            Text("World", style = TextStyle(fontSize = 22.sp))
            Text("from", style = TextStyle(fontSize = 22.sp))
            Text("Jetpack Compose", style = TextStyle(fontSize = 22.sp))
        }
        Column {
            Box(modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .weight(1f))
            Box(modifier = Modifier
                .background(Color.Yellow)
                .fillMaxSize(0.5f)
                .weight(3f))
            Box(modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
                .weight(2f))
        }
    }
}

//Row располагает вложенные компоненты в строку. Функция Row принимает четыре параметра:
//modifier: объект Modifier, который позволяет настроить внешний вид и поведение компонента
//verticalArrangement: объект Arrangement.Horizontal, который устанавливает выравнивание компонента по вертикали. По умолчанию имеет значение Arrangement.Start (расположение в вначале: слева для левосторонних языков и справа для правосторонних языков)
//verticalAlignment: объект Alignment.Vertical, который устанавливает выравнивание компонента по вертикали. По умолчанию имеет значение Alignment.Top (расположение вверху)
//content: объект интерфейса RowScope, который представляет вложенное содержимое
@Composable
fun setRow() {
    Column {
        Column {
            Text("Hello", style = TextStyle(fontSize = 22.sp))
            Text("World", style = TextStyle(fontSize = 22.sp))
            Text("from", style = TextStyle(fontSize = 22.sp))
            Text("Jetpack Compose", style = TextStyle(fontSize = 22.sp))
        }
        Column {
            Box(modifier = Modifier
                .background(Color.Red)
                .fillMaxWidth()
                .weight(1f))
            Box(modifier = Modifier
                .background(Color.Yellow)
                .fillMaxSize(0.5f)
                .weight(3f))
            Box(modifier = Modifier
                .background(Color.Green)
                .fillMaxWidth()
                .weight(2f))
        }
    }
}