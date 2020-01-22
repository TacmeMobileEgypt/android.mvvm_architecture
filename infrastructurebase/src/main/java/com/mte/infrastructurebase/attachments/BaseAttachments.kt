package com.mte.infrastructurebase.attachments

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.checkSelfPermission
import com.mte.infrastructurebase.forms.fields.attachments.AttachItemModel
import com.mte.infrastructurebase.forms.fields.attachments.FilePathUtils


abstract class BaseAttachments(
    val activity: Activity?,
    val isMulti: Boolean = false,
    var onAttachmentSelectedListener : OnAttachmentSelectedListener ? = null) {


    abstract var RESULT_CODE : Int

    val filesPathes: ArrayList<String> = ArrayList<String>()


    fun openSAF(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

            if(checkPermision()){
                dispatchIntent()
            }else
                requestPermision()
        }else{
            dispatchIntent()
        }
    }

    protected open fun dispatchIntent() {

        try {

            val intent = createIntent()
            if(isMulti)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                    intent?.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
                }

            activity?.startActivityForResult(intent, RESULT_CODE)
        } catch (e: ActivityNotFoundException) {
            Log.e("tag", "No activity can handle picking a file. Showing alternatives.")
        }
    }

    abstract fun createIntent(): Intent?


    @RequiresApi(Build.VERSION_CODES.M)
    private fun requestPermision() {
        activity?.requestPermissions(
            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
            REQUEST_READ_STORAGE
        )
    }


    private fun checkPermision() : Boolean {
        return  checkSelfPermission(activity!!, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
    }

    fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_READ_STORAGE) {
            if (grantResults.size > 0) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    dispatchIntent()
            }
        }
    }

    private fun openIntent() {
        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent().apply {

            action = Intent.ACTION_GET_CONTENT //To open images

//            action = Intent.ACTION_PICK // to choose different apps

//            action = Intent.ACTION_CREATE_DOCUMENT // to create file and return it

//            action = Intent.ACTION_OPEN_DOCUMENT //  allows the user to browse all files that other apps have made available

//            action = Intent. ACTION_OPEN_DOCUMENT_TREE  // llows the user to choose a directory for a client app to access.

            addCategory(Intent.CATEGORY_OPENABLE)

            type = "*/*"
//            type = "text/plain"

//            if(isMulti)
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
//                    putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
//                }
//
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                val mimeTypes = arrayOf("image/jpeg", "image/png")
//                putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes)
//            }

        }
        try {

            activity?.startActivityForResult(Intent.createChooser(intent, "Choose a file"),
                RESULT_CODE
            );
//            fragment.activity!!.startActivityForResult(intent, PICK_FILE_RESULT_CODE)
        } catch (e: ActivityNotFoundException) {
            Log.e("tag", "No activity can handle picking a file. Showing alternatives.")
        }
    }

    open fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RESULT_CODE) {
            if (resultCode == RESULT_OK) {
                val attachments = getAttachmentsFromIntent(data)
                onAttachmentSelectedListener?.onAttachmentsSelected(attachments)
            }
        }
    }

    /*GET Files Pathes */
    private fun getAttachmentsFromIntent(data: Intent?): List<AttachItemModel?> {

        val pathList = ArrayList<AttachItemModel?>()

        if(activity == null) return pathList

        try {
            if (data?.clipData != null) {
                val count = data.clipData?.itemCount ?: 0

                for (i in 0 until count) {
                    val fileUri = data.clipData?.getItemAt(i)?.uri
                    if (fileUri != null) {
                        val filePath: String? =
                            FilePathUtils.getFilePath(
                                activity,
                                fileUri
                            )
                        pathList.add(
                            AttachItemModel(
                                filePath,
                                fileUri
                            )
                        )
                    }
                }

            } else if (data?.getData() != null) {
                val fileUri = data.data
                if(fileUri != null ) {
                    val filePath =
                        FilePathUtils.getFilePath(
                            activity,
                            fileUri
                        )
                    pathList.add(
                        AttachItemModel(
                            filePath,
                            fileUri
                        )
                    )
                }

            }

        } catch (ex: java.lang.Exception) {
            ex.printStackTrace()
        }

        return pathList

    }


    companion object {
        const val REQUEST_READ_STORAGE: Int = 0x1A
        const val TAG = "AttachFragment"
    }

}

