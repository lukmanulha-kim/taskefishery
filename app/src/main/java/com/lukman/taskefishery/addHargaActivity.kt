package com.lukman.taskefishery

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.lukman.taskefishery.data.NetworkConfig
import com.lukman.taskefishery.data.PostHarga
import com.lukman.taskefishery.data.ResponseData
import com.lukman.taskefishery.data.ResponseHargaItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class addHargaActivity : AppCompatActivity() {
    lateinit var komoditas : EditText
    lateinit var provinsi : EditText
    lateinit var kabupaten : EditText
    lateinit var size : EditText
    lateinit var harga : EditText
    lateinit var buttonSave : CardView

    private fun InitComponents(){
        komoditas = findViewById(R.id.komoditas)
        provinsi = findViewById(R.id.provinsi)
        kabupaten = findViewById(R.id.kabupaten)
        size = findViewById(R.id.ukuran)
        harga = findViewById(R.id.harga)
        buttonSave = findViewById(R.id.bt_simpan)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_harga)

        InitComponents()


        buttonSave.setOnClickListener {
            if (!komoditas.text.equals("") && !provinsi.text.equals("") && !kabupaten.text.equals("") && !size.text.equals("") && !harga.text.equals("")) {
                val postData = PostHarga(
                    uuid = UUID.randomUUID().toString(),
                    komoditas = komoditas.text.toString(),
                    areaProvinsi = provinsi.text.toString(),
                    areaKota = kabupaten.text.toString(),
                    size = size.text.toString(),
                    price = harga.text.toString()
                )
                NetworkConfig().getService()
                    .postHarga(listOf(postData))
                    .enqueue(object : Callback<ResponseData> {
                        override fun onFailure(call: Call<ResponseData>, t: Throwable) {
                            Toast.makeText(
                                this@addHargaActivity,
                                t.localizedMessage,
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<ResponseData>,
                            response: Response<ResponseData>
                        ) {
                            startActivity(Intent(this@addHargaActivity, MainActivity::class.java))
                            Toast.makeText(
                                this@addHargaActivity,
                                "Berhasil Disimpan",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    })

            }else{
                Toast.makeText(this@addHargaActivity, "Harap Isi Semua Form Cvk", Toast.LENGTH_SHORT)
                .show()
            }
        }
    }
}