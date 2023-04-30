package com.example.myapplication.projetmobile.ui.welcome

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateDpAsState
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.R
import com.example.myapplication.projetmobile.MyPreferences
import kotlinx.coroutines.delay

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
                fontSize = 25.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .animateContentSize()
            )
            Image(
                painter = painterResource(id = R.drawable.equipewecome),
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
                    .padding(top = 10.dp)
                    .size(width = 200.dp, height = 40.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Blue,
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "Commencer",
                    fontWeight = FontWeight.Light,
                    fontSize = 15.sp,
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
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Icone de l'application",
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 1.dp)
                    .align(Alignment.CenterHorizontally)
                    .size(200.dp)
                    .animateContentSize() // Animer la taille de l'image
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(35.dp)
            ) {
                Text(
                    text = "Gestion des Tâches",
                    style = MaterialTheme.typography.h4.copy(fontWeight = FontWeight.Bold),
                    color = Color.Black,
                    fontWeight = FontWeight.Light,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(bottom = 16.dp)
                        .alpha(animatedTextAlpha.value) // Animer l'opacité du texte
                )
                Text(
                    text = "Notre application vous aide à organiser votre travail et vos projets, en vous permettant de créer, éditer et suivre vos tâches en un seul endroit. Avec notre interface utilisateur simple et intuitive, vous pouvez facilement visualiser et prioriser votre travail pour rester productif tout au long de la journée.",
                    style = MaterialTheme.typography.body1,
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
                        .fillMaxWidth()
                        .scale(animatedButtonSize.value) // Animer la taille du bouton
                        .padding(horizontal = 32.dp, vertical = 16.dp)
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
            LazyColumn(
                modifier = Modifier.alpha(animateFloatAsState(targetValue = 1f).value),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                item {
                    Text(
                        text = "Terminer",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Light,
                        textAlign = TextAlign.Center,
                        color = Color.Black,
                        modifier = Modifier.padding(horizontal = 32.dp, vertical = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(32.dp))
                }
                item {
                    Image(
                        painter = painterResource(id = R.drawable.bureaux),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(16.dp)
                    )
                }
                item {
                    Button(
                        onClick = {
                            // Stocker la valeur true pour hasCompletedWelcome dans les préférences partagées
                            MyPreferences.hasCompletedWelcome=true
                            onMenuRoute()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Commencer",
                            style = MaterialTheme.typography.button,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LogoScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        var logoVisible by remember { mutableStateOf(false) }

        // Animation d'apparition
        LaunchedEffect(Unit) {
            logoVisible = true
        }

        // Animation de déplacement vers le centre
        val translateY = animateFloatAsState(
            if (logoVisible) 0f else (-200).dp.value,
            animationSpec = TweenSpec(durationMillis = 500, easing = FastOutSlowInEasing)
        ).value
        val modifier = Modifier.offset(y = translateY.dp)

        // Animation de zoom
        val scale = animateFloatAsState(
            if (logoVisible) 1f else 0.5f,
            animationSpec = TweenSpec(durationMillis = 500, easing = FastOutSlowInEasing)
        ).value
        val size = animateDpAsState(
            if (logoVisible) 200.dp else 100.dp,
            animationSpec = TweenSpec(durationMillis = 500, easing = FastOutSlowInEasing)
        ).value
        val scaleModifier = Modifier
            .size(size)
            .scale(scale)

        if (logoVisible) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo",
                modifier = modifier.then(scaleModifier)
            )
        }

        // Déclenche la redirection vers une autre page après 4 secondes
        LaunchedEffect(Unit) {
            delay(5000)
        }
    }
}