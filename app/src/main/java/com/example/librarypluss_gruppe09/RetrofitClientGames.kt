package com.example.librarypluss_gruppe09

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type


//Custom converter for retrofit
class ToStringConverterFactory : Converter.Factory() {
    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        if (type == String::class.java) {
            return Converter<String, RequestBody> { value ->
                value.toRequestBody("text/plain".toMediaType())
            }
        }
        return null
    }
}

//For feilsøking, trengs ikke i ferdig versjon.
val logging = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val OkHttpClient = OkHttpClient.Builder()
    //.addInterceptor(logging)
    .addInterceptor { chain ->
        val originalRequest = chain.request()
        val requestWithPlainText = originalRequest.newBuilder()
            .header("Content-Type", "text/plain")
            .method(originalRequest.method, originalRequest.body)
            .build()
        chain.proceed(requestWithPlainText)
    }
    .build()

val retrofitGames = Retrofit.Builder()
    .baseUrl("https://api.igdb.com/v4/")
    .client(com.example.librarypluss_gruppe09.OkHttpClient)
    .addConverterFactory(ToStringConverterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .build()
