package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (year != other.year) return year.compareTo(other.year)
        if (month != other.month) return month.compareTo(other.month)
        return dayOfMonth.compareTo(other.dayOfMonth)
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)

operator fun MyDate.plus(interval: TimeInterval): MyDate {
    return addTimeIntervals(interval, 1)
}

operator fun MyDate.plus(repeatedInterval: RepeatedTimeInterval): MyDate {
    var date = this
    for (i in 1..(repeatedInterval.times)) {
        date += repeatedInterval.interval
    }
    return date
}

class RepeatedTimeInterval(val interval: TimeInterval, val times: Int)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR;

    operator fun times(times: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, times)
}

// 1st solution: implement ClosedRange interface
class DateRange(override val start: MyDate, override val endInclusive: MyDate)
    : ClosedRange<MyDate>, Iterator<MyDate> {

    private var currentDate = start

    override fun hasNext(): Boolean = currentDate <= endInclusive

    override fun next(): MyDate {
        // what in case of no further elements?
        val result = currentDate
        if (hasNext()) currentDate = currentDate.nextDay()
        return result
    }

// 2nd solution: implement "contains" method to allow "in" operator
//    operator fun contains(date: MyDate): Boolean {
//        return start <= date && date <= endInclusive
//    }
}
