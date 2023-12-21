package mx.com.practicamvvm.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import mx.com.practicamvvm.data.local.database.entity.Facts
import java.time.format.SignStyle


@Dao
interface DaoFacts {
    @Query("SELECT * FROM Facts limit :size Offset :page")
    fun getPage(size: Int, page: Int): List<Facts>

    @Query("Select * from Facts")
    fun getFacts(): Array<Facts>

    @Insert
    fun insert(facts: List<Facts>)

    @Query("delete from Facts")
    fun delete()

    @Query("Select * from Facts where Id=:id")
    fun getInfoResults(id: String): Facts

    @Query("Select * from Facts where organization like :search")
    fun getSearch(search: String): List<Facts>
}