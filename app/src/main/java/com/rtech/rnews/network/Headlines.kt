package com.rtech.rnews.network

import com.squareup.moshi.Json

data class Headlines(
    val articles:List<Article>
)

data class Article(
    val author:String?="Unknown",
    val title:String?="Unknown",
    val description:String?="Unknown",
    val urlToImage:String?="https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.publicdomainpictures.net%2Fen%2Fview-image.php%3Fimage%3D263441%26picture%3Dnewsletter-news-app-article&psig=AOvVaw0KkLCDWEVxDUaFg6kA7bDo&ust=1635312269317000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJDn7s-s5_MCFQAAAAAdAAAAABAD",
    val url:String?="https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.publicdomainpictures.net%2Fen%2Fview-image.php%3Fimage%3D263441%26picture%3Dnewsletter-news-app-article&psig=AOvVaw0KkLCDWEVxDUaFg6kA7bDo&ust=1635312269317000&source=images&cd=vfe&ved=0CAsQjRxqFwoTCJDn7s-s5_MCFQAAAAAdAAAAABAD",
    val source:Source
)

data class Source(
    @Json(name = "name")val name:String
)