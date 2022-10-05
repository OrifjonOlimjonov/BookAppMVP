package uz.orifjon.bookappmvppattern.database

import androidx.room.*
import uz.orifjon.bookappmvppattern.models.Book

@Dao
interface BookDao
{
    @Insert
    fun add(book: BookData)

    @Update
    fun update(book: BookData)

    @Delete
    fun delete(book: BookData)

    @Query("SELECT * FROM BookData")
    fun list():List<BookData>
}