package com.submission.core.di

import android.content.Context
import androidx.room.Room
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.submission.core.data.source.JobRepositoryImpl
import com.submission.core.data.source.local.LocalDataSource
import com.submission.core.data.source.local.room.JobDao
import com.submission.core.data.source.local.room.JobDatabase
import com.submission.core.data.source.remote.RemoteDataSource
import com.submission.core.data.source.remote.network.ApiService
import com.submission.core.domain.repository.JobRepository
import com.submission.core.domain.usecase.JobInteractor
import com.submission.core.domain.usecase.JobUseCase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module {
    single { provideChuckerInterceptor(get()) }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideDatabase(get()) }
    single { provideDao(get()) }
    single { provideApiService(get()) }
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single<JobRepository> { provideRepository(get(), get()) }
    single<JobUseCase> { provideJobInteractor(get()) }
}


private fun provideChuckerInterceptor(context: Context): ChuckerInterceptor {
    return ChuckerInterceptor.Builder(context).build()
}

private fun provideOkHttpClient(chuckerInterceptor: ChuckerInterceptor): OkHttpClient {
    val certificatePinner = CertificatePinner.Builder()
        .add("jobs.dicoding.com", "sha256/s5BoykB5XSK6MLIgqoC/sXnz+mS1cj5Dqt2M3G8OPYY=")
        .build()
    return OkHttpClient.Builder()
        .addInterceptor(chuckerInterceptor)
        .certificatePinner(certificatePinner)
        .build()
}

private fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://jobs.dicoding.com/api/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

private fun provideDatabase(context: Context): JobDatabase {
    val passphrase: ByteArray = SQLiteDatabase.getBytes("djobs".toCharArray())
    val factory = SupportFactory(passphrase)
    return Room.databaseBuilder(
        context,
        JobDatabase::class.java,
        "djobs.db"
    ).openHelperFactory(factory).build()
}

private fun provideDao(jobDatabase: JobDatabase): JobDao {
    return jobDatabase.favouriteDao()
}

private fun provideApiService(retrofit: Retrofit): ApiService {
    return retrofit.create(ApiService::class.java)
}

private fun provideRepository(
    localDataSource: LocalDataSource,
    remoteDataSource: RemoteDataSource
): JobRepository {
    return JobRepositoryImpl(localDataSource, remoteDataSource)
}

private fun provideJobInteractor(jobRepository: JobRepository): JobUseCase {
    return JobInteractor(jobRepository)
}