package com.mte.infrastructurebase.forms.interfaces

interface IRule<T> {
    fun validate(value: T?) : String?
}