package com.mte.infrastructurebase.defaults

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog
import com.mte.infrastructurebase.base.base_activity.ConfirmHandler
import com.mte.infrastructurebase.base.base_activity.IDialogAlert

class DefaultDialogAlert(val context: Context , val yesBtnText : String? = "Yes" , val noBtnText : String? = "No" ) : IDialogAlert {


    private var builder: AlertDialog.Builder? = null
    private  var messageDialog: AlertDialog? = null
    var themeId : Int = -1

    var isCancelable : Boolean = false


    init {
        init()
    }


    private fun init() {

        builder  = if (themeId != -1)
            AlertDialog.Builder(context ,themeId) else AlertDialog.Builder(context)

        builder?.setCancelable(isCancelable)

    }


    override fun showInfoMsg(msg: String , title : String?) {


        if(messageDialog?.isShowing == true) return


        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText , DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showWarningMsg(msg: String , title : String?) {

        if(messageDialog?.isShowing == true) return


        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText , DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showErrorMsg(msg: String , title : String? ) {

        if(messageDialog?.isShowing == true) return

        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText , DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showSuccessMsg(msg: String, title: String?) {

        if(messageDialog?.isShowing == true) return

        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText , DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }

    override fun showConfirmationMsg(msg: String, confirmHandler: ConfirmHandler , title : String? ) {


        if(messageDialog?.isShowing == true) return

        builder?.setTitle(title)
        builder?.setMessage(msg)

        //Yes Action
        builder?.setPositiveButton(yesBtnText , DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()
            confirmHandler.onConfirmed()
        })

        builder?.setNegativeButton(noBtnText , DialogInterface.OnClickListener{ dialog, which ->
            dialog.dismiss()
        })

        messageDialog = builder?.create()

        messageDialog?.show()
    }
}