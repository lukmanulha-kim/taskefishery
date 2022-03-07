package com.lukman.taskefishery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.lukman.taskefishery.data.NetworkConfig
import com.lukman.taskefishery.data.QuerySeacrh
import com.lukman.taskefishery.data.ResponseHargaItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var rvHarga : RecyclerView
    lateinit var buttonAdd: CardView
    lateinit var editQuery: EditText
    val query = QuerySeacrh()

    private fun initComponents() {
        rvHarga = findViewById(R.id.rvHarga)
        buttonAdd = findViewById(R.id.bt_add)
        editQuery = findViewById(R.id.editQuery)
    }

    override fun onResume() {
        super.onResume()
        NetworkConfig().getService()
            .getUsers(query = query.toMap())
            .enqueue(object : Callback<List<ResponseHargaItem>> {
                override fun onFailure(call: Call<List<ResponseHargaItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<List<ResponseHargaItem>>,
                    response: Response<List<ResponseHargaItem>>
                ) {
                    val  data = response.body() ?: listOf()

                    rvHarga.adapter = HargaAdapter(data)

                }
            })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initComponents()

        NetworkConfig().getService()
            .getUsers(query = query.toMap())
            .enqueue(object : Callback<List<ResponseHargaItem>> {
                override fun onFailure(call: Call<List<ResponseHargaItem>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<List<ResponseHargaItem>>,
                    response: Response<List<ResponseHargaItem>>
                ) {
                  val  data = response.body() ?: listOf()

                    rvHarga.adapter = HargaAdapter(data)

                }
            })

        editQuery.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.toString() != "")
                NetworkConfig().getService()
                    .getUsers(query = QuerySeacrh(search = "{\"komoditas\":\"${s.toString()}\"}").toMap())
                    .enqueue(object : Callback<List<ResponseHargaItem>> {
                        override fun onFailure(call: Call<List<ResponseHargaItem>>, t: Throwable) {
                            Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                        override fun onResponse(
                            call: Call<List<ResponseHargaItem>>,
                            response: Response<List<ResponseHargaItem>>
                        ) {
                            val  data = response.body() ?: listOf()

                            rvHarga.adapter = HargaAdapter(data)

                        }
                    })

                else
                    NetworkConfig().getService()
                        .getUsers(query = QuerySeacrh(search = "").toMap())
                        .enqueue(object : Callback<List<ResponseHargaItem>> {
                            override fun onFailure(call: Call<List<ResponseHargaItem>>, t: Throwable) {
                                Toast.makeText(this@MainActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                            }
                            override fun onResponse(
                                call: Call<List<ResponseHargaItem>>,
                                response: Response<List<ResponseHargaItem>>
                            ) {
                                val  data = response.body() ?: listOf()

                                rvHarga.adapter = HargaAdapter(data)

                            }
                        })
            }
        })

        buttonAdd.setOnClickListener{
            startActivity(Intent(this, addHargaActivity::class.java))
        }
    }
}