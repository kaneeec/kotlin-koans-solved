package iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        if (this.year != other.year) return this.year - other.year
        if (this.month != other.month) return this.month - other.month
        if (this.dayOfMonth != other.dayOfMonth) return this.dayOfMonth - other.dayOfMonth
        return 0
    }

}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this, other)
operator fun MyDate.plus(interval: RepeatedTimeInterval): MyDate = this.addTimeIntervals(interval.interval, interval.n)
operator fun MyDate.plus(interval: TimeInterval): MyDate = this.addTimeIntervals(interval, 1)
operator fun TimeInterval.times(n: Int): RepeatedTimeInterval = RepeatedTimeInterval(this, n)

data class RepeatedTimeInterval(val interval: TimeInterval, val n: Int = 1)

data class DateRange(val start: MyDate, val endInclusive: MyDate) : Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate> = object : Iterator<MyDate> {
        var currentDate = start;

        override fun hasNext(): Boolean = currentDate <= endInclusive

        override fun next(): MyDate {
            val dateToReturn = currentDate
            currentDate = currentDate.nextDay()
            return dateToReturn
        }
    }

    operator fun contains(date: MyDate): Boolean = start <= date && date <= endInclusive
}
