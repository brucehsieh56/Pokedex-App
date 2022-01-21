package app.pokedex.common.di

import android.content.Context
import app.pokedex.BuildConfig
import app.pokedex.common.data.PokemonRepository
import app.pokedex.common.data.PokemonRepositoryImpl
import app.pokedex.common.data.remote.PokemonService
import app.pokedex.common.utils.NetworkChecker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providePokemonService(): PokemonService {
        return Retrofit.Builder()
            .baseUrl(PokemonService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createClient())
            .build()
            .create(PokemonService::class.java)
    }

    /**
     * Create a logging interceptor.
     * */
    private fun createClient(): OkHttpClient {
        val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor =
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        return okHttpClientBuilder.build()
    }

    @Provides
    @Singleton
    fun providePokemonRepository(
        networkChecker: NetworkChecker,
        pokemonService: PokemonService
    ): PokemonRepository {
        return PokemonRepositoryImpl(networkChecker, pokemonService)
    }

    @Provides
    @Singleton
    fun provideNetworkChecker(@ApplicationContext context: Context): NetworkChecker {
        return NetworkChecker(context)
    }
}