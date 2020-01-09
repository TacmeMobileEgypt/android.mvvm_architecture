package com.hamdy.infrastructurebase.base.base_activity


interface IDialogAlert {

    fun showInfoMsg(msg : String , title : String? = null)
    fun showWarningMsg(msg : String , title : String? = null)
    fun showErrorMsg(msg : String , title : String? = null)
    fun showConfirmationMsg(msg : String , confirmHandler : ConfirmHandler , title : String? = null)

}