package com.ayub.khosa.myloginapplication.api


import android.webkit.CookieManager
import com.ayub.khosa.myloginapplication.utils.PrintLogs
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.net.CookieHandler
import java.net.CookiePolicy
import java.util.concurrent.TimeUnit

@Module
@InstallIn(SingletonComponent::class)
object RetrofitBuilder {


    @Provides
    fun providesLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    fun providesBaseUrl(): String {
        val BASE_URL = "https://ayubkhosa.com/"
        return BASE_URL
    }


    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        PrintLogs.printD("------------ new OKHttpClient ------------")

        lateinit var cookieManager: CookieManager
        // init cookie manager
        val myHttpClient: OkHttpClient by lazy {
            CookieHandler.setDefault(java.net.CookieManager(null, CookiePolicy.ACCEPT_ALL))

            val ins = OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .cookieJar(object : CookieJar {

                    /**
                     * @param url
                     * @param cookies list of cookies get in api response
                     */
                    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {

                        cookieManager = CookieManager.getInstance()
                        for (cookie in cookies) {
                            cookieManager.setCookie(url.toString(), cookie.toString())
                            PrintLogs.printD(
                                "saveFromResponse :  Cookie url : " + url.toString() + cookie.toString()
                            )
                        }
                    }

                    /**
                     * @param url
                     *
                     * adding cookies with request
                     */
                    override fun loadForRequest(url: HttpUrl): List<Cookie> {
                        cookieManager = CookieManager.getInstance()

                        val cookies: ArrayList<Cookie> = ArrayList()
                        if (cookieManager.getCookie(url.toString()) != null) {
                            val splitCookies =
                                cookieManager.getCookie(url.toString()).split("[,;]".toRegex())
                                    .dropLastWhile { it.isEmpty() }.toTypedArray()
                            for (i in splitCookies.indices) {
                                cookies.add(Cookie.parse(url, splitCookies[i].trim { it <= ' ' })!!)
                                PrintLogs.printD(
                                    "loadForRequest :Cookie.add ::  " + Cookie.parse(
                                        url,
                                        splitCookies[i].trim { it <= ' ' })!!
                                )
                            }
                        }
                        return cookies
                    }
                })
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
            ins.build()

        }
        return myHttpClient
    }


    @Provides
    fun getRetrofit(myHttpClient: OkHttpClient, baseUrl: String): Retrofit {
        var gson: Gson = GsonBuilder()
            .setLenient()
            .create()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addConverterFactory(ScalarsConverterFactory.create())
            .client(myHttpClient)
            .build()
    }


    @Provides
    fun provideRestApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}