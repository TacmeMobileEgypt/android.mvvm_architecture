package com.hamdy.infrastructurebase.attachments

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ImageFileFromCamera(activity: Activity,
                          isMulti : Boolean,
                          var onTakePhotoListener : OnTakeFilePhotoListener? = null
) : BaseAttachments(activity , isMulti) {


    private var photoURI: Uri? = null
    private var packageManager = activity.packageManager
    private lateinit var currentPhotoPath: String


    override val RESULT_CODE: Int
        get() = 113

    override fun dispatchIntent() {
        dispatchSavePictureIntent()
    }

    /* <provider
        android:name="android.support.v4.content.FileProvider"
        android:authorities="com.example.android.fileprovider"
        android:exported="false"
        android:grantUriPermissions="true">
        <meta-data
            android:name="android.support.FILE_PROVIDER_PATHS"
            android:resource="@xml/file_paths"></meta-data>
        </provider>

        <?xml version="1.0" encoding="utf-8"?>
        <paths xmlns:android="http://schemas.android.com/apk/res/android">
            <external-path name="my_images" path="Android/data/com.example.package.name/files/Pictures" />
        </paths>
    */

    //To save The captured picture
    private fun dispatchSavePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            // Ensure that there's a camera activity to handle the intent
            takePictureIntent.resolveActivity(packageManager)?.also {
                // Create the File where the photo should go
                val photoFile: File? = try {
                    createImageFile()
                } catch (ex: IOException) {
                    null
                }
                // Continue only if the File was successfully created
                photoFile?.also {
                    photoURI = FileProvider.getUriForFile(
                        activity!!,
                        "com.example.android.fileprovider",
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    activity.startActivityForResult(takePictureIntent, RESULT_CODE)
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RESULT_CODE && resultCode == RESULT_OK) {
            onTakePhotoListener?.OnTakePhoto(photoURI)
        }

    }



    @Throws(IOException::class)
    private fun createImageFile(): File {
        // Create an image file name
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File? = activity?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}_", /* prefix */
            ".jpg", /* suffix */
            storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            currentPhotoPath = absolutePath
        }
    }

    override fun createIntent(): Intent? = null
}