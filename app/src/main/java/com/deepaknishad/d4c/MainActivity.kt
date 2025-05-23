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
import androidx.compose.ui.res.dimensionResource
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
                    "Shop",
                    color = Color.White,
                    fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._18ssp).value.sp,
                    fontWeight = FontWeight.Bold
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
                        modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._24sdp))
                    )
                }
                BadgedBox(badge = {
                    Badge(
                        modifier = Modifier.offset(
                            x = dimensionResource(id = com.intuit.sdp.R.dimen._minus5sdp).value.dp,
                            y = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp).value.dp
                        ), containerColor = Color(0xFF00FF00)
                    ) { Text("5", color = Color.Black) }
                }) {
                    Icon(
                        Icons.Default.FavoriteBorder,
                        contentDescription = "Favorites",
                        tint = Color.White,
                        modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._24sdp))
                    )
                }
                Spacer(modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)))
                BadgedBox(badge = {
                    Badge(
                        modifier = Modifier.offset(
                            x = dimensionResource(id = com.intuit.sdp.R.dimen._minus5sdp).value.dp,
                            y = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp).value.dp
                        ), containerColor = Color(0xFF00FF00)
                    ) { Text("3", color = Color.Black) }
                }) {
                    Icon(
                        Icons.Default.ShoppingCart,
                        contentDescription = "Cart",
                        tint = Color.White,
                        modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._24sdp))
                    )
                }
                Spacer(modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._14sdp)))
            }, colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF1E1E1E))
            )
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color(0xFF1E1E1E)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._14sdp))
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
                    modifier = Modifier
                        .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._14sdp))
                        .offset(y = dimensionResource(id = com.intuit.sdp.R.dimen._minus6sdp)),
                    horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._14sdp))
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
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._16sdp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "New products",
            color = Color.White,
            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._18ssp).value.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "See all",
            color = Color.White,
            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp,
            style = TextStyle(
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
            .offset(y = dimensionResource(id = com.intuit.sdp.R.dimen._minus5sdp))
            .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._14sdp)),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            "Categories",
            color = Color.White,
            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._18ssp).value.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            "See all",
            color = Color.White,
            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp,
            style = TextStyle(
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
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                "GET 20% OFF",
                color = Color.White,
                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._20ssp).value.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    start = dimensionResource(id = com.intuit.sdp.R.dimen._32sdp),
                    top = dimensionResource(id = com.intuit.sdp.R.dimen._28sdp)
                )
            )
            Text(
                "Get 20% off",
                color = Color.White,
                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp,
                modifier = Modifier.padding(
                    start = dimensionResource(id = com.intuit.sdp.R.dimen._32sdp),
                    top = dimensionResource(id = com.intuit.sdp.R.dimen._4sdp)
                )
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = com.intuit.sdp.R.dimen._32sdp),
                        top = dimensionResource(id = com.intuit.sdp.R.dimen._24sdp),
                        end = dimensionResource(id = com.intuit.sdp.R.dimen._80sdp)
                    ), verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { /* Handle click */ },
                    modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._28sdp)),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF00FF00)),
                    shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._14sdp))
                ) {
                    Text(
                        "12-16 October",
                        color = Color.Black,
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                }

                Spacer(modifier = Modifier.weight(1f)) // Pushes the image to the end

                Image(
                    painter = painterResource(id = R.drawable.image_view),
                    contentDescription = "Icon",
                    modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._32sdp))
                )
            }

            // Indicators as rounded rectangles
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = com.intuit.sdp.R.dimen._50sdp),
                        top = dimensionResource(id = com.intuit.sdp.R.dimen._46sdp),
                        bottom = dimensionResource(id = com.intuit.sdp.R.dimen._14sdp)
                    ), // Adjust padding as needed
                horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)), // Space between indicators
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Indicator 1 (active, highlighted)
                Box(
                    modifier = Modifier
                        .size(
                            width = dimensionResource(id = com.intuit.sdp.R.dimen._18sdp),
                            height = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)
                        ) // Rectangular shape: wider than tall
                        .background(
                            color = Color(0xFF00FF00),
                            shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._3sdp)) // Radius for rounded corners
                        )
                )
                // Indicator 2 (inactive)
                Box(
                    modifier = Modifier
                        .size(
                            width = dimensionResource(id = com.intuit.sdp.R.dimen._18sdp),
                            height = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)
                        ) // Rectangular shape
                        .background(
                            color = Color(0xFF111111),
                            shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._3sdp)) // Radius for rounded corners
                        )
                )
                // Indicator 3 (inactive)
                Box(
                    modifier = Modifier
                        .size(
                            width = dimensionResource(id = com.intuit.sdp.R.dimen._18sdp),
                            height = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)
                        ) // Rectangular shape
                        .background(
                            color = Color(0xFF111111),
                            shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._3sdp)) // Radius for rounded corners
                        )
                )
            }

        }
    }
}

data class Category(val name: String)

val categories = listOf(
    Category("Cleansers"),
    Category("Toner"),
    Category("Serums"),
    Category("Moisturisers"),
    Category("Sunscreen")
)

