package com.example.juandaonlineshop_backup.room

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.juandaonlineshop_backup.model.Produk

@Dao
interface KeranjangDao {

    @Insert(onConflict = REPLACE)
    fun insert(data: Produk)

    @Delete
    fun delete(data: Produk)

    @Update
    fun update(data: Produk): Int

    @Query("SELECT * from keranjangg ORDER BY id ASC")
    fun getAll(): List<Produk>

    @Query("SELECT * FROM keranjangg WHERE id = :id LIMIT 1")
    fun getNote(id: Int): Produk

    @Query("DELETE FROM keranjangg")
    fun deleteAll(): Int
}