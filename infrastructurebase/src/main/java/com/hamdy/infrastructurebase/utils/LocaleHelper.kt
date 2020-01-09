package com.android.fdf.utils

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import android.preference.PreferenceManager
import com.hamdy.infrastructurebase.base.BasePreferenceStorage
import java.util.*


object LocaleHelper {

    private val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"


    @JvmField
    var locale: Locale = Locale("en")

    fun wrapContext(context: Context): Context =
        wrapContext(context, context.resources.configuration)


    fun wrapContext(context: Context, configuration: Configuration?): Context {
        var injectedContext = context
        if (this.locale.language.equals(configuration?.locale?.displayName)) {
            return injectedContext
        }
        injectedContext = updateConfiguration(injectedContext.resources.configuration, injectedContext)
        return ContextWrapper(injectedContext)
    }


    private fun updateConfiguration(configuration: Configuration, context: Context): Context {

        var injectedContext = context
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> {
                configuration.setLocale(locale)

                val localeList = LocaleList(locale)
                LocaleList.setDefault(localeList)

                configuration.setLocales( localeList)

                injectedContext = injectedContext.createConfigurationContext(configuration)
            }
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 -> {
                configuration.setLocale(locale)
                injectedContext = injectedContext.createConfigurationContext(configuration)
            }
            else -> {
                configuration.setLocale( locale)
                injectedContext.resources.updateConfiguration(configuration, injectedContext.resources.displayMetrics)
            }
        }
        return injectedContext
    }

    fun onAttach(context: Context?): Context? {
        val lang = getPersistedData(context, Locale.getDefault().language)
        return setLocale(context, lang)
    }

    fun onAttach(context: Context, defaultLanguage: String): Context? {
        val lang = getPersistedData(context, defaultLanguage)
        return setLocale(context, lang)
    }

    fun getLanguage(context: Context): String? {
        return getPersistedData(context, Locale.getDefault().language)
    }

    @JvmStatic
    fun isArabic(context: Context): Boolean {
        return getLanguage(context).equals("ar")
    }

    @JvmStatic
    fun isArabic(): Boolean {
        return Locale.getDefault().language.equals("ar")
    }

    fun setLocale(context: Context?, language: String?): Context? {
        persist(context, language)

        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)

    }

    private fun getPersistedData(context: Context?, defaultLanguage: String): String? {
        val preferences : BasePreferenceStorage? = BasePreferenceStorage(context)
        return preferences?.getString(SELECTED_LANGUAGE, defaultLanguage)
    }

    private fun persist(context: Context?, language: String?) {
        val preferences : BasePreferenceStorage? = BasePreferenceStorage(context)
        preferences?.putString(SELECTED_LANGUAGE, language)
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context?, language: String?): Context? {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val configuration = context?.resources?.configuration
        configuration?.setLocale(locale)

        return if(configuration == null) null else context.createConfigurationContext(configuration)
    }


    private fun updateResourcesLegacy(context: Context?, language: String?): Context? {
        val locale = Locale(language?: Locale.getDefault().language)
        Locale.setDefault(locale)

        val resources = context?.resources

        val configuration = resources?.configuration
        configuration?.setLocale(locale)

        @Suppress("DEPRECATION")
        resources?.updateConfiguration(configuration, resources.displayMetrics)

        return context
    }
}