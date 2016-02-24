package nz.co.sush.simplelistdetailkotlin.utils

import java.text.DateFormat
import java.util.*

/**
 * Created by tlnacl on 24/02/16.
 */
fun Long.toDateString(dateFormat: Int = DateFormat.MEDIUM): String {
    val df = DateFormat.getDateInstance(dateFormat, Locale.getDefault())
    return df.format(this)
}