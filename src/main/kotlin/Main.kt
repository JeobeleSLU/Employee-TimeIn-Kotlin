import Managers.EmployeeManager

fun main() {
    val employeeManager = EmployeeManager()

    while (true) {
        println("\n==== Employee Manager ====")
        println("1. Add Employee")
        println("2. Remove Employee")
        println("3. Edit Employee Name")
        println("4. Edit Employee Department")
        println("5. Log Employee Time (In/Out)")
        println("6. Show Employee Time Records")
        println("7. Exit")
        print("Choose an option: ")

        when (readLine()?.trim()) {
            "1" -> {
                print("Enter Employee ID: ")
                val id = readLine()?.trim() ?: ""
                print("Enter Employee Name: ")
                val name = readLine()?.trim() ?: ""
                print("Enter Department: ")
                val department = readLine()?.trim() ?: ""

                val added = employeeManager.addEmployee(id, name, department)
                if (added) println("Employee added successfully.")
                else println("Employee with ID $id already exists.")
            }

            "2" -> {
                print("Enter Employee ID to remove: ")
                val id = readLine()?.trim() ?: ""
                val removed = employeeManager.removeEmployee(id)
                if (removed) println("Employee removed.")
                else println("Employee not found.")
            }

            "3" -> {
                print("Enter Employee ID: ")
                val id = readLine()?.trim() ?: ""
                print("Enter New Name: ")
                val newName = readLine()?.trim() ?: ""
                val updated = employeeManager.editEmployeeName(id, newName)
                if (updated) println("Employee name updated.")
                else println("Employee not found.")
            }

            "4" -> {
                print("Enter Employee ID: ")
                val id = readLine()?.trim() ?: ""
                print("Enter New Department: ")
                val dept = readLine()?.trim() ?: ""
                val updated = employeeManager.editEmployeeDepartment(id, dept)
                if (updated) println("Department updated.")
                else println("Employee not found.")
            }

            "5" -> {
                print("Enter Employee ID: ")
                val id = readLine()?.trim() ?: ""
                println(employeeManager.logEmployeeTime(id))
            }

            "6" -> {
                print("Enter Employee ID: ")
                val id = readLine()?.trim() ?: ""
                println(employeeManager.printTimeRecordsOfEmployee(id))
            }

            "7" -> {
                println("Exiting program. Goodbye!")
                employeeManager.save()
                break
            }

            else -> println("Invalid option. Try again.")
        }
    }
}
