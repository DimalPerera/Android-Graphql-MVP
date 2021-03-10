package com.developers.apollographqlexample.network

import com.apollographql.apollo.ApolloClient
import com.developers.apollographqlexample.BuildConfig
import okhttp3.OkHttpClient

class ApiEndPoint {

    private val BASE_URL = "https://api.github.com/graphql"

    fun setupApollo(): ApolloClient {
        val okHttp = OkHttpClient
                .Builder()
                .addInterceptor { chain ->
                    val original = chain.request()
                    val builder = original.newBuilder().method(original.method(),
                            original.body())
                    builder.addHeader("Authorization", "Bearer " + BuildConfig.AUTH_TOKEN)
                    chain.proceed(builder.build())
                }
                .build()
        return ApolloClient.builder()
                .serverUrl(BASE_URL)
                .okHttpClient(okHttp)
                .build()
    }

}