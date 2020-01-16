package com.android.fdf.utils

import android.content.Context
import com.mte.infrastructurebase.base.BasePreferenceStorage


enum class Theme{
    EMPLOYEE,
    EMPLOYER
}

object ThemeHelper {

    private val SELECTED_THEME = "Theme.Helper.Selected.theme"



    fun ChangeTheme(context: Context, theme: Theme?) {
        persist(context, theme)
    }

    fun getCurrenttTheme(context: Context): Theme? {
        return getPersistedData(context)
    }

    private fun getPersistedData(context: Context): Theme {

        val preferences = BasePreferenceStorage(context)
        return when(preferences.getString(SELECTED_THEME, Theme.EMPLOYEE.name)){
            Theme.EMPLOYEE.name -> Theme.EMPLOYEE
            Theme.EMPLOYER.name -> Theme.EMPLOYER
            else -> Theme.EMPLOYER
        }
    }

    private fun persist(context: Context, theme: Theme?) {
        val preferences = BasePreferenceStorage(context)
        preferences.putString(SELECTED_THEME, theme?.name)
    }


}