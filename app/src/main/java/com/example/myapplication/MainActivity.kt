package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardingFlow()
        }
    }
}

@Composable
fun OnboardingFlow() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "splash") {
        composable("splash") { SplashScreen(navController) }
        composable("onboarding") { OnboardingScreen(navController) }
        composable("home") { HomeScreen() }
    }
}

@Composable
fun SplashScreen(navController: androidx.navigation.NavController) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000)
        navController.navigate("onboarding")
    }
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier.size(200.dp)
            )
            Text("UTH SmartTasks", fontSize = 24.sp, color = Color.Blue)
        }
    }
}

@Composable
fun OnboardingScreen(navController: androidx.navigation.NavController) {
    var pageIndex by remember { mutableStateOf(0) }
    val pages = listOf(
        OnboardingPage(
            "Easy Time Management",
            "With management based on priority and daily tasks, it will give you convenience in managing and determining the tasks that must be done first ",
            painterResource(id = R.drawable.image1)
        ),
        OnboardingPage(
            "Increase Work Effectiveness",
            "Time management and the determination of more important tasks will give your job statistics better and always improve",
            painterResource(id = R.drawable.image2)
        ),
        OnboardingPage(
            "Reminder Notification",
            "The advantage of this application is that it also provides reminders for you so you don't forget to keep doing your assignments well and according to the time you have set",
            painterResource(id = R.drawable.image3)
        )
    )
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextButton(
                onClick = { navController.navigate("home") },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Skip")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Image(painter = pages[pageIndex].image, contentDescription = null,modifier = Modifier.size(300.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(pages[pageIndex].title, fontSize = 22.sp, fontWeight = FontWeight.Bold)
            Text(pages[pageIndex].description, fontSize = 16.sp, modifier = Modifier.padding(16.dp))
            Spacer(modifier = Modifier.weight(1f))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                AnimatedVisibility(visible = pageIndex > 0) {
                    IconButton(onClick = { pageIndex-- },) {
                        Icon(
                            painter = painterResource(id = R.drawable.iconback),
                            contentDescription = "Back",
                            tint = Color.Unspecified
                        )
                    }
                }
                Button(
                    onClick = {
                        if (pageIndex < pages.size - 1) {
                            pageIndex++
                        } else {
                            navController.navigate("home")
                        }
                    }, shape = RoundedCornerShape(50),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(if (pageIndex == pages.size - 1) "Get Started" else "Next")
                }
            }
        }
    }
}

data class OnboardingPage(val title: String, val description: String, val image: androidx.compose.ui.graphics.painter.Painter)

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Welcome to Home Screen", fontSize = 24.sp, fontWeight = FontWeight.Bold)
    }
}