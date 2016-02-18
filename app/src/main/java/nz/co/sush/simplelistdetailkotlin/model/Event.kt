package nz.co.sush.simplelistdetailkotlin.model

import com.google.gson.annotations.SerializedName

/**
 * Created by tomtang on 18/02/16.
 */
data class Event(
        @SerializedName("id") val id: Long,
        @SerializedName("type") val type: String,
        @SerializedName("public") val isPublic: Boolean
)