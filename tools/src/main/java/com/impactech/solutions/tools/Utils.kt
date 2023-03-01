package com.impactech.solutions.tools

import android.app.Activity.RESULT_OK
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*


/**
 * A high level function that converts a [Date] object to a {String} that conforms to the provided format
 *
 * @receiver [Date]
 * @param format The desired date format.
 * @sample format "yyyy-MM-dd hh:mm:ss"
 * @param locale The desired locale. this param is not required.
 * @sample locale [Locale].getDefault()
 * @sample locale [Locale].US
 *
 * @return [String] a date in the specified format
 *
 * @throws Exception when [format] is not a valid date format
 * - This function can be triggered with a [Date] scope
 * Example
 * - val format = "yyyy-MM-dd hh:mm:ss"
 *  Kotlin
 * - val date = Date().dateToString(format)
 * Java
 * - val date = UtilsKt.dateToString(Date())
 * **/
fun Date.dateToString(format: String, locale: Locale = Locale.getDefault()): String{
    val dateFormat = SimpleDateFormat(format, locale)
    return dateFormat.format(this)
}

/**
 * A high level function that converts a [String] that conforms to the provided date [format] object to a {Date}
 * @receiver [Date]
 * @param format The required date format.
 * @sample format "yyyy-MM-dd hh:mm:ss"
 * @param locale The desired locale. this param is not required.
 * @sample locale [Locale].getDefault()
 * @sample locale [Locale].US
 *
 * @return [Date] or null if format is invalid
 *
 * @throws Exception when [format] is not a valid date format
 * - This function can be triggered with a [Date] scope
 * Example
 * - val format = "yyyy-MM-dd hh:mm:ss"
 *  Kotlin
 * - val date = this.dateToString([format])
 * Java
 * - val date = UtilsKt.dateToString([String], [format])
 * **/
fun String.stringToDate(format: String, locale: Locale = Locale.getDefault()): Date?{
    val dateFormat = SimpleDateFormat(format, locale)
    return dateFormat.parse(this)
}

fun AppCompatActivity.launchIntentForResult(onResult: (Boolean, Intent?) -> Unit): ActivityResultLauncher<Intent>{
    return registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        onResult.invoke(it.resultCode == RESULT_OK, it.data)
    }
}






