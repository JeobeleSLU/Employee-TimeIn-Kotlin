package Managers

import BaseClasses.TimeRecord
import kotlinx.serialization.json.Json
import org.group1.BaseClasses.Employee
import org.group1.Loader.SingletonEmployeeLoader
import java.io.File

class EmployeeManager {
    private val employeeList: HashMap<String, Employee> = HashMap()
    private val timeLogger: TimeLogger = TimeLogger()

    init {
        loadEmployees()
    }

    private fun loadEmployees() {
        val loader = SingletonEmployeeLoader
        val temp: ArrayList<Employee> = loader.loadEmployees()
        if (temp.isEmpty()) return

        for (employee in temp) {
            employeeList[employee.id] = employee
        }
    }

    fun employeeExists(id: String): Boolean {
        return employeeList.containsKey(id)
    }

    fun addEmployee(id: String, name: String, department: String): Boolean {
        if (employeeExists(id)) {
            return false
        }
        val temp = Employee(id, name, department)
        employeeList[id] = temp
        return true
    }

    fun removeEmployee(id: String): Boolean {
        if (!employeeList.containsKey(id)) {
            return false
        }
        employeeList.remove(id)
        return true
    }

    fun editEmployeeName(id: String, name: String): Boolean {
        val emp = employeeList[id] ?: return false
        emp.name = name
        return true
    }

    fun editEmployeeDepartment(id: String, department: String): Boolean {
        val emp = employeeList[id] ?: return false
        emp.department = department
        return true
    }

    fun logEmployeeTime(id: String): String {
        //check if the employee exists
        val emp = employeeList[id] ?: return "Employee not found!"

        val timeRecord: TimeRecord? = timeLogger.logTime(id)
        if (timeRecord == null) {
            return "Error logging time."
        }

        return if (timeRecord.timeOut == null) {
            emp.isCheckedIn = true
            emp.lastTimeIn = timeRecord.timeIn
            "Employee ${emp.name} clocked in at ${timeRecord.timeIn}"
        } else {
            emp.isCheckedIn = false
            emp.lastTimeOut = timeRecord.timeOut
            emp.timeRecords.add(timeRecord)
            "Employee ${emp.name} clocked out at ${timeRecord.timeOut}"
        }
    }

    fun printTimeRecordsOfEmployee(employeeID :String ):String {
        if (!employeeList.containsKey(employeeID)) {
            return "No Employee found"
        }
        val emp = employeeList[employeeID]
        val timeRecordsOfEmp = emp?.timeRecords

        if (timeRecordsOfEmp == null) {
            return "Employee does not have a record"
        }
        //Syntactic sugar for StringBuilder in kotlin instead of declaring a string builder
        //ps love you chat gpt for easier formatting
        val result = buildString {
            appendLine("Employee: ${emp.name}")
            appendLine("====================================")
            appendLine(String.format("%-12s %-10s %-10s", "Date", "Time In", "Time Out"))
            appendLine("====================================")

            timeRecordsOfEmp.forEach { record ->
                appendLine(String.format("%-12s %-10s %-10s", record.date, record.timeIn, record.timeOut))
            }
        }
        return result
    }

    /**
     * converts the map into an array and then strings the employee
     * details in to json format then saves it into a file employees.json
     */
    fun save() {
        val prettyJson = Json {
            prettyPrint = true
            prettyPrintIndent = " "
        }

        var tempEmpList: MutableList<Employee> = mutableListOf()
        for (employee in employeeList.values) {
           tempEmpList.add(employee)
        }
        val jsonString = prettyJson.encodeToString(tempEmpList)
        File("employees.json").writeText(jsonString)
    }
}
