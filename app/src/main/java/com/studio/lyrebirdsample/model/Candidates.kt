package com.studio.lyrebirdsample.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Candidates {
    @SerializedName("overlayId")
    @Expose
    var overlayId: Int? = null
    @SerializedName("overlayName")
    @Expose
    var overlayName: String? = null
    @SerializedName("overlayPreviewIconUrl")
    @Expose
    var overlayPreviewIconUrl: String? = null
    @SerializedName("overlayUrl")
    @Expose
    var overlayUrl: String? = null

}