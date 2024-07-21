package com.example.littlelemonandroid

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.room.Room
import com.example.littlelemonandroid.ui.theme.Black
import com.example.littlelemonandroid.ui.theme.DarkGray
import com.example.littlelemonandroid.ui.theme.Green
import com.example.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.example.littlelemonandroid.ui.theme.White
import com.example.littlelemonandroid.ui.theme.Yellow
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json

//@Preview(showBackground = true)
@Composable
fun Home(navController: NavHostController)  {
    val searchPhrase = remember {
        mutableStateOf("")
    }

    val focusManager: FocusManager = LocalFocusManager.current
    var isAnyTextFieldFocused by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    LittleLemonAndroidTheme {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(White)
                .pointerInput(Unit) {
                    detectTapGestures {
                        if (isAnyTextFieldFocused) {
                            focusManager.clearFocus()
                        }
                    }
                }
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 110.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .wrapContentSize(unbounded = true)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Little Lemon Logo",
                        modifier = Modifier
                            .size(150.dp)
                    )
                }
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .wrapContentSize(unbounded = true)
                        .clip(CircleShape)
                        .padding(start = 30.dp)
                        .clickable {
                            navController.navigate(Profile.route)
                        }
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Little Lemon Logo",
                        modifier = Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .scale(2.0f)
                            .absoluteOffset(y = 7.dp)
                    )
                }
            }
            Column(
                modifier = Modifier
                    .background(Green)
                    .fillMaxWidth()
                    .padding(start = 15.dp, end = 15.dp, bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.app_title),
                    style = MaterialTheme.typography.titleMedium,
                    color = Yellow,
                    modifier = Modifier
                        .fillMaxWidth()
                        .size(50.dp)
                )
                Text(
                    text = stringResource(id = R.string.app_city),
                    style = MaterialTheme.typography.titleSmall,
                    color = White,
                )
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                )   {
                    Text(
                        text = stringResource(id = R.string.app_description),
                        color = White,
                        style = MaterialTheme.typography.bodyMedium,
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Left,
                        modifier = Modifier
                            .weight(0.7f)
                            .padding(top = 20.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.hero_image),
                        contentDescription = "Hero Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(height = 130.dp, width = 150.dp)
                            .weight(0.5f)
                            .clip(RoundedCornerShape(10.dp))
                    )
                }
                TextField(
                    value = searchPhrase.value,
                    onValueChange = {searchPhrase.value = it},
                    placeholder = {
                        Text(
                            text = "Enter search phrase",
                            style = MaterialTheme.typography.labelMedium,
                            color = DarkGray,
                            fontSize = 18.sp,
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = "Search Icon",
                            tint = Black
                        )
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = White,
                        focusedContainerColor = White
                    ),
                    textStyle = MaterialTheme.typography.labelMedium,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 15.dp,
                        )
                        .size(50.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .focusRequester(focusRequester)
                        .onFocusChanged { focusState ->
                            isFocused = focusState.isFocused
                            isAnyTextFieldFocused = isFocused
                        }
                )
            }

        }
    }
}

suspend fun fetchMenu() : List<MenuItemNetwork> {
    val httpClient = HttpClient(Android)    {
        install(ContentNegotiation) {
            json()
        }
    }

    val dataURL = "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    val menuNetwork : MenuNetwork = httpClient.get(dataURL).body()

    return menuNetwork.menu

}