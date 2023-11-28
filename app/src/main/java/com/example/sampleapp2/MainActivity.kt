package com.example.sampleapp2

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.sampleapp2.adapters.EntryAdapter
import com.example.sampleapp2.adapters.NekoAdapter
import com.example.sampleapp2.adapters.ShibeAdapter
import com.example.sampleapp2.api.NekosBestAPIService
import com.example.sampleapp2.api.PublicAPIService
import com.example.sampleapp2.api.ShibeAPIService
import com.example.sampleapp2.databinding.ActivityMainBinding
import com.example.sampleapp2.model.EntryEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Error
import java.lang.Exception
import java.util.logging.Logger

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val logger = Logger.getLogger(MainActivity::javaClass.name)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.clearButton.setOnClickListener {
            binding.radioGroup.clearCheck()
        }

        var isCheckNotChanged = true
        binding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
            isCheckNotChanged = false
        }
        val radioButtonClickCallback = {
            _: View ->
            if(isCheckNotChanged) {
                binding.radioGroup.clearCheck()
            }
            isCheckNotChanged = true
        }
        binding.radioButton1.setOnClickListener(radioButtonClickCallback)
        binding.radioButton2.setOnClickListener(radioButtonClickCallback)
        runBlocking {
            launch(Dispatchers.IO) {
                updateEntry()
            }
        }
    }

    private suspend fun updateShibes() {
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://shibe.online/api/").build()
        val service = retrofit.create(ShibeAPIService::class.java)
        val shibesResponse = service.listShibes()
        shibesResponse.body()?.let {
            binding.recyclerView.adapter = ShibeAdapter(it)
        }
    }

    private suspend fun updateNekos() {
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://nekos.best/api/v2/").build()
        val service = retrofit.create(NekosBestAPIService::class.java)
        val nekosResponse = service.listNekos(amount = 10)
        nekosResponse.body()?.let {
            binding.recyclerView.adapter = NekoAdapter(it.results.toTypedArray())
        }
    }

    private suspend fun updateEntry() {
        val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.publicapis.org").build()
        val service = retrofit.create(PublicAPIService::class.java)
        val db = Room.databaseBuilder(this, AppDatabase::class.java, "sample-database").build()
        val entries = db.entryDao().getAll()
        binding.recyclerView.adapter = EntryAdapter(entries.map { it.toEntry() }.toTypedArray())

//        try {
//            val entriesResponse = service.listEntries()
//            entriesResponse.body()?.let {
//                val entries = it.entries
//                binding.recyclerView.adapter = EntryAdapter(entries.toTypedArray())
//                db.entryDao().insertAll(*entries.map(EntryEntity::fromEntry).toTypedArray())
//            }
//        } catch (e: HttpException) {
//            val builder = AlertDialog.Builder(this).setMessage(e.message)
//            builder.create()
//        }
    }
}