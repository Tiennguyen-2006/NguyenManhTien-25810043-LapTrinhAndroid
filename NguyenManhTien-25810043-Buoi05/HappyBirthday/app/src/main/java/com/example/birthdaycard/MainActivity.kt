package com.example.birthdaycard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.scaleIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayCardApp()
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun BirthdayCardApp() {
    var recipientName by rememberSaveable { mutableStateOf("Tiến") }
    var isEditingName by rememberSaveable { mutableStateOf(false) }
    var wishCount by rememberSaveable { mutableIntStateOf(0) }
    
    val quickWishes = remember {
        listOf("🏋️ Sức khỏe", "🚀 Thành công", "🥰 Hạnh phúc", "💰 Tài lộc", "😄 Vui vẻ")
    }
    var selectedWishes by remember { mutableStateOf(setOf("🏋️ Sức khỏe", "🥰 Hạnh phúc", "🚀 Thành công")) }

    // Floating animation for icon
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulseScale"
    )

    val backgroundGradient = Brush.verticalGradient(
        colors = listOf(
            Color(0xFFFFF0F5), // Lavender blush
            Color(0xFFF3E5F5), // Light purple
            Color(0xFFFFEBEE)  // Soft pink
        )
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Transparent
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundGradient)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 20.dp, vertical = 28.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Header decoration
                Text(
                    text = "🎈 🎂 🎉",
                    fontSize = 28.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Main Birthday Card
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(
                            elevation = 12.dp,
                            shape = RoundedCornerShape(32.dp),
                            spotColor = Color(0x33D81B60)
                        ),
                    shape = RoundedCornerShape(32.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = Color.White
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Title Pill Tag
                        Surface(
                            shape = RoundedCornerShape(50),
                            color = Color(0xFFFCE4EC),
                            modifier = Modifier.padding(bottom = 20.dp)
                        ) {
                            Text(
                                text = "✨ CHÚC MỪNG SINH NHẬT ✨",
                                color = Color(0xFFD81B60),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                                letterSpacing = 1.5.sp
                            )
                        }

                        // Gift Icon Container
                        Box(
                            modifier = Modifier
                                .scale(pulseScale)
                                .size(96.dp)
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFFF80AB),
                                            Color(0xFFEC407A),
                                            Color(0xFFAB47BC)
                                        )
                                    )
                                )
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_gift),
                                contentDescription = "Hộp quà sinh nhật",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(56.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Recipient Name Display / Edit
                        if (isEditingName) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                OutlinedTextField(
                                    value = recipientName,
                                    onValueChange = { recipientName = it },
                                    label = { Text("Tên người nhận") },
                                    singleLine = true,
                                    modifier = Modifier.weight(1f),
                                    shape = RoundedCornerShape(16.dp),
                                    colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = Color(0xFFD81B60),
                                        focusedLabelColor = Color(0xFFD81B60)
                                    )
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Button(
                                    onClick = { isEditingName = false },
                                    shape = RoundedCornerShape(16.dp),
                                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF8E24AA))
                                ) {
                                    Text("Lưu")
                                }
                            }
                        } else {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .clickable { isEditingName = true }
                                    .padding(horizontal = 12.dp, vertical = 4.dp)
                            ) {
                                Text(
                                    text = "Gửi tới: ",
                                    fontSize = 16.sp,
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = recipientName.ifEmpty { "Bạn" },
                                    fontSize = 32.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFF7B1FA2),
                                    fontFamily = FontFamily.Serif
                                )
                                Text(
                                    text = " ✏️",
                                    fontSize = 16.sp
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Wishes Content Card
                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = Color(0xFFFFF8FA),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(
                                    width = 1.dp,
                                    color = Color(0xFFF8BBD0),
                                    shape = RoundedCornerShape(20.dp)
                                )
                        ) {
                            Column(
                                modifier = Modifier.padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "“ Chúc bạn luôn ngập tràn niềm vui, sức khỏe dồi dào và có một tuổi mới thật rực rỡ! ”",
                                    textAlign = TextAlign.Center,
                                    fontSize = 15.sp,
                                    color = Color(0xFF4A148C),
                                    lineHeight = 22.sp,
                                    fontWeight = FontWeight.Medium
                                )

                                if (selectedWishes.isNotEmpty()) {
                                    Spacer(modifier = Modifier.height(12.dp))
                                    FlowRow(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalArrangement = Arrangement.Center,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        selectedWishes.forEach { wish ->
                                            Surface(
                                                shape = RoundedCornerShape(12.dp),
                                                color = Color(0xFFF3E5F5),
                                                modifier = Modifier.padding(2.dp)
                                            ) {
                                                Text(
                                                    text = wish,
                                                    fontSize = 12.sp,
                                                    color = Color(0xFF6A1B9A),
                                                    fontWeight = FontWeight.SemiBold,
                                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Quick Wish Selector Chips
                        Text(
                            text = "Thêm lời chúc nhanh:",
                            fontSize = 13.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(bottom = 6.dp)
                        )

                        FlowRow(
                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            quickWishes.forEach { wish ->
                                val isSelected = wish in selectedWishes
                                FilterChip(
                                    selected = isSelected,
                                    onClick = {
                                        selectedWishes = if (isSelected) {
                                            selectedWishes - wish
                                        } else {
                                            selectedWishes + wish
                                        }
                                    },
                                    label = { Text(wish, fontSize = 12.sp) },
                                    colors = FilterChipDefaults.filterChipColors(
                                        selectedContainerColor = Color(0xFFE1BEE7),
                                        selectedLabelColor = Color(0xFF4A148C)
                                    ),
                                    shape = RoundedCornerShape(16.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(24.dp))

                        // Wish Action Button
                        val buttonScale by animateFloatAsState(
                            targetValue = if (wishCount > 0) 1.02f else 1f,
                            animationSpec = tween(200),
                            label = "buttonScale"
                        )

                        Button(
                            onClick = { wishCount++ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(54.dp)
                                .scale(buttonScale),
                            shape = RoundedCornerShape(27.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFD81B60)
                            ),
                            elevation = ButtonDefaults.buttonElevation(
                                defaultElevation = 6.dp,
                                pressedElevation = 2.dp
                            )
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Text(
                                    text = if (wishCount == 0) "🎁 GỬI LỜI CHÚC" else "💖 GỬI THÊM LỜI CHÚC",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    letterSpacing = 1.sp
                                )
                            }
                        }

                        // Wish Counter Badge
                        AnimatedVisibility(
                            visible = wishCount > 0,
                            enter = fadeIn() + scaleIn()
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(top = 16.dp)
                            ) {
                                Surface(
                                    shape = RoundedCornerShape(16.dp),
                                    color = Color(0xFFFFF0F5),
                                    border = BorderStroke(1.dp, Color(0xFFF48FB1))
                                ) {
                                    Text(
                                        text = "🎉 Đã gửi $wishCount lời chúc yêu thương! ❤️",
                                        color = Color(0xFFC2185B),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Footer note
                Text(
                    text = "Made with ❤️ for Birthday Celebration",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BirthdayCardPreview() {
    BirthdayCardApp()
}

