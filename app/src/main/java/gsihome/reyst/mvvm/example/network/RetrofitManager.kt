package gsihome.reyst.mvvm.example.network

import com.google.gson.Gson
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Class for management of the network requests. We use the Retrofit library.
 * The RetrofitManager allows to get implementation of the services for network-requests.
 *
 * @param baseUrl - it's a base url for requests
 * @param gson - the instance of GSON for conversion answers into DTOs
 */
class RetrofitManager(
    baseUrl: String,
    private val gson: Gson,
    private val headers: Map<String, String>
) {

    private val retrofit = retrofitBuilder(baseUrl).build()

    private fun retrofitBuilder(baseUrl: String) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(httpClient())

    private fun httpClient() = OkHttpClient.Builder()
        .addInterceptor { chain -> setRequestHeaders(chain) }
        .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        .build()

    private fun setRequestHeaders(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .addExtraHeaders(headers)
            .method(original.method, original.body)
            .build()
        return chain.proceed(request)
    }

    private fun Request.Builder.addExtraHeaders(headers: Map<String, String>): Request.Builder {
        headers.entries.forEach { (name, value) -> addHeader(name, value) }
        return this
    }


    /**
     * This method returns implementation for api interfaces
     *
     * @param apiClass - interface with descriptions of the requests
     * @return implementation of the passed interface
     */
    fun <T> getService(apiClass: Class<T>): T {
        return retrofit.create(apiClass)
    }
}

