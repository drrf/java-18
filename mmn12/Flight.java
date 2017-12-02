
/**
 * Represents a flight. 
 * A Flight object is represented by the flight's origin,
 * destination,departure time, flight duration, no of passengers,
 * if it is full and the price.
 * 
 * @author Ron F.
 * @version 1.0
 * @date 2017-11-25
 */
public class Flight
{
    // all the attributes
    private String _origin;
    private String _destination;
    private Time1 _departure;
    private int _flightDuration;
    private int _noOfPassengers;
    private boolean _isFull;
    private int _price;

    // all the finals
    final int MAX_CAPACITY = 250;
    final int HOURS_IN_DAY = 24;
    final int MINUTES_IN_HOUR = 60;
    final int MINUTES_LOW_BOUND = 0;
    final int PASSENGERS_LOW_BOUND = 0;
    final int PRICE_LOW_BOUND = 0;

    /**
     * Constructor for a Flight object.
     * If the number of passengers exceeds the maximum capacity
     * the number of passengers will be set to the maximum capacity
     * If the number of passengers is negative the number of passengers will be set to zero.
     * If the flight duration is negative the flight duration will be set to zero.
     * If the price is negative the price will be set to zero.
     * 
     * @param dest The city the flight lands at.
     * @param origin The city the flight leaves from.
     * @param depHour The departure hour (should be between 0-23).
     * @param depMinute The departure minute (should be between 0-59).
     * @param durTimeMinutes The duration time in minutes(should not be negative).
     * @param noOfPass The number of passengers (should be between 0-maximum capacity).
     * @param price The price (should not be negative).
     */

    public Flight (String origin, String dest, int depHour, int depMinute, int durTimeMinutes, int noOfPass, int price)
    {
        _origin = origin;
        _destination = dest;
        _departure = new Time1 (depHour, depMinute);
        setFlightDuration(durTimeMinutes);

        if(noOfPass >= MAX_CAPACITY)
            _noOfPassengers = MAX_CAPACITY;
        else
            setNoOfPassengers(noOfPass);

        _isFull = getIsFull();
        setPrice(price);
    }

    /**
     * Copy constructor for a Flight object.
     * Construct a Flight object with the same attributes as another Flight object.
     * 
     * @param other The Flight object from which to construct the new Flight.
     */

    public Flight (Flight other)
    {
        this._origin = other._origin;
        this._destination = other._destination;
        this._departure = new Time1(other._departure);
        this._flightDuration = other._flightDuration;
        this._noOfPassengers = other._noOfPassengers;
        this._isFull = other._isFull;
        this._price = other._price;
    }

    /**
     * Return a string representation of this flight
     * (for example: "Flight from London to Paris departs at 09:24. Flight is full.").
     * 
     * @return String representation of this flight (for example: "Flight from London to Paris departs at 09:24.Flight is full.").
     */

    public String toString()
    {
        if(getIsFull()) // check if filght is full or not
            return ("Flight form " + getOrigin() + " to " + getDestination() + " departs at " + getDeparture() + ". Flight is full.");
        else
            return ("Flight form " + getOrigin() + " to " + getDestination() + " departs at " + getDeparture() + ". Flight is not full.");
    }

    /**
     * Returns the arrival time of the flight.
     * 
     * @return The arrival time of this flight.
     */

    public Time1 getArrivalTime()
    { 
        // get time arrival in minutes 
        int arrivalInMinutes = _flightDuration + _departure.minFromMidnight();
        
        // convert the minutes to Time (h,m)
        int h = (arrivalInMinutes / MINUTES_IN_HOUR) % HOURS_IN_DAY;
        int m = (arrivalInMinutes % MINUTES_IN_HOUR);

        // crate new instance in Time1 class
        Time1 arrivalTime = new Time1 (h, m);

        // return
        return arrivalTime;
    }

    /**
     * Returns the flight departure time.
     * 
     * @return A copy of the flight departure time.
     */

    public Time1 getDeparture()
    {
        return new Time1 (_departure); // aliasing, return must be a new copy!
    }

    /**
     * Changes the flight's departure time.
     * 
     * @param departureTime The flight's new departure time.
     */

    public void setDeparture (Time1 departureTime)
    {
        _departure = new Time1 (departureTime);
    }

    /**
     * Returns the flight origin.
     * 
     * @return The flight origin.
     */

    public String getOrigin()
    {
        return _origin;
    }

