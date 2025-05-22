package com.deepaknishad.d4c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SkincareApp()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkincareApp() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                Text(
                    "Shop", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
            }, navigationIcon = {
                IconButton(onClick = { /* Handle back */ }) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }, actions = {
                IconButton(onClick = { /* Handle search */ }) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                BadgedBox(badge = {
                    Badge(
                        modifier = Modifier.offset(x = (-7).dp, y = 14.dp),
                        containerColor = Color(0xFF00FF00)
                    ) { Text("5", color = Color.Black) }
                }) {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Favorites",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                BadgedBox(badge = {
                    Badge(
                        modifier = Modifier.offset(x = (-7).dp, y = 14.dp),
                        containerColor = Color(0xFF00FF00)
                    ) { Text("3", color = Color.Black) }
                }) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color.White,
                        modifier = Modifier.size(30.dp)
                    )
                }
                Spacer(modifier = Modifier.width(16.dp))
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF3D3D3D))
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF3D3D3D))
        ) {
            // Discount Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shop_flow_card),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth() // Fill the Box
                )
                Column(
                    modifier = Modifier.padding(top = 24.dp), // Adjust for vertical positioning
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        "GET 20% OFF",
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 50.dp, top = 20.dp)
                    )
                    Text(
                        "Get 20% off",
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 50.dp, top = 6.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 50.dp, top = 40.dp, end = 90.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Button(
                            onClick = { /* Handle click */ },
                            modifier = Modifier.height(34.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FF00)),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text("12-16 October", color = Color.Black, fontSize = 14.sp)
                        }

                        Spacer(modifier = Modifier.weight(1f)) // Pushes the image to the end

                        Image(
                            painter = painterResource(id = R.drawable.image_view),
                            contentDescription = "Icon",
                            modifier = Modifier.size(40.dp)
                        )
                    }

                    // Indicators as rounded rectangles
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = 70.dp, top = 58.dp, bottom = 16.dp
                            ), // Adjust padding as needed
                        horizontalArrangement = Arrangement.spacedBy(8.dp), // Space between indicators
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Indicator 1 (active, highlighted)
                        Box(
                            modifier = Modifier
                                .size(
                                    width = 20.dp, height = 8.dp
                                ) // Rectangular shape: wider than tall
                                .background(
                                    color = Color(0xFF00FF00),
                                    shape = RoundedCornerShape(4.dp) // Radius for rounded corners
                                )
                        )
                        // Indicator 2 (inactive)
                        Box(
                            modifier = Modifier
                                .size(
                                    width = 20.dp, height = 8.dp
                                ) // Rectangular shape
                                .background(
                                    color = Color(0xFF1B1B1B),
                                    shape = RoundedCornerShape(4.dp) // Radius for rounded corners
                                )
                        )
                        // Indicator 3 (inactive)
                        Box(
                            modifier = Modifier
                                .size(
                                    width = 20.dp, height = 8.dp
                                ) // Rectangular shape
                                .background(
                                    color = Color(0xFF1B1B1B),
                                    shape = RoundedCornerShape(4.dp) // Radius for rounded corners
                                )
                        )
                    }

                }
            }

            // Categories
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Categories",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "See all", color = Color.White, fontSize = 14.sp, style = TextStyle(
                        textDecoration = TextDecoration.Underline, color = Color.White
                    ) // Add underline
                )
            }
            LazyRow(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(categories) { category ->
                    CategoryItem(category)
                }
            }

            // New Products
            Row(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "New products",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "See all", color = Color.White, fontSize = 14.sp, style = TextStyle(
                        textDecoration = TextDecoration.Underline, color = Color.White
                    ) // Add underline
                )
            }

            // Product List
            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(products) { product ->
                    ProductItem(product)
                }
            }
        }
    }
}

data class Category(val name: String/*, val imageUrl: String*/)

val categories = listOf(
    Category("Cleansers"/*, "https://example.com/cleansers.png"*/),
    Category("Toner"/*, "https://example.com/toner.png"*/),
    Category("Serums"/*, "https://example.com/serums.png"*/),
    Category("Moisturisers"/*, "https://example.com/moisturisers.png"*/),
    Category("Sunscreen"/*, "https://example.com/sunscreen.png"*/)
)

@Composable
fun CategoryItem(category: Category) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(60.dp) // Overall size of the circular container
                .clip(CircleShape) // Clip the entire composable to a circle
                .background(Color(0xFF1B1B1B)), // Background color of the circle
            contentAlignment = Alignment.Center // Center the image inside the Box
        ) {
            Image(
                //            painter = rememberAsyncImagePainter(category.imageUrl),
                painter = painterResource(id = R.drawable.categorysample), // Using drawable resource
                contentDescription = category.name,
                modifier = Modifier.size(50.dp) // Smaller size for the image itself
            )
        }
        Text(
            category.name,
            color = Color.White,
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}

data class Product(
    val name: String,
    val description: String,
    val price: String,
    val originalPrice: String,
    val inStock: Boolean,
    val imageUrl: String,
    val isBestSeller: Boolean
)

val products = listOf(
    Product(
        "clencera",
        "French clay and algae-powered cleanser\nSkin Tightness • Dry & Dehydrated Skin",
        "RS. 355.20",
        "RS. 444.00",
        true,
        "https://example.com/clencera.png",
        true
    ), Product(
        "glow",
        "French clay and algae-powered cleanser\nSkin Tightness • Dry & Dehydrated Skin",
        "RS. 355.20",
        "RS. 444.00",
        true,
        "https://example.com/glow.png",
        false
    ), Product(
        "afterglow",
        "French clay and algae-powered cleanser\nSkin Tightness • Dry & Dehydrated Skin",
        "RS. 355.20",
        "RS. 444.00",
        false,
        "https://example.com/afterglow.png",
        false
    )
)

@Composable
fun ProductItem(product: Product) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(Color(0xFF4A4A4A), Color(0xFF2A2A2A))
                )
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.imageUrl),
                contentDescription = product.name,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
            Column(
                modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        product.name,
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color(0xFF8B5CF6),
                        modifier = Modifier.size(24.dp)
                    )
                }
                if (product.isBestSeller) {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color(0xFF00FF00))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("Best seller", color = Color.Black, fontSize = 12.sp)
                    }
                }
                Text(
                    product.description,
                    color = Color.White,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text(
                        product.price,
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        product.originalPrice,
                        color = Color(0xFF808080),
                        fontSize = 14.sp,
                        textDecoration = TextDecoration.LineThrough
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(8.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (product.inStock) Color(0xFF00FF00) else Color(0xFFFF0000)
                                    )
                            )
                            Text(
                                if (product.inStock) "In stock" else "Out of stock",
                                color = if (product.inStock) Color(0xFF00FF00) else Color(0xFFFF0000),
                                fontSize = 12.sp
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Default.FavoriteBorder,
                                    contentDescription = "Star",
                                    tint = Color(0xFFFFD700),
                                    modifier = Modifier.size(12.dp)
                                )
                            }
                            Text(
                                "249 reviews",
                                color = Color(0xFFFFD700),
                                fontSize = 12.sp,
                                modifier = Modifier.padding(start = 4.dp)
                            )
                        }
                    }
                    IconButton(
                        onClick = { /* Add to cart */ },
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color(0xFF2A2F30))
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Add to cart",
                            tint = Color(0xFF00FF00),
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}