package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (year != other.year) return year.compareTo(other.year)
        if (month != other.month) return month.compareTo(other.month)
        return dayOfMonth.compareTo(other.dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = todoTask27()

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

// 1st solution: implement ClosedRange interface
class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate> {

// 2nd solution: implement "contains" method to allow "in" operator
//    operator fun contains(date: MyDate): Boolean {
//        return start <= date && date <= endInclusive
//    }
}
