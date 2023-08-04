package com.example.animateio.ui.presentation.screens

import android.graphics.PathDashPathEffect.Style
import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawStyle
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animateio.data.contactList
import com.example.animateio.ui.presentation.components.AppHeader
import kotlinx.coroutines.delay

@OptIn(ExperimentalTextApi::class)
@Preview
@Composable
fun ContactDetail(
    paddingValues: PaddingValues = PaddingValues(top = 32.dp, end = 16.dp, start = 16.dp),
    index: Int = 0
) {

    val contact = contactList[index]
    val headerShown = remember { mutableStateOf(false) }
    val brushStartColor = remember { Animatable(Color.Green) }
    val brushEndColor = remember { Animatable(Color.Red) }
    LaunchedEffect(key1 = true, block = {
        delay(700)
        brushStartColor.animateTo(Color.Magenta)
        brushEndColor.animateTo(Color.Magenta)
        delay(700)
        brushStartColor.animateTo(Color.Blue)
        brushEndColor.animateTo(Color.Blue)
        delay(700)
        brushStartColor.animateTo(Color.Yellow, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500)
        ))
        brushEndColor.animateTo(Color.Yellow, animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1500)
        ))
    })

    LaunchedEffect(key1 = headerShown, block = {
        delay(500)
        headerShown.value = !headerShown.value
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(paddingValues)) {

        AnimatedVisibility(visible = headerShown.value) {
            AppHeader(
                message = "Welcome back, ${contact.name}",
                detail = contact.phone
            )
        }

        Image(
            modifier = Modifier
                .padding(top = 35.dp)
                .size(225.dp)
                .align(Alignment.CenterHorizontally)
                .clip(CircleShape)
                .border(
                    width = 5.dp,
                    brush = Brush.linearGradient(listOf(brushStartColor.value, brushEndColor.value), start = Offset.Infinite, end = Offset.Zero),
                    shape = CircleShape
                ),
            painter = painterResource(id = contact.icon),
            contentDescription = contact.name,
            contentScale = ContentScale.Crop
        )

        Text(
            text = contact.email,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 15.dp),
            fontStyle = FontStyle.Italic,
            textDecoration = TextDecoration.Underline
        )

        Text(
            text = contact.description,
            modifier = Modifier.padding(top = 5.dp),
            style = MaterialTheme.typography.bodyLarge.copy(
                lineBreak = LineBreak.Simple
            )
        )

    }

}