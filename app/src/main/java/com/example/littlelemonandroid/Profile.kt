package com.example.littlelemonandroid

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.littlelemonandroid.ui.theme.Black
import com.example.littlelemonandroid.ui.theme.DarkGray
import com.example.littlelemonandroid.ui.theme.Green
import com.example.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.example.littlelemonandroid.ui.theme.White
import com.example.littlelemonandroid.ui.theme.Yellow

@Preview(showBackground = true)
@Composable
fun Profile()  {
    val context : Context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val verticalScrollState: ScrollState = rememberScrollState()
    val focusManager: FocusManager = LocalFocusManager.current

    var isAnyTextFieldFocused by remember { mutableStateOf(false) }

    val firstName = remember {
        mutableStateOf(sharedPrefs.getString("first name", "Tilly").toString())
    }
    val lastName = remember {
        mutableStateOf(sharedPrefs.getString("last name", "Doe").toString())
    }
    val email = remember {
        mutableStateOf(sharedPrefs.getString("email", "tillydoe@example.com").toString())
    }

    LittleLemonAndroidTheme {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .background(White)
                .pointerInput(Unit) {
                    detectTapGestures {
                        if (isAnyTextFieldFocused) {
                            focusManager.clearFocus()
                        }
                    }
                }
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .verticalScroll(verticalScrollState)
                    .background(White)
            ) {
                Box(
                    modifier = Modifier
                        .size(100.dp)
                        .wrapContentSize(unbounded = true)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Little Lemon Logo",
                        modifier = Modifier
                            .size(250.dp)
                    )
                }


                Text(
                    text = "Personal Information",
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 35.dp)
                )
                Column(
                    verticalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxHeight(0.75f)
                        .padding(horizontal = 10.dp)
                ) {
                    InputBoxes("First name", "Tilly", firstName) { isAnyTextFieldFocused = it }
                    InputBoxes("Last name", "Doe", lastName) { isAnyTextFieldFocused = it }
                    InputBoxes("Email", "tillydoe@example.com", email) { isAnyTextFieldFocused = it }
                }
                Button(
                    onClick = {

                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 35.dp)
                ) {
                    Text(
                        text = "Register",
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
                Spacer(modifier = Modifier.imePadding())
            }
        }
    }
}
