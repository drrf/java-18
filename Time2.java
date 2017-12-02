
/**
 * Represents time - hours:minutes. Values must represent a proper time.
 *
 * @author Ron F.
 * @version 1.0
 * @date 2017-11-25
 */
public class Time2
{
    // all the attributes
    private int _minFromMid;

    // all the finals
    final int MINUTES_IN_HOUR = 60;
    final int HOURS_IN_DAY = 24;
    final int MIN_VALID = 0; 
    final int MIN_CLOCK_ZERO = 10;

    /**
     * 
     * Constructs a Time2 object.
     * Construct a new time instance with the specified hour and minute.
     * hour should be between 0-23, otherwise it should be set to 0.
     * minute should be between 0-59, otherwise they should be set to 0.
     * 
     * @param h hour.
     * @param m minute.
     * 
     */

    public Time2 (int h, int m)
    {
        setHour(h);
        setMinute(m);
    }

    /**
     * 
     * Copy constructor for Time2.
     * Constructs a time with the same variables as another time.
     * 
     * @param other The time object from which to construct the new time
     * 
     */

    public Time2 (Time2 other)
    {
        this._minFromMid = other._minFromMid;
    }

    /**
     * 
     * Returns the hour of the time.
     * 
     * @return The hour of the time.
     * 
     */

    public int getHour()
    {
        return (_minFromMid / MINUTES_IN_HOUR);
    }

    /**
     * 
     * Returns the minute of the time.
     * 
     * @return The minute of the time.
     * 
     */

    public int getMinute()
    {
        return (_minFromMid % MINUTES_IN_HOUR);   
    }

    /**
     * 
     * Changes the hour of the time.
     * If an illegal number is received hour will remain unchanged.
     * 
     * @param num The new hour.
     * 
     */

    public void setHour (int num)
    {
        if(num < HOURS_IN_DAY && num >= MIN_VALID) // setHour only if the hour is valid
            this._minFromMid = (num * MINUTES_IN_HOUR) + (_minFromMid % MINUTES_IN_HOUR);
    }

    /**
     * 
     * Changes the minute of the time.
     * If an illegal number is received minute will remain unchanged.
     * 
     * @param num The new minute.
     * 
     */

    public void setMinute (int num)
    {
        if(num < MINUTES_IN_HOUR && num >= MIN_VALID) // setMinute only if the minute is valid
            this._minFromMid = (_minFromMid - (_minFromMid % MINUTES_IN_HOUR)) + num;
    }

    /**
     * 
     * Returns a string representation of this time(hh:mm).
     * 
     * @return String representation of this time(hh:mm).
     * 
     */

    public String toString()
    {
        if(getHour() < MIN_CLOCK_ZERO && getMinute() < MIN_CLOCK_ZERO)  // add 0 before hour and minute
            return ("0" + getHour() + ":" + "0" + getMinute());
        else if (getMinute() < MIN_CLOCK_ZERO) // add 0 before minute
            return (getHour() + ":" + "0" + getMinute());
        else if (getHour() < MIN_CLOCK_ZERO) // add 0 before hour
            return ("0" + getHour() + ":" + getMinute());
        else
            return (getHour() + ":" + getMinute());
    }

    /**
     * 
     * Return the amount of minutes since midnight.
     * 
     * @return amount of minutes since midnight.
     * 
     */

    public int minFromMidnight()
    {
        return _minFromMid;
    }

    /**
     * 
     * Checks if the received time is equal to this time.
     * 
     * @param other The time to be compared with this time.
     * @return True if the received time is equal to this time.
     * 
     */

    public boolean equals (Time2 other)
    {
        return (this._minFromMid == other._minFromMid);
    }

    /**
     * 
     * Checks if this time is before a received time.
     * 
     * @param other The time to check if this time is before.
     * @return True if this time is before other time.
     * 
     */

    public boolean before (Time2 other)
    {
        return (this._minFromMid < other._minFromMid);
    }

    /**
     * 
     * Checks if this time is after a received time.
     * 
     * @param other The time to check if this time is after.
     * @return True if this time is after other time.
     * 
     */

    public boolean after (Time2 other)
    {
        return other.before(this);
    }

    /**
     * 
     * Calculates the difference (in minutess) between two times.
     * 
     * @param other The time to check the difference with. Assumption: this time is after other time.
     * @return int difference in minutes.
     * 
     */

    public int difference (Time2 other)
    {
        return Math.abs(this.minFromMidnight() - other.minFromMidnight());  // the return is positive
    }
} // End of class Time2
