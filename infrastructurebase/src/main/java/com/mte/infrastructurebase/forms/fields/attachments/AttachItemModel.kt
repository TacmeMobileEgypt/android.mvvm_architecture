package com.mte.infrastructurebase.forms.fields.attachments

import android.net.Uri
import java.io.File

data class AttachItemModel (
    val filePath : String?,
    val fileUri : Uri?,
    val file : File? = null
    )