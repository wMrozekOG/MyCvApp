package com.example.mycvapp.di

import androidx.room.Room
import com.example.mycvapp.database.MIGRATION_1_2
import com.example.mycvapp.database.PersonDatabase
import com.example.mycvapp.model.Api
import com.example.mycvapp.model.PersonRepository
import com.example.mycvapp.model.PersonResource
import com.example.mycvapp.model.RequestApi
import com.example.mycvapp.viewmodel.MainViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val applicationModule = module {
    single {
        Room.databaseBuilder(get(), PersonDatabase::class.java, "personDb")
            .addMigrations(MIGRATION_1_2)
            .build()
    }

    factory { PersonResource(get(Api::class.java), get(PersonDatabase::class.java)) }

    factory { PersonRepository(get(PersonResource::class.java)) }

    viewModel { MainViewModel(get(PersonRepository::class.java)) }
}

val networkModule = module {
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single<Converter.Factory> { GsonConverterFactory.create(GsonBuilder().create()) }

    single<RequestApi> {
        Retrofit.Builder()
            .baseUrl("https://gist.githack.com/wMrozekOG/0c17f6380112e5f8f2b1ac34f1fc84e8/raw/323bf2c14d903712406923df057521515bf9c446/")
            .addConverterFactory(get(Converter.Factory::class.java))
            .client(get(OkHttpClient::class.java)).build().create(RequestApi::class.java)
    }

    single { Api(get(RequestApi::class.java)) }
}