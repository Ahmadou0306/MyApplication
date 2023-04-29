package com.example.myapplication.projetmobile.ui.welcome

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.projetmobile.MyPreferences

@Composable
fun WelcomePage(onNext: () -> Unit) {
    val offset = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        offset.animateTo(
            targetValue = 1f,
            animationSpec = tween(durationMillis = 1000, delayMillis = 500)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .offset(y = with(LocalDensity.current) { offset.value.dp * 200 })
                .padding(horizontal = 32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bienvenue dans Team Flow !",
                fontSize = 28.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .animateContentSize()
            )
            Image(
                painter = painterResource(id = R.drawable.team),
                contentDescription = "Illustration de bienvenue",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .padding(bottom = 16.dp)
            )
            Button(
                onClick = {
                    onNext()
                },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 16.dp)
                    .size(width = 200.dp, height = 60.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Commencer",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .alpha(with(LocalDensity.current) { offset.value })
                        .animateContentSize()
                )
            }
        }
    }
}
@SuppressLint("RememberReturnType")
@Composable
fun AppDescriptionPage(onNext: () -> Unit) {
    val animatedColor = animateColorAsState(
        targetValue = Color.White,
        animationSpec = tween(durationMillis = 1000)
    )

    val animatedTextAlpha = animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, delayMillis = 500)
    )

    val animatedButtonSize = animateFloatAsState(
        targetValue = 1f,
        animationSpec = tween(durationMillis = 1000, delayMillis = 1000)
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(animatedColor.value)
    ) {
        Image(
            painter = painterResource(R.drawable.undraw_welcoming_re_x0qo),
            contentDescription = "Image de fond",
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(bottom = 16.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.kotlinico),
                contentDescription = "Icone de l'application",
                modifier = Modifier
                    .size(120.dp)
                    .animateContentSize() // Animer la taille de l'image
            )
            Spacer(modifier = Modifier.height(130.dp))
            Text(
                text = "Gestion des Tâches",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .animateContentSize() // Animer la taille du texte
                    .alpha(animatedTextAlpha.value) // Animer l'opacité du texte
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Notre application vous aide à organiser votre travail et vos projets, en vous permettant de créer, éditer et suivre vos tâches en un seul endroit. Avec notre interface utilisateur simple et intuitive, vous pouvez facilement visualiser et prioriser votre travail pour rester productif tout au long de la journée.",
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Light,
                color = Color.Black,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .alpha(animatedTextAlpha.value) // Animer l'opacité du texte
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onNext,
                modifier = Modifier
                    .align(Alignment.End)
                    .scale(animatedButtonSize.value) // Animer la taille du bouton
            ) {
                Text(
                    text = "Suivant",
                    style = MaterialTheme.typography.button,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun PresentationFinished(onMenuRoute: () -> Unit) {
    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        visible = true
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (visible) {
            Column(
                modifier = Modifier.alpha(animateFloatAsState(targetValue = 1f).value),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    Text(
                        text = "Présentation terminée",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(
                        onClick = {
                            // Stocker la valeur true pour hasCompletedWelcome dans les préférences partagées
                            MyPreferences.hasCompletedWelcome=true
                            onMenuRoute()
                        },
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        Text(
                            text = "Commmencer",
                            style = MaterialTheme.typography.button,
                            color = Color.White
                        )
                    }
                }
            )
        }
    }
}