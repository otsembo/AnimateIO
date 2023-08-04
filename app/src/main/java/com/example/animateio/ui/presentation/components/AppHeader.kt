package com.example.animateio.ui.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType

@Composable
fun AppHeader(
    modifier: Modifier = Modifier,
    message: String,
    detail: String,
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = message,
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.Bold,
                fontSize = TextUnit(35f, TextUnitType.Sp)
            ),
        )
        Text(
            text = detail,
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light,
                fontSize = TextUnit(20f, TextUnitType.Sp),
                color = MaterialTheme.colorScheme.tertiary
            )
        )
    }
}