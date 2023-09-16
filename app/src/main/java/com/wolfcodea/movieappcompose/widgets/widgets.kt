package com.wolfcodea.movieappcompose.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.wolfcodea.movieappcompose.model.Movie
import com.wolfcodea.movieappcompose.model.getMovies
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.wolfcodea.movieappcompose.R


@Composable
fun MovieRow(movie: Movie = getMovies()[0], onItemClick: (String) -> Unit = {}) {
    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
    var expanded by remember {
        mutableStateOf(false)
    }
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .clickable {
                onItemClick(movie.id)
            },
        shape = RoundedCornerShape(corner = CornerSize(16.dp)),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,

                ) {
                AsyncImage(
                    movie.images.first(),
                    modifier = Modifier.clip(
                        shape = RoundedCornerShape(
                            topStart = 16.dp,
                            topEnd = 16.dp,
                            bottomEnd = 16.dp,
                            bottomStart = 16.dp,
                        )
                    ),
                    alignment = Alignment.Center,
                    contentDescription = "Movie Image",
                    contentScale = ContentScale.Crop,
                )
            }
            Column(modifier = Modifier.padding(4.dp)) {
                val typography = MaterialTheme.typography
                //TODO
                val ralewayFont = GoogleFont(name = "Raleway")
                val ralewayFontFamily = FontFamily(
                    Font(googleFont = ralewayFont, fontProvider = provider)
                )

                val openSans = GoogleFont(name = "Open Sans")
                val openSansFontFamily = FontFamily(
                    Font(googleFont = openSans, fontProvider = provider)
                )

                Text(text = movie.title, style = typography.titleLarge, fontSize = 20.sp)
                Text(
                    text = "Director: ${movie.director}",
                    style = typography.titleSmall,
                    fontFamily = ralewayFontFamily,
                    fontSize = 16.sp,
                )
                Text(
                    text = "Released: ${movie.year}",
                    style = typography.titleSmall,
                    fontFamily = ralewayFontFamily,
                    fontSize = 16.sp,
                )
                AnimatedVisibility(visible = expanded) {
                    Column(modifier = Modifier.padding(6.dp)) {
                        Text(buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontFamily = openSansFontFamily
                                )
                            ) {
                                append("Plot: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Black,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light,
                                    fontFamily = openSansFontFamily
                                )
                            ) {
                                append(movie.plot)
                            }
                        }, )
                        Divider()
                        Text(
                            text = "Director: ${movie.director}",
                            style = typography.titleSmall,
                            fontFamily = ralewayFontFamily,
                        )
                        Text(
                            text = "Actors: ${movie.actors}",
                            style = typography.titleSmall,
                            fontFamily = ralewayFontFamily,
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            style = typography.titleSmall,
                            fontFamily = ralewayFontFamily,
                        )


                    }
                }
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    TextButton(onClick = { expanded = !expanded }) {
                        Text(text = "Show Plot")
                        Icon(
                            imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Show Content",
                            modifier = Modifier
                                .size(20.dp),
                            tint = Color.DarkGray
                        )
                    }

                }
            }

        }


    }
}