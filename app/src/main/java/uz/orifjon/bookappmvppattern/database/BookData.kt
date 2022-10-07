package uz.orifjon.bookappmvppattern.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.orifjon.bookappmvppattern.models.BuyLink
import java.io.Serializable

@Entity
data class BookData(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val books: String
) : Serializable