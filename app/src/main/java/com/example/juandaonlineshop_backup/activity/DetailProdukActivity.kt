package com.example.juandaonlineshop_backup.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.juandaonlineshop_backup.R
import com.example.juandaonlineshop_backup.helper.Helper
import com.example.juandaonlineshop_backup.model.Produk
import com.example.juandaonlineshop_backup.room.DataSaya
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail_produk.*
import kotlinx.android.synthetic.main.fragment_akun.tv_nama
import kotlinx.android.synthetic.main.item_produk.tv_harga
import kotlinx.android.synthetic.main.toolbar.*

class DetailProdukActivity : AppCompatActivity() {

    lateinit var produk : Produk

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_produk)

        getInfo()
        mainButton()
    }
    fun mainButton() {
        keranjang_btn.setOnClickListener {
            insert()

        }
        btn_favorit.setOnClickListener {
            val myDb: DataSaya = DataSaya.getInstance(this)!! // call database
            val listData = myDb.daoKeranjang().getAll() // get All data
            for(note :Produk in listData){
                println("-----------------------")
                println(note.name)
                println(note.harga)
            }
        }
    }

    fun insert(){
        val myDb: DataSaya = DataSaya.getInstance(this)!!// call database

        CompositeDisposable().add(Observable.fromCallable { myDb.daoKeranjang().insert(produk) }
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                Log.d("respons", "data inserted")
            })
    }

    fun getInfo(){
        val data = intent.getStringExtra("extra")
        produk = Gson().fromJson<Produk>(data, Produk::class.java)

        // set value
        tv_nama.text = produk.name
        tv_harga.text = Helper().gantiRupiah(produk.harga)
        tv_deskripsi.text = produk.deskripsi

        //pemanggilan gambar
        val img = "http://192.168.1.8/tokojuanda/public/storage/produk/" + produk.image
        Picasso.get()
            .load(img)
            .placeholder(R.drawable.product)
            .error(R.drawable.product)
            .resize(400, 400)
            .into(image)

        //set toolbar
        setSupportActionBar(toolbar)
        supportActionBar!!.title = produk.name
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}