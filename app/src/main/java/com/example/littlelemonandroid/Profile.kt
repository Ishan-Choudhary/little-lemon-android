package com.example.littlelemonandroid

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import androidx.navigation.NavHostController
import com.example.littlelemonandroid.ui.theme.DarkGray
import com.example.littlelemonandroid.ui.theme.Green
import com.example.littlelemonandroid.ui.theme.LittleLemonAndroidTheme
import com.example.littlelemonandroid.ui.theme.White
import com.example.littlelemonandroid.ui.theme.Yellow


@Composable
fun Profile(navController: NavHostController)  {
    val context : Context = LocalContext.current
    val sharedPrefs = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val firstName : String = sharedPrefs.getString("first name", "").toString()
    val lastName : String = sharedPrefs.getString("last name", "").toString()
    val email : String = sharedPrefs.getString("email", "").toString()

    LittleLemonAndroidTheme {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxHeight()
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
                modifier = Modifier
                    .height(270.dp)
                    .padding(horizontal = 10.dp),

                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                DisplayBox("First name", firstName)
                DisplayBox("Last name", lastName)
                DisplayBox("Email", email)
            }
            Button(
                onClick = {
                    sharedPrefs.edit(commit = true) {
                        clear()
                    }
                    navController.navigate("Onboarding")
                    Toast.makeText(context, "Logged out successfully!", Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Yellow),
                shape = RoundedCornerShape(6.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 35.dp)
            ) {
                Text(
                    text = "Log out",
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            Spacer(modifier = Modifier.imePadding())
        }
    }
}

@Composable
fun DisplayBox(boxLabel: String, boxValue : String) {


    Column(
        modifier = Modifier
            .padding(bottom = 20.dp)
    ) {
        Text(
            text = boxLabel,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp)
                .border(1.dp, DarkGray, RoundedCornerShape(6.dp))
        ) {
            Text(
                text = boxValue,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp, start = 13.dp)
            )
        }
    }

}