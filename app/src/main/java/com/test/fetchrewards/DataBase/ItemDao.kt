package com.test.fetchrewards.DataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.test.fetchrewards.Item

    @Dao
    interface ItemDao {

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertAll(itemList:MutableList<Item>)

        @Query("SELECT * FROM Item ORDER BY listId")
        fun getOrderedItems(): MutableList<Item>

        @Query("SELECT COUNT(listId) FROM item")
        fun countRows(): Int

    }
