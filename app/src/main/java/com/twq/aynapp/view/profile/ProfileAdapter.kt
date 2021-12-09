package com.twq.aynapp.view.profile

import android.annotation.SuppressLint
import android.content.Context
import android.os.Parcel
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.twq.aynapp.R

@SuppressLint("ParcelCreator")
internal class MainAdapter(
    private val context: Context,
    private val numbersInWords: Array<String>,
    private val numberImage: IntArray
) :
    BaseAdapter(), Parcelable {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var imageView: ImageView
    private lateinit var textView: TextView

    override fun getCount(): Int {
        return numbersInWords.size
    }
    override fun getItem(position: Int): Any? {
        return null
    }
    override fun getItemId(position: Int): Long {
        return 0
    }
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }
        if (convertView == null) {
            convertView = layoutInflater!!.inflate(R.layout.list_home_posts, null)
        }
        imageView = convertView!!.findViewById(R.id.imageViewPost)
        textView = convertView.findViewById(R.id.textViewProjectTitle)
        imageView.setImageResource(numberImage[position])
        textView.text = numbersInWords[position]
        return convertView
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeStringArray(numbersInWords)
        parcel.writeIntArray(numberImage)
    }

    override fun describeContents(): Int {
        return 0
    }

}