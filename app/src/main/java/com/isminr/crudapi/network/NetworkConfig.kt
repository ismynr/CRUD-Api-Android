package com.isminr.crudapi.network

import com.isminr.crudapi.model.ResultStaff
import com.isminr.crudapi.model.ResultStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

object NetworkConfig {

    fun getInterceptor() : OkHttpClient {
        val logging   = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    //RETROFIT
    fun getRetrofit(): Retrofit {

        return Retrofit.Builder()
            .baseUrl("http://localhost/android_api2/index.php/serverapi/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun getService() = getRetrofit().create(StaffService::class.java)
}

interface StaffService {
    //Fungsi Create Data
    @FormUrlEncoded
    @POST("add")
    fun addStaff(@Field("name") name : String,
                 @Field("hp") hp : String,
                 @Field("alamat") alamat : String) :
            Call<ResultStatus>

    //Fungsi Get Data
    @GET("getDataStaff")

    fun getData() : Call<ResultStaff>

    //Fungsi Delete Data
    @FormUrlEncoded
    @POST("deleteStaff")
    fun deleteStaff(@Field("id") id: String?) :
            Call<ResultStatus>

    //Fungsi Update Data
    @FormUrlEncoded
    @POST("updateStaff")
    fun updateStaff(@Field("id") id: String,
                    @Field("name") name: String,
                    @Field("hp") hp : String,
                    @Field("alamat") alamat : String) :
            Call<ResultStatus>
}