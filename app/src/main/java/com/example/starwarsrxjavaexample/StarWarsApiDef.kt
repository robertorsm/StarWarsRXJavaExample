package com.example.starwarsrxjavaexample

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface StarWarsApiDef {

    @GET("films")
    fun listarFilmes():Observable<FilmeResult>

    @GET("people/{idPessoa}")
    fun carregarPessoa(@Path("idPessoa")idPessoa:String): Observable<Person>

}