package uz.orifjon.bookappmvppattern.models

import android.os.Parcel
import android.os.Parcelable

data class BuyLink(
    val name: String,
    val url: String
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(url)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BuyLink> {
        override fun createFromParcel(parcel: Parcel): BuyLink {
            return BuyLink(parcel)
        }

        override fun newArray(size: Int): Array<BuyLink?> {
            return arrayOfNulls(size)
        }
    }
}