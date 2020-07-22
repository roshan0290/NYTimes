package com.nytimes.data.network


import com.nytimes.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


object RetrofitClient {

    var retrofit: Retrofit? = null


    val client = OkHttpClient().newBuilder().connectTimeout(7000, TimeUnit.SECONDS)
            .addInterceptor(HttpLoggingInterceptor().apply {
                level =
                        if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            .build()


    /**
     * Create an instance of Retrofit object
     */
    val  retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {

                retrofit = Retrofit.Builder()
                        .baseUrl("https://api.nytimes.com/svc/mostpopular/v2/viewed/")
                        //.client(client)
                        .client(getUnsafeOkHttpClient().build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }


    fun getUnsafeOkHttpClient(): OkHttpClient.Builder {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun getAcceptedIssuers(): Array<X509Certificate> {
                    return arrayOf()
                }

                @Throws(CertificateException::class)
                override fun checkClientTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }

                @Throws(CertificateException::class)
                override fun checkServerTrusted(chain: Array<java.security.cert.X509Certificate>, authType: String) {
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.getSocketFactory()

            val builder = OkHttpClient.Builder()
            builder.addInterceptor(HttpLoggingInterceptor().apply {
                level =
                        if (BuildConfig.DEBUG)
                            HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
            })
            builder.sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
            builder.hostnameVerifier(object : HostnameVerifier {
                override fun verify(hostname: String, session: SSLSession): Boolean {
                    return true
                }
            })
            return builder
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

}