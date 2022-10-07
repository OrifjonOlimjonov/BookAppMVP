package uz.orifjon.bookappmvppattern.database

import androidx.room.*
import io.reactivex.rxjava3.core.Flowable
import uz.orifjon.bookappmvppattern.models.Book

@Dao
interface BookDao
{
    @Insert
    fun add(books: BookData)

    @Update
    fun update(books: BookData)

    @Delete
    fun delete(books: BookData)

    @Query("DELETE FROM bookdata")
    fun deleteTable()

    @Query("SELECT * FROM bookdata")
    fun list():Flowable<List<BookData>>

    @Query("SELECT * FROM bookdata")
    fun listNotThread(): List<BookData>
}