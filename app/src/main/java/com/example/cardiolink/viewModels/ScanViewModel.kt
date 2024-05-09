package com.example.cardiolink.viewModels

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cardiolink.api.FileApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.util.concurrent.TimeUnit
import kotlin.math.min

class ScanViewModel : ViewModel() {
    private lateinit var context: Context

    fun setContext(context: Context){
        this.context = context
    }

    fun predict(){

    }

    fun upload(uri: Uri){
        val api  = provideFileApi()
        val path = getRealPathFromURI(uri = uri)

        val file = File(path!!)
        val image =  MultipartBody.Part
            .createFormData(
                "image",
                file.name,
                file.asRequestBody()
            )
        viewModelScope.launch {
            val multipartBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("name", "name")
                .addFormDataPart("avatarUrl", file.name, file.asRequestBody())
                .build()



            val result = api.formWithImage2(body = multipartBody)
//
            println("result:${result}")
        }
    }

    private fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    private fun provideOKHttpClientLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun provideOKHttpClientInterceptor(): Interceptor {
        return Interceptor { chain ->
            val original = chain.request()

            val newRequest = original.newBuilder()
                .addHeader("Content-Type","application/json")

                .build()

            chain.proceed(newRequest)
        }
    }

    private fun provideOKHttpClient(logInterceptor: HttpLoggingInterceptor, interceptor: Interceptor): OkHttpClient {
        return  OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logInterceptor)
            .addInterceptor(interceptor)
            .build()
    }

    private fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://postman-echo.com")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
            .build()
    }

    private fun provideFileApi(): FileApi {
        val moshi: Moshi = provideMoshi()
        val logInterceptor: HttpLoggingInterceptor = provideOKHttpClientLoggingInterceptor()
        val client: OkHttpClient = provideOKHttpClient(
            logInterceptor = logInterceptor,
            interceptor = provideOKHttpClientInterceptor()
        )

        val retrofit: Retrofit = provideRetrofit(client = client, moshi = moshi)
        return retrofit.create(FileApi::class.java)

    }

    private fun getRealPathFromURI(uri: Uri ): String? {
        val returnCursor = context.contentResolver.query(uri, null, null, null, null)
        val nameIndex =  returnCursor!!.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        val sizeIndex = returnCursor.getColumnIndex(OpenableColumns.SIZE)
        returnCursor.moveToFirst()
        val name = returnCursor.getString(nameIndex)
        val size = returnCursor.getLong(sizeIndex).toString()
        val file = File(context.filesDir, name)
        try {
            val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
            val outputStream = FileOutputStream(file)
            var read = 0
            val maxBufferSize = 1 * 1024 * 1024
            val bytesAvailable: Int = inputStream?.available() ?: 0
            val bufferSize = min(bytesAvailable, maxBufferSize)
            val buffers = ByteArray(bufferSize)
            while (inputStream?.read(buffers).also {
                    if (it != null) {
                        read = it
                    }
                } != -1) {
                outputStream.write(buffers, 0, read)
            }
            Log.e("File Size", "Size " + file.length())
            inputStream?.close()
            outputStream.close()
            Log.e("File Path", "Path " + file.path)

        } catch (e: java.lang.Exception) {
            Log.e("Exception", e.message!!)
        }
        return file.path
    }
}