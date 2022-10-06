package uz.orifjon.bookappmvppattern.models

import android.os.Parcel
import android.os.Parcelable
import java.io.Serializable


data class Book(
    val age_group: String,
    val amazon_product_url: String,
    val article_chapter_link: String,
    val author: String,
    val book_image: String,
    val book_image_height: Int,
    val book_image_width: Int,
    val book_review_link: String,
    val book_uri: String,
    val buy_links: List<BuyLink>,
    val contributor: String,
    val contributor_note: String,
    val created_date: String,
    val description: String,
    val first_chapter_link: String,
    val price: String,
    val primary_isbn10: String,
    val primary_isbn13: String,
    val publisher: String,
    val rank: Int,
    val rank_last_week: Int,
    val sunday_review_link: String,
    val title: String,
    val updated_date: String,
    val weeks_on_list: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.createTypedArrayList(BuyLink)!!,
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readInt()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(age_group)
        parcel.writeString(amazon_product_url)
        parcel.writeString(article_chapter_link)
        parcel.writeString(author)
        parcel.writeString(book_image)
        parcel.writeInt(book_image_height)
        parcel.writeInt(book_image_width)
        parcel.writeString(book_review_link)
        parcel.writeString(book_uri)
        parcel.writeTypedList(buy_links)
        parcel.writeString(contributor)
        parcel.writeString(contributor_note)
        parcel.writeString(created_date)
        parcel.writeString(description)
        parcel.writeString(first_chapter_link)
        parcel.writeString(price)
        parcel.writeString(primary_isbn10)
        parcel.writeString(primary_isbn13)
        parcel.writeString(publisher)
        parcel.writeInt(rank)
        parcel.writeInt(rank_last_week)
        parcel.writeString(sunday_review_link)
        parcel.writeString(title)
        parcel.writeString(updated_date)
        parcel.writeInt(weeks_on_list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }
}