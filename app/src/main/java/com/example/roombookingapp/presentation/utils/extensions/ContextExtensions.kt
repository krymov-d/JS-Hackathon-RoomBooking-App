package com.example.roombookingapp.presentation.utils.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.annotation.StringRes

fun Context.showToast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}

fun Context.showToastLong(msg: String, length: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, msg, length).show()
}

fun Context.showToast(@StringRes resId: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, length).show()
}

fun Context.showToastLong(@StringRes resId: Int, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, resId, length).show()
}

fun Context.startEmail(email: String) {
    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
    startActivity(emailIntent)
}

fun Context.startPhoneCall(number: String) {
    val phoneIntent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$number"))
    startActivity(phoneIntent)
}

fun Context.startWebsite(website: String) {
    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://$website"))
    startActivity(webIntent)
}

fun Context.startGeo(lat: String, lng: String) {
    val geoIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:$lat,$lng"))
    startActivity(geoIntent)
}