package com.hamdy.infrastructurebase.forms.interfaces

interface IRule<T> {
    fun validate(value: T?) : String?
}