package com.payment.pramod.net

import com.payment.pramod.utils.Urls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory.*

class RetrofitClient {
    companion object{
     val instance:Apiservices by lazy{
         val retrofit = Retrofit.Builder()
             .baseUrl(Urls.BASE_URL)
             .addConverterFactory(create())
             .build()
         retrofit.create(Apiservices::class.java)
     }
    }
}