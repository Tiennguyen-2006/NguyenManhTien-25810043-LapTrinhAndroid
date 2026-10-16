package com.example.tiembanhvuive

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

// Color Palette
private val PinkPrimary = Color(0xFFE91E63)
private val PinkLight = Color(0xFFFCE4EC)
private val PinkDark = Color(0xFFC2185B)
private val BackgroundColor = Color(0xFFFFF8F9)
private val TextDark = Color(0xFF2D2727)
private val TextGray = Color(0xFF757575)

private val SimpleBakeryColorScheme = lightColorScheme(
    primary = PinkPrimary,
    onPrimary = Color.White,
    primaryContainer = PinkLight,
    onPrimaryContainer = PinkDark,
    background = BackgroundColor,
    surface = Color.White,
    onSurface = TextDark
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme(colorScheme = SimpleBakeryColorScheme) {
                CupcakeNavigationApp()
            }
        }
    }
}

@Composable
fun CupcakeNavigationApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {
        composable("welcome") {
            WelcomeScreen(
                onStart = { navController.navigate("quantity") }
            )
        }

        composable("quantity") {
            QuantityScreen(
                onQuantitySelected = { quantity ->
                    navController.navigate("flavor/$quantity")
                }
            )
        }

        composable(
            route = "flavor/{quantity}",
            arguments = listOf(navArgument("quantity") { type = NavType.IntType })
        ) { entry ->
            val quantity = entry.arguments?.getInt("quantity") ?: 1
            FlavorScreen(
                quantity = quantity,
                onFlavorSelected = { flavor ->
                    navController.navigate("summary/$quantity/${Uri.encode(flavor)}")
                }
            )
        }

        composable(
            route = "summary/{quantity}/{flavor}",
            arguments = listOf(
                navArgument("quantity") { type = NavType.IntType },
                navArgument("flavor") { type = NavType.StringType }
            )
        ) { entry ->
            val quantity = entry.arguments?.getInt("quantity") ?: 1
            val flavor = entry.arguments?.getString("flavor").orEmpty()
            SummaryScreen(
                quantity = quantity,
                flavor = flavor,
                onOrderAgain = {
                    navController.navigate("welcome") {
                        popUpTo("welcome") { inclusive = true }
                    }
                }
            )
        }
    }
}

// 1. Welcome Screen
@Composable
fun WelcomeScreen(onStart: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(PinkLight),
                contentAlignment = Alignment.Center
            ) {
                Text("🧁", fontSize = 50.sp)
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "TIỆM BÁNH VUI VẺ",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    color = PinkDark
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Đặt bánh nhỏ cho một ngày vui!",
                style = MaterialTheme.typography.bodyLarge.copy(color = TextGray),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onStart,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Bắt đầu đặt bánh ✨", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// 2. Quantity Screen
@Composable
fun QuantityScreen(onQuantitySelected: (Int) -> Unit) {
    var selectedQuantity by remember { mutableIntStateOf(6) }
    val options = listOf(
        1 to "1 cái bánh (Dùng thử)",
        2 to "2 cái bánh (Cặp đôi)",
        6 to "6 cái bánh (Hộp vừa)",
        12 to "12 cái bánh (Hộp lớn)"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Chọn số lượng bánh",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            options.forEach { (qty, label) ->
                val isSelected = selectedQuantity == qty
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { selectedQuantity = qty }
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = if (isSelected) PinkPrimary else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) PinkLight else Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = label,
                            fontSize = 16.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) PinkDark else TextDark
                        )
                        RadioButton(
                            selected = isSelected,
                            onClick = { selectedQuantity = qty },
                            colors = RadioButtonDefaults.colors(selectedColor = PinkPrimary)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onQuantitySelected(selectedQuantity) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Tiếp tục ➔", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// 3. Flavor Screen
@Composable
fun FlavorScreen(quantity: Int, onFlavorSelected: (String) -> Unit) {
    var selectedFlavor by remember { mutableStateOf("Bánh Dâu") }
    val flavors = listOf(
        "Bánh Dâu" to "🍓 Dâu tây tươi ngọt thanh",
        "Bánh Socola" to "🍫 Chocolate đậm đà thơm béo",
        "Bánh Vanilla" to "🍦 Vani kem sữa truyền thống",
        "Bánh Trà Xanh" to "🍵 Matcha thanh mát Nhật Bản"
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Chọn hương vị cho $quantity cái",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            flavors.forEach { (name, desc) ->
                val isSelected = selectedFlavor == name
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable { selectedFlavor = name }
                        .border(
                            width = if (isSelected) 2.dp else 0.dp,
                            color = if (isSelected) PinkPrimary else Color.Transparent,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isSelected) PinkLight else Color.White
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) PinkDark else TextDark
                            )
                            Text(
                                text = desc,
                                fontSize = 13.sp,
                                color = TextGray
                            )
                        }
                        RadioButton(
                            selected = isSelected,
                            onClick = { selectedFlavor = name },
                            colors = RadioButtonDefaults.colors(selectedColor = PinkPrimary)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { onFlavorSelected(selectedFlavor) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Xem đơn hàng ➔", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

// 4. Summary Screen
@Composable
fun SummaryScreen(quantity: Int, flavor: String, onOrderAgain: () -> Unit) {
    val total = quantity * 25000

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = BackgroundColor
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Đơn hàng của bạn 🎉",
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextDark
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White),
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Số lượng:", color = TextGray)
                        Text("$quantity cái bánh", fontWeight = FontWeight.Bold, color = TextDark)
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Hương vị:", color = TextGray)
                        Text(flavor, fontWeight = FontWeight.Bold, color = TextDark)
                    }

                    Spacer(modifier = Modifier.height(12.dp))
                    HorizontalDivider(color = PinkLight)
                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Tổng tiền:", fontWeight = FontWeight.Bold, color = TextDark)
                        Text("$total VNĐ", fontWeight = FontWeight.Bold, fontSize = 18.sp, color = PinkDark)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = onOrderAgain,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text("Đặt đơn hàng mới 🔄", fontSize = 16.sp, fontWeight = FontWeight.Bold)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BakeryPreview() {
    MaterialTheme(colorScheme = SimpleBakeryColorScheme) {
        WelcomeScreen(onStart = {})
    }
}
