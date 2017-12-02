
/**
 * Represents time - hours:minutes. Coordinates cannot be negative.
 *
 * @author Ron F.
 * @version 1.0
 * @date 2017-11-25
 */
public class Time1
{
    // all the attributes
    private int _hour;
    private int _minute;

    // all the finals
    final int HOURS_IN_DAY = 24;
    final int MINUTES_IN_HOUR = 60;
    final int MIN_VALID = 0;
    final int MIN_CLOCK_ZERO = 10;

    /**
     * Constructs a Time1 object.
     * Construct a new time instance with the specified hour and minute.
     * hour should be between 0-23, otherwise it should be set to 0.
     * minute should be between 0-59, otherwise it should be set to 0.
     * 
     * @param h The hour of the time.
     * @param m The minute of the time.
     * 
     */

    public Time1 (int h, int m)
    {
        setHour(h);
        setMinute(m);
    }

    /**
     * 
     * Copy constructor for Time1.
     * Construct a time with the same instance variables as another time.
     * 
     * @param other The time object from which to construct the new time.
     * 
     */

    public Time1 (Time1 other)
    {
        this._hour = other._hour;
        this._minute = other._minute;
    }

    /**
     * 
     * Returns The hour of the time.
     * 
     * @return The hour of the time.
     * 
     */

    public int getHour()
    {
        return _hour;
    }

    /**
     * 
     * Returns The minute of the time.
     * 
     * @return The minute of the time.
     * 
     */

    public int getMinute()
    {
        return _minute;   
    }

    /**
     * 
     * Changes the hour of the time.
     * If an illegal number is received hour will be unchanged.
     * 
     * @param num The new hour.
     * 
     */

    public void setHour (int num)
    {
        if(num < HOURS_IN_DAY && num >= MIN_VALID) // setHour only if the hour is valid
            this._hour = num;
    }

    /**
     * 
     * Changes the minute of the time.
     * If an illegal number is received hour will be unchanged.
     * 
     * @param num The new minute.
     * 
     */

    public void setMinute (int num)
    {
        if(num < MINUTES_IN_HOUR && num >= MIN_VALID) // setMinute only if the minute is valid
            this._minute = num;
    }

    /**
     * 
     * Return a string representation of this time (hh:mm).
     * 
     * @return String representation of this time (hh:mm).
     * 
     */

    public String toString()
    {
        if(getHour() < MIN_CLOCK_ZERO && getMinute() < MIN_CLOCK_ZERO) // add 0 before hour and minute
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
        return (_hour * MINUTES_IN_HOUR) + _minute;
    }

    /**
     * 
     * Check if the received time is equal to this time.
     * 
     * @param other The time to be compared with this time.
     * @return True if the received time is equal to this time.
     * 
     */

    public boolean equals (Time1 other)
    {
        return (this._hour == other._hour && this._minute == other._minute);
    }

    /**
     * 
     * Check if this time is before a received time.
     * 
     * @param other The time to check if this point is before.
     * @return True if this time is before other time.
     * 
     */

    public boolean before (Time1 other)
    {
        return ((this._hour < other._hour) || (this._hour == other._hour && this._minute < other._minute));
    }

    /**
     * 
     * Check if this time is before a received time.
     * 
     * @param other The time to check if this point is before.
     * @return True if this time is before other time.
     * 
     */

    public boolean after (Time1 other)
    {
        return other.before(this);
    }

    /**
     * 
     * Calculates the difference (in minutes) between two times.
     * Assumption: this time is after other time.
     * 
     * @param other The time to check the difference to.
     * @return int difference in minutes.
     * 
     */

    public int difference (Time1 other)
    {
        return Math.abs(this.minFromMidnight() - other.minFromMidnight()); // the return is positive 
    }
} // End of class Time1
