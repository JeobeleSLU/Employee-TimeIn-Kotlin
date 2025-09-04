package org.group1.BaseClasses
import kotlinx.serialization.Serializable


@Serializable

data class Employee(val id: String, var name:String, var department:String )