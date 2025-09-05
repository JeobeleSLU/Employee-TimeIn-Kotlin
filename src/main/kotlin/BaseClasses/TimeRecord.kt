package BaseClasses
import kotlinx.serialization.Serializable

@Serializable
data class TimeRecord(
    val timeIn: String,
    var timeOut: String? = null,
    val date: String
)