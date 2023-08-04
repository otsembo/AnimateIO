package com.example.animateio.ui.presentation.screens

import androidx.compose.animation.Animatable
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInOutBounce
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.StartOffsetType
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.animateio.data.Contact
import com.example.animateio.data.contactList
import com.example.animateio.ui.presentation.components.AppHeader

@Preview
@Composable
fun ContactList(
    controller: NavController = rememberNavController(),
    paddingValues: PaddingValues = PaddingValues(top = 32.dp, end = 16.dp, start = 16.dp)
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(paddingValues)) {

        AppHeader(
            message = "John Doe,",
            detail = "Get back to your contact list."
        )

        LazyColumn(
            modifier = Modifier.padding(top = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ){
            itemsIndexed(contactList){ index, contact ->
                ContactItem(contact = contact, click = {
                    controller.navigate("contactDetail/$index")
                })
            }
        }



    }

}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContactItem(
    contact: Contact = contactList[2],
    click: () -> Unit = {}
) {

    val borderColors = listOf(Color.Red, Color.Green)
    val descriptionVisible = rememberSaveable { mutableStateOf(false) }
    val colorTransition = rememberInfiniteTransition(label = "color-transition")
    val artColor by colorTransition.animateColor(
        initialValue = MaterialTheme.colorScheme.primaryContainer,
        targetValue = MaterialTheme.colorScheme.tertiary,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                this.durationMillis = 1500
                this.delayMillis = 500
            },
            repeatMode = RepeatMode.Restart,
        ), label = ""
    )


    fun toggleVisibility(){
        descriptionVisible.value = !descriptionVisible.value
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { toggleVisibility() }
            .animateContentSize(animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy)),
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .size(75.dp)
                        .border(
                            width = 2.dp,
                            brush = Brush.linearGradient(borderColors),
                            shape = CircleShape
                        )
                        .clip(CircleShape),
                    painter = painterResource(id = contact.icon),
                    contentDescription = contact.description,
                    contentScale = ContentScale.Crop
                )

                Column {
                    Text(
                        modifier = Modifier.padding(bottom = 2.dp),
                        text = contact.name,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                    Text(
                        modifier = Modifier,
                        text = contact.email,
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Light
                    )
                }

            }
            
            AnimatedContent(
                targetState = descriptionVisible.value,
                transitionSpec = {
                     if (targetState){
                         slideInVertically { fullHeight -> fullHeight } +
                                 fadeIn() with slideOutVertically { fullHeight -> fullHeight } + fadeOut()
                     } else {
                         slideInVertically { fullHeight -> fullHeight } +
                                 fadeIn() with slideOutVertically { fullHeight -> fullHeight } + fadeOut()
                     }.using(
                         SizeTransform(clip = false)
                     )},
                label = "icon_change"
            ) { toggle ->

                    val icon = if (!toggle) Icons.Filled.ArrowDownward else Icons.Filled.ArrowUpward

                    Icon(
                        modifier = Modifier
                            .clickable { toggleVisibility() },
                        imageVector = icon,
                        contentDescription = "Down"
                    )
            }

        }
        
        AnimatedVisibility(
            visible = descriptionVisible.value,
            enter = expandVertically(animationSpec = spring(dampingRatio = 0.4f, stiffness = 100f, null)),
        ) {
            Column {

                Text(
                    modifier = Modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                    text = contact.description,
                    style = MaterialTheme.typography.bodySmall
                )
                
                Button(
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(end = 10.dp, start = 10.dp, bottom = 5.dp),
                    onClick = click,
                    colors = ButtonDefaults
                        .buttonColors(
                            containerColor = if (descriptionVisible.value) artColor else MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onTertiary
                        )
                ) {
                    Text(text = "view")
                }

            }
        }
        

        

    }

}