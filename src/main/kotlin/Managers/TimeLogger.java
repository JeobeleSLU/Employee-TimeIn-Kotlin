package Managers;
import BaseClasses.TimeRecord;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class TimeLogger {
    /**
     * This time logger is implemented in java, to show the interoperability of
     * Kotlin and java
     */
    private HashSet<String> employeeInTimeIn = new HashSet<>();
    private Map<String, TimeRecord> timeRecords = new HashMap<>();

    private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public TimeRecord logTime(String employeeNumber) {
        LocalDateTime currentTime = LocalDateTime.now();
        String currentTimeString = currentTime.format(timeFormatter);
        String currentDateString = currentTime.format(dateFormatter);

        if (employeeInTimeIn.contains(employeeNumber)) {
            return timeOut(employeeNumber, currentTimeString);
        } else {
            return timeIn(employeeNumber, currentTimeString, currentDateString);
        }
    }

    /**
     * Helper function for log time.
     */
    private TimeRecord timeIn(String employeeNumber, String timeIn, String date) {
        employeeInTimeIn.add(employeeNumber);
        TimeRecord timeRecord = new TimeRecord(timeIn, null,date);
        timeRecords.put(employeeNumber, timeRecord);
        return timeRecord;
    }

    private TimeRecord timeOut(String employeeNumber, String timeOut) {
        employeeInTimeIn.remove(employeeNumber);
        TimeRecord existingRecord = timeRecords.get(employeeNumber);

        if (existingRecord != null) {
            existingRecord.setTimeOut(timeOut);
            timeRecords.remove(employeeNumber);
        }
        return existingRecord;
    }
}