    /**
     * Changes the flight's origin.
     * 
     * @param origin The flight's new origin.
     */
    public void setOrigin (String origin)
    {
        _origin = origin;
    }

    /**
     * Returns the flight destination.
     * 
     * @return The flight destination.
     */

    public String getDestination()
    {
        return _destination;
    }

    /**
     * Changes the flight's destination.
     * 
     * @param dest The flight's new destination.
     */

    public void setDestination (String dest)
    {
        _destination = dest;
    }

    /**
     * Returns the flight duration time in minutes.
     * 
     * @return The flight duration.
     */

    public int getFlightDuration()
    {
        return _flightDuration;
    }

    /**
     * Changes the flight's duration time.
     * If the parameter is negative the duration time will remain unchanged.
     * 
     * @param durTimeMinutes The flight's new duration time.
     */

    public void setFlightDuration (int durTimeMinutes)
    {
        if(durTimeMinutes > MINUTES_LOW_BOUND) // negative check
            _flightDuration = durTimeMinutes;
    }

    /**
     * Returns the number of passengers on the flight.
     * 
     * @return The number of passengers.
     */

    public int getNoOfPassengers()
    {
        return _noOfPassengers;
    }

    /**
     * Changes the number of passengers.
     * If the parameter is negative or larger than the maximum capacity the number of passengers will remain unchanged.
     * Insert "0" (zero) is allowed.
     * 
     * @param noOfPass The new number of passengers.
     */

    public void setNoOfPassengers (int noOfPass)
    {
        if(noOfPass <= MAX_CAPACITY && noOfPass >= PASSENGERS_LOW_BOUND){ // negative & max check
            _noOfPassengers = noOfPass;
        }
    }

    /**
     * Returns the price of the flight.
     * 
     * @return The price.
     */

    public int getPrice()
    {
        return _price;
    }

    /**
     * Changes the flight price.
     * If the parameter is negative the price will remain unchanged.
     * Insert "0" (zero) is allowed.
     * 
     * @param price The new price.
     */

    public void setPrice(int price)
    {
        if(price >= PRICE_LOW_BOUND) // negative check
            _price = price;
    }

    /**
     * Returns whether the flight is full or not.
     * 
     * @return True if the flight is full.
     */

    public boolean getIsFull()
    {
        if (_noOfPassengers >= MAX_CAPACITY)
        {
            return _isFull = true;
        }
        else
            return false;
    }

    /**
     * Add passengers to this flight.
     * If the number of passengers exceeds the maximum capacity, no passengers are added and alse is returned.
     * If the flight becomes full, the boolean attribute describing whether the flight if full becomes true.
     * If the parameter is negative or zero the boolean will be false.
     * 
     * @param num The number of passengers to be added to this flight.
     * @return True if the passengers were added to the flight.
     */

    public boolean addPassengers(int num)
    {
        if(_noOfPassengers + num <= MAX_CAPACITY && num > PASSENGERS_LOW_BOUND)
        {
            _noOfPassengers += num;
            getIsFull(); // update for _isFull
            return true;
        }
        else
            return false;
    }

    /**
     * Check if the received flight is equal to this flight. Flights are considered equal if the origin,
     * destination and departure times are the same.
     * 
     * @param other The flight to be compared with this flight.
     * @return True if the received flight is equal to this flight.
     */

    public boolean equals(Flight other)
    {
        return (this._origin.equals(other._origin) && this._destination.equals(other._destination) && _departure.equals(other._departure));
    }

    /**
     * Check if this flight lands before another flight.
     * Note - the flights may land on different days, the method checks which flight lands first.
     * 
     * @param other The flight whose arrival time to be compared with this flight's arrival time.
     * @return True if this flight arrives before the received flight.
     */

    public boolean landsEarlier(Flight other)
    {
        int landsThis = this._departure.minFromMidnight() + this._flightDuration;
        int landsOther = other._departure.minFromMidnight() + other._flightDuration;
        return (landsThis < landsOther);
    } 

    /**
     * Check if this flight is cheaper than another flight.
     * 
     * @param other The flight whose price is to be compared with this flight's price.
     * @return True if this flight is cheaper than the received flight.
     */

    public boolean isCheaper(Flight other)
    {
        return (this._price < other._price);
    }

    /**
     * Calculate the total price of the flight.
     * 
     * @return The total price of the flight.
     */

    public int totalPrice()
    {
        return _price * _noOfPassengers;
    }
} // End of class Flight
