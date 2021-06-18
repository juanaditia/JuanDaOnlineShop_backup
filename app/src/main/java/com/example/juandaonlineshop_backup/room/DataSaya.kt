package com.example.juandaonlineshop_backup.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.juandaonlineshop_backup.model.Produk

@Database(entities = [Produk::class] /* List model Ex:NoteModel */, version = 1)
abstract class DataSaya : RoomDatabase() {
    abstract fun daoKeranjang(): KeranjangDao // DaoNote

    companion object {
        private var INSTANCE: DataSaya? = null

        fun getInstance(context: Context): DataSaya? {
            if (INSTANCE == null) {
                synchronized(DataSaya::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DataSaya::class.java, "MyDatabaseName" // Database Name
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}