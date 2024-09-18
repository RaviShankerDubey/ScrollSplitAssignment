package com.assignment.scrollsplitassignment.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * Created by Ravi Sankar on 10/12/2023.
 */
object ApiFactory {

    //https://jsonkeeper.com/b/5BEJ
    private val BASE_URL = "https://jsonkeeper.com/"

    @JvmStatic
    val client: Retrofit
        get() {
            val httpClient = OkHttpClient.Builder().hostnameVerifier(object : HostnameVerifier {
                override fun verify(p0: String?, p1: SSLSession?): Boolean {
                    return true
                }
            })
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
            httpClient.readTimeout(600, TimeUnit.SECONDS)
            httpClient.connectTimeout(600, TimeUnit.SECONDS)
            val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").setLenient().create()
            return Retrofit.Builder().baseUrl(BASE_URL).client(httpClient.build()).addConverterFactory(GsonConverterFactory.create(gson)).build()
        }

}