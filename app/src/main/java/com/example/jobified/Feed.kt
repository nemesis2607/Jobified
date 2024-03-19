package com.example.jobified

import android.os.Parcel
import android.os.Parcelable

data class Feed(
    var post: String = "",
    var imageUrl :String="",
    var user: String = "",
    var time: String = "",

):  Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""


    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(post)
        parcel.writeString(imageUrl)
        parcel.writeString(user)
        parcel.writeString(time)

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Candidate> {
        override fun createFromParcel(parcel: Parcel): Candidate {
            return Candidate(parcel)
        }

        override fun newArray(size: Int): Array<Candidate?> {
            return arrayOfNulls(size)
        }
    }
}