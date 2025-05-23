package com.deepaknishad.d4c

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E1E1E))
            )
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1E1E1E)), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                // Discount Banner
                DiscountBanner()
            }

            item {
                // Categories Row Title
                CategoryHeader()
            }

            item {
                // Horizontal scroll of categories
                LazyRow(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(categories) { category ->
                        CategoryItem(category)
                    }
                }
            }

            item {
                // New Products Header
                NewProductHeader()
            }

            // Product List
            itemsIndexed(products) { index, product ->
                ProductItem(product = product, isLastItem = index == products.lastIndex)
            }
        }
    }
}

@Composable
fun NewProductHeader() {
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
            "New products", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold
        )
        Text(
            "See all", color = Color.White, fontSize = 14.sp, style = TextStyle(
                textDecoration = TextDecoration.Underline, color = Color.White
            ) // Add underline
        )
    }
}

@Composable
fun CategoryHeader() {
    // Categories
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Categories", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold
        )
        Text(
            "See all", color = Color.White, fontSize = 14.sp, style = TextStyle(
                textDecoration = TextDecoration.Underline, color = Color.White
            ) // Add underline
        )
    }
}

@Composable
fun DiscountBanner() {
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
                            color = Color(0xFF111111),
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
                            color = Color(0xFF111111),
                            shape = RoundedCornerShape(4.dp) // Radius for rounded corners
                        )
                )
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
                .background(Color(0xFF111111)), // Background color of the circle
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
fun ProductItem(product: Product, isLastItem: Boolean) {/*Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp, top = 0.dp, bottom = if (isLastItem) 40.dp else 0.dp
            )
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
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = { *//* Add to cart *//* },
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
    }*/

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = if (isLastItem) 40.dp else 0.dp)
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.card_grey_bg),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth() // Fill the Box
        )
        Column(
            modifier = Modifier.padding(top = 12.dp), // Adjust for vertical positioning
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 24.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                // Favorite Icon with Circular Background
                Box(
                    modifier = Modifier
                        .size(40.dp) // Size of the background circle
                        .clip(CircleShape)
                        .background(Color.Black), // Dark gray to blend with the gradient
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (product.isBestSeller) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color(0xFF8B5CF6),
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                if (product.isBestSeller) {
                    Button(
                        onClick = { /* Handle click */ },
                        modifier = Modifier.height(34.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            "Best Seller",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00FF00),
                            fontSize = 14.sp
                        )
                    }
                }

            }

            // Product Image Below the Row, Centered
            Image(
                painter = painterResource(id = R.drawable.product_image),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(
                        width = 800.dp, height = 350.dp
                    ) // Set specific width and height
                    .align(Alignment.CenterHorizontally) // Center the image horizontally
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp)
                    .offset(y = (-7).dp)
                    .wrapContentHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.card_black_shape),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth() // Fill the Box
                )

                Column(
                    modifier = Modifier.padding(
                        top = 12.dp, start = 14.dp, end = 14.dp
                    ), // Adjust for vertical positioning
                    horizontalAlignment = Alignment.Start
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            product.name,
                            color = Color(0xFF00FF00),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.tangerine, FontWeight.Bold))
                        )
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
                    }

                    Text(
                        product.description,
                        color = Color.White,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = 30.dp)
                    ) {
                        Text(
                            product.price,
                            color = Color(0xFF8B5CF6),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            product.originalPrice,
                            color = Color(0xFF808080),
                            fontSize = 16.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            modifier = Modifier.padding(top = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Star",
                                    tint = Color(0xFFFFD700),
                                    modifier = Modifier.size(18.dp)
                                )
                            }

                            Text(
                                "249 reviews",
                                color = Color.White,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(start = 4.dp),
                                style = TextStyle(
                                    textDecoration = TextDecoration.Underline, color = Color.White
                                ) // Add underline
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .offset(x = (-2).dp, y = (-36).dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp) // Size of the background circle
                                .clip(CircleShape)
                                .border(
                                    width = 2.dp, // Thickness of the outline
                                    color = Color(0xFF00FF00), // Color of the outline
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.cart3),
                                contentDescription = "Cart",
                                colorFilter = ColorFilter.tint(Color(0xFF00FF00)),
                                modifier = Modifier.size(26.dp)
                            )
                        }
                    }

                }
            }

        }
    }

}