@Composable
fun CategoryItem(category: Category) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(dimensionResource(id = com.intuit.sdp.R.dimen._46sdp)) // Overall size of the circular container
                .clip(CircleShape) // Clip the entire composable to a circle
                .background(Color(0xFF111111)), // Background color of the circle
            contentAlignment = Alignment.Center // Center the image inside the Box
        ) {
            Image(
                //            painter = rememberAsyncImagePainter(category.imageUrl),
                painter = painterResource(id = R.drawable.categorysample), // Using drawable resource
                contentDescription = category.name,
                modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._36sdp)) // Smaller size for the image itself
            )
        }
        Text(
            category.name,
            color = Color.White,
            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp,
            modifier = Modifier.padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._4sdp))
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
fun ProductItem(product: Product, isLastItem: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = if (isLastItem) dimensionResource(id = com.intuit.sdp.R.dimen._30sdp) else 0.dp)
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.card_grey_bg),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth() // Fill the Box
        )
        Column(
            horizontalAlignment = Alignment.Start
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = dimensionResource(id = com.intuit.sdp.R.dimen._10sdp),
                        top = dimensionResource(id = com.intuit.sdp.R.dimen._4sdp),
                        end = dimensionResource(id = com.intuit.sdp.R.dimen._20sdp)
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {

                // Favorite Icon with Circular Background
                Box(
                    modifier = Modifier
                        .size(dimensionResource(id = com.intuit.sdp.R.dimen._32sdp)) // Size of the background circle
                        .clip(CircleShape)
                        .background(Color.Black), // Dark gray to blend with the gradient
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = if (product.isBestSeller) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = Color(0xFF8B5CF6),
                        modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._20sdp))
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                if (product.isBestSeller) {
                    Button(
                        onClick = { /* Handle click */ },
                        modifier = Modifier
                            .padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp))
                            .height(dimensionResource(id = com.intuit.sdp.R.dimen._28sdp)),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._14sdp))
                    ) {
                        Text(
                            "Best Seller",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF00FF00),
                            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp
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
                        width = dimensionResource(id = com.intuit.sdp.R.dimen._500sdp),
                        height = dimensionResource(id = com.intuit.sdp.R.dimen._250sdp)
                    ) // Set specific width and height
                    .align(Alignment.CenterHorizontally) // Center the image horizontally
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._16sdp))
                    .offset(y = dimensionResource(id = com.intuit.sdp.R.dimen._minus6sdp))
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
                        top = dimensionResource(id = com.intuit.sdp.R.dimen._10sdp),
                        start = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp),
                        end = dimensionResource(id = com.intuit.sdp.R.dimen._12sdp)
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
                            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._14ssp).value.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily(Font(R.font.tangerine, FontWeight.Bold))
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._3sdp)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(dimensionResource(id = com.intuit.sdp.R.dimen._6sdp))
                                    .clip(CircleShape)
                                    .background(
                                        if (product.inStock) Color(0xFF00FF00) else Color(0xFFFF0000)
                                    )
                            )
                            Text(
                                if (product.inStock) "In stock" else "Out of stock",
                                color = if (product.inStock) Color(0xFF00FF00) else Color(0xFFFF0000),
                                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp
                            )
                        }
                    }

                    Text(
                        product.description,
                        color = Color.White,
                        fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp,
                        modifier = Modifier.padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp))
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._14sdp))
                    ) {
                        Text(
                            product.price,
                            color = Color(0xFF8B5CF6),
                            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._12ssp).value.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            product.originalPrice,
                            color = Color(0xFF808080),
                            fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp,
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Row(
                            modifier = Modifier.padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._3sdp)),
                            horizontalArrangement = Arrangement.spacedBy(dimensionResource(id = com.intuit.sdp.R.dimen._3sdp)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            repeat(5) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Star",
                                    tint = Color(0xFFFFD700),
                                    modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._12sdp))
                                )
                            }

                            Text(
                                "249 reviews",
                                color = Color.White,
                                fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._10ssp).value.sp,
                                modifier = Modifier.padding(start = dimensionResource(id = com.intuit.sdp.R.dimen._3sdp)),
                                style = TextStyle(
                                    textDecoration = TextDecoration.Underline, color = Color.White
                                ) // Add underline
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .padding(top = dimensionResource(id = com.intuit.sdp.R.dimen._2sdp))
                            .fillMaxWidth()
                            .offset(
                                x = dimensionResource(id = com.intuit.sdp.R.dimen._8sdp),
                                y = dimensionResource(id = com.intuit.sdp.R.dimen._minus24sdp)
                            ),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(dimensionResource(id = com.intuit.sdp.R.dimen._40sdp)) // Size of the background circle
                                .clip(CircleShape)
                                .border(
                                    width = dimensionResource(id = com.intuit.sdp.R.dimen._1sdp), // Thickness of the outline
                                    color = Color(0xFF00FF00), // Color of the outline
                                    shape = CircleShape
                                ), contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(R.drawable.cart3),
                                contentDescription = "Cart",
                                colorFilter = ColorFilter.tint(Color(0xFF00FF00)),
                                modifier = Modifier.size(dimensionResource(id = com.intuit.sdp.R.dimen._20sdp))
                            )
                        }
                    }

                }
            }

        }
    }

}