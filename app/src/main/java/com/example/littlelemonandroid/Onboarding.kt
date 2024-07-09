package com.example.littlelemonandroid

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.littlelemonandroid.ui.theme.Black
import com.example.littlelemonandroid.ui.theme.DarkGray
import com.example.littlelemonandroid.ui.theme.Green
import com.example.littlelemonandroid.ui.theme.White
import com.example.littlelemonandroid.ui.theme.Yellow


@Preview(showBackground = true)
@Composable
fun Onboarding()    {

    val verticalScrollState : ScrollState = rememberScrollState()

    Box(modifier = Modifier.fillMaxHeight().background(White))  {
        Column(
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
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Green)
                    .padding(vertical = 50.dp)
            ) {
                Text(
                    text = "Let's get to know you",
                    style = MaterialTheme.typography.bodyLarge,
                    color = White
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
                InputBoxes("First name", "Tilly")
                InputBoxes("Last name", "Doe")
                InputBoxes("Email", "tillydoe@example.com")
            }
            Button(
                onClick = { /*TODO*/ },
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
@Composable
fun InputBoxes(boxLabel : String, placeholderText : String)    {
    var inputBox by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier.padding(bottom = 20.dp)
    ) {
        Text(text = boxLabel, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold)
        OutlinedTextField(
            value = inputBox,
            onValueChange = {
                inputBox = it
            },

            placeholder = {
                Text(
                    text = placeholderText,
                    style = MaterialTheme.typography.labelSmall
                )
            },

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Black,
                unfocusedBorderColor = DarkGray,
                cursorColor = Black,
                focusedTextColor = Black,
                unfocusedTextColor = Black
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            textStyle = MaterialTheme.typography.labelMedium,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next
            )
        )

    }
}