package org.group1.BaseClasses
import BaseClasses.TimeRecord
import kotlinx.serialization.Serializable


@Serializable

data class Employee(val id: String,
                    var name:String,
                    var department:String,
                    var isCheckedIn: Boolean = false,
                    var lastTimeIn: String? = null,
                    var lastTimeOut: String? = null,
                    val timeRecords: MutableList<TimeRecord> = mutableListOf()

){
    constructor(id: String, name: String, department: String) : this(
        id, name, department, false, "09:00", "17:00", mutableListOf()
    )
}


