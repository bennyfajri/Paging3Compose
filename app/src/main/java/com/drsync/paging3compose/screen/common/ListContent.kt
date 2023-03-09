package com.drsync.paging3compose.screen.common

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.drsync.paging3compose.R
import com.drsync.paging3compose.model.*

@ExperimentalCoilApi
@Composable
fun ListContent(items: LazyPagingItems<UnsplashImage>) {
    Log.d("Error: ", "ListContent: ${items.itemCount}")
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = items,
            key = { unsplashImage ->
                unsplashImage.id
            }
        ) { unsplashImage ->
            unsplashImage?.let { UnsplashItem(unsplashImage = it) }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun UnsplashItem(unsplashImage: UnsplashImage) {
    val painter = rememberImagePainter(data = unsplashImage.urls.regular) {
        crossfade(durationMillis = 1000)
        error(R.drawable.ic_placeholder)
        placeholder(R.drawable.ic_placeholder)
    }
    val profilePicture = rememberImagePainter(data = unsplashImage.user.profileImage.small) {
        crossfade(durationMillis = 400)
        error(R.drawable.ic_placeholder)
        placeholder(R.drawable.ic_placeholder)
    }

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .clickable {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://unsplash.com/@${unsplashImage.user.username}?utm_source=DemoApp&utm_medium=referral")
                )
                startActivity(context, browserIntent, null)
            }
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape),
                painter = profilePicture,
                contentDescription = "Profile Picture",
                contentScale = ContentScale.Crop,

                )
            Text(
                modifier = Modifier.padding(12.dp),
                text = unsplashImage.user.username,
                style = MaterialTheme.typography.subtitle1,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp),
            painter = painter,
            contentDescription = "Unsplash Image",
            contentScale = ContentScale.FillHeight
        )
        LikeCounter(
            modifier = Modifier
                .padding(8.dp),
            painter = painterResource(id = R.drawable.ic_heart),
            likes = "${unsplashImage.likes}"
        )
    }
}

@Composable
fun LikeCounter(
    modifier: Modifier,
    painter: Painter,
    likes: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Icon(
            painter = painter,
            contentDescription = "Heart Icon",
            tint = Color.Gray
        )
        Divider(modifier = Modifier.width(6.dp))
        Text(
            text = buildAnnotatedString {
                append(likes)
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp
                    )
                ) {
                    append(" • on Unsplash")
                }

            },
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun UnsplashImagePreview() {
    UnsplashItem(
        unsplashImage = UnsplashImage(
            id = "1",
            urls = Urls(regular = ""),
            likes = 100,
            user = User(
                username = "Benny",
                userLinks = UserLinks(html = ""),
                profileImage = ProfileImage(small = "")
            )
        )
    )
}