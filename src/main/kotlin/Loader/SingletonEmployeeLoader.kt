package org.group1.Loader

import kotlinx.serialization.json.Json
import org.group1.BaseClasses.Employee
import java.io.File

object SingletonEmployeeLoader {
    private val filePath = "./employees.json"

    fun loadEmployees() : ArrayList<Employee> {
        val file = File(filePath)
        if (!file.exists()) {
            println("File not found: $filePath")
            return arrayListOf()
        }
        val jsonContent = file.readText()
        val employees: List<Employee> = Json.decodeFromString(jsonContent)
        return ArrayList(employees)
    }

}