package com.example.starwarsrxjavaexample

import android.net.Uri
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class StarWarsApi {
    private fun  createBase(): StarWarsApiDef {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder()
            .baseUrl("https://swapi.dev/api/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build().create<StarWarsApiDef>(StarWarsApiDef::class.java)
    }

    fun carregarFilmes(): Observable<Movie>?{
        return createBase().listarFilmes()
            .flatMap { filmeResultados ->
                Observable.fromIterable(filmeResultados.results)}
            .map { film -> Movie(film.title,film.episode_id,ArrayList<Character>())}
    }


    fun getObservableFilmes (film: Film): Observable<Movie>{
        return Observable.just(Movie(film.title,film.episode_id,ArrayList<Character>()))
    }

    fun <T> aplicarTransformer(): ObservableTransformer<T,T>{
        return ObservableTransformer { observable ->
            observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
        }
    }

}