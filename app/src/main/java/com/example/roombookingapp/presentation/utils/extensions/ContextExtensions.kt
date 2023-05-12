package com.example.roombookingapp.presentation.utils.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import com.example.roombookingapp.R
import com.google.android.material.snackbar.Snackbar

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

fun Context.showSnackBar(view: View, @StringRes messageStringId: Int) {
    Snackbar.make(view, messageStringId, 3000).show()
}

fun Context.showSnackBarWithAction(
    view: View,
    @StringRes messageStringId: Int,
    @StringRes actionStringId: Int,
    listener: View.OnClickListener
) {
    Snackbar
        .make(view, messageStringId, 3000)
        .setAction(actionStringId, listener)
        .setActionTextColor(resources.getColor(R.color.ui_05, null))
        .show()
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