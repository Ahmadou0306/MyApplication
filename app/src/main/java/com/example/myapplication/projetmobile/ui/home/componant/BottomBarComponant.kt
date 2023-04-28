package com.example.myapplication.projetmobile.ui.home.componant

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay


private const val colorPersonnel =0xFF1E88E5

@Composable
fun BottomBar(onHomeNavigate:()->Unit){
    var isShow = remember { mutableStateOf(false) }
    var isMessageShow= remember { mutableStateOf(false) }
    val idNotification= listNotification()
    BottomAppBar(
        backgroundColor= Color(color = colorPersonnel),
        cutoutShape = MaterialTheme.shapes.small.copy(
            CornerSize(percent = 50)
        )
    ) {
        Surface() {

            Column(modifier = Modifier.background(Color(color = colorPersonnel))) {
                val iconRotations = remember {
                    listOf(
                        mutableStateOf(false),
                        mutableStateOf(false),
                        mutableStateOf(false)
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(90.dp),
                    modifier = Modifier.background(
                        Color(color = colorPersonnel)
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.Home,
                        contentDescription = "",
                        tint = Color.White,
                        modifier = Modifier
                            .background(Color(color = colorPersonnel))
                            .width(40.dp)
                            .clickable {
                                iconRotations[0].value = true
                                onHomeNavigate()
                            }
                            .rotate(if (iconRotations[0].value) 20f else 0f)
                            .animateContentSize() // Ajout d'une animation de changement de taille
                            .padding(6.dp) // Ajout d'un padding pour rendre l'icône plus grande au clic
                        ,
                    )
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "",
                        modifier = Modifier
                            .background(Color(color = colorPersonnel))
                            .width(40.dp)
                            .clickable {
                                iconRotations[1].value = true
                                isShow.value = true
                            }
                            .rotate(if (iconRotations[1].value) 20f else 0f)
                            .animateContentSize()
                            .padding(6.dp),
                        tint = Color.White,
                    )

                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                iconRotations[2].value = true
                                isMessageShow.value = true
                            }
                            .rotate(if (iconRotations[2].value) 20f else 0f)
                            .animateContentSize()
                            .padding(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                        if (idNotification > 0) {
                            Text(
                                text = idNotification.toString(),
                                color = Color.White,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier
                                    .background(Color.Red, shape = CircleShape)
                                    .padding(2.dp)
                                    .align(Alignment.TopEnd)
                            )
                        }
                    }
                    LaunchedEffect(iconRotations[0].value) {
                        delay(100) // Attendre une seconde
                        iconRotations[0].value = false // Réinitialiser la rotation
                    }

                    LaunchedEffect(iconRotations[1].value) {
                        delay(100) // Attendre une seconde
                        iconRotations[1].value = false // Réinitialiser la rotation
                    }

                    LaunchedEffect(iconRotations[2].value) {
                        delay(100) // Attendre une seconde
                        iconRotations[2].value = false // Réinitialiser la rotation
                    }
                    CalendarModal(isShow)
                    MessageModal(isMessageShow)

                }
            }

        }
    }
}

