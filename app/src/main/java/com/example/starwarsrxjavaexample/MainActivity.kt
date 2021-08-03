package com.example.starwarsrxjavaexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    lateinit var  listview: ListView
    lateinit var  movieAdapter: ArrayAdapter<String>

    var filmes = mutableListOf<String>()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listview  = ListView(this)
        setContentView(listview)
        movieAdapter = ArrayAdapter(this,android.R.layout.simple_list_item_1, filmes)
        listview.adapter = movieAdapter

        val api = StarWarsApi()
        api.carregarFilmes()?.compose(api.aplicarTransformer())
            ?.subscribe({ filme ->
                run {
                    filmes.add("${filme.title} -- ${filme.episodeId}")
                }
            },{ e->
                e.printStackTrace()
            },{
                movieAdapter.notifyDataSetChanged()
            })

    }
}