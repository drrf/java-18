
/**
 * This class represents a Airport.
 * A Airport object is represented by Flight array,
 * The city of Airport, and the number of flights.
 *
 * @author Ron F.
 * @version 1.0
 * @date 2017-12-09
 */
public class Airport
{
    // all the attributes
    private Flight [] _flightsSchedule;
    private int _noOfFlights;
    private String _airport;

    // all the finals
    public final int MAX_FLIGHTS = 200;
    public final String DEFAULT_NAME = "Null";

    /**
     * Constructor for a Airport object.
     * If the city == null, _airport set the DEFAULT_NAME, else _airport set "city".
     * The maximum numbers of Flight array is set from the final MAX_FLIGHTS.
     * Initializing _noOfFlights as a counter of flights in this airport, starting from 0.
     * 
     * @param city The city of Airport location.
     */

    public Airport (String city){
        if (city == null){
            _airport = DEFAULT_NAME;
        } else {
            _airport = city;
        }
        _flightsSchedule = new Flight [MAX_FLIGHTS];
        _noOfFlights = 0; // Represents the flights counter
    }

    /**
     * Return a String representation of the airport flights
     * (for example: "The flights for airport Tel-Aviv today are:
     * Flight from London to Paris departs at 09:24. Flight is full.").
     * 
     * @return String representation of this flight (for example: "The flights for airport Tel-Aviv today are:
     * Flight from London to Paris departs at 09:24.Flight is full.").
     */

    public String toString(){
        String str = "The flights for airport " + _airport + " today are:\n";
        for (int i = 0; i <= _noOfFlights; i++){ // for loop from 0 to <= noOfFlights, to print all the flights in the array
            if(_flightsSchedule[i] != null){
                str += _flightsSchedule[i].toString()+ "\n"; // toString from Flight class 
            }
        } // End of for
        return (str);
    }

    /**
     * Add flight to airport Flight array.
     * If the number of Flight exceeds the maximum capacity, no flight are added, and false returned.
     * If the flight (f) == null, or Origin == null or Destination == null, no flight are added, and false returned.
     * If the flight Origin or Destination equals to airport location,
     * add flight and count++, else return false.
     * 
     * @param f The Flight object to adding to Airport flight schedule.
     * 
     * @return True if the flight add to Flight array.
     */

    public boolean addFlight (Flight f){
        if (_noOfFlights == MAX_FLIGHTS) return false; // check if array full
        if(f == null || f.getOrigin() == null || f.getDestination() == null) return false;  // check if enter null

        if (f.getOrigin().equals(this._airport) || f.getDestination().equals(this._airport)){ // check if this flight is from this airport
            _flightsSchedule [_noOfFlights++] = new Flight (f); // add flight and count++
            return true;
        } else {
            return false;
        }// End of if
    } 

    /**
     * Remove flight From airport Flight array.
     * If the flight (f) == null, no flight remove, and false returned.
     * For loop to found the flight to be removed, 
     * another For loop to move the rest of the flights 1 step back (override), 
     * remove the last flight from Flight array, and counter minus by 1.
     * 
     * @param f The Flight object to remove from Airport flight schedule.
     * 
     * @return True if the flight remove from Flight array.
     */

    public boolean removeFlight(Flight f){
        if(f == null) return false;   // check if enter null

        for (int i = 0; i < _noOfFlights; i++){  // for loop from 0 to < noOfFlights, to found the flight to be removed
            if(_flightsSchedule[i].equals(f)){ // found the flight to be removed
                for (int j = i; j < _noOfFlights-1; j++){ // move the rest of the flights 1 step back
                    _flightsSchedule[j] = _flightsSchedule[j+1];
                    _flightsSchedule[_noOfFlights - 1] = null;
                    _noOfFlights--;
                    return true;
                }
            } // End of if
        } // End of external for
        return false; // flight wasn't found
    }

    /**
     * Returns the first flight departure time from destination.
     * If the counter of flights is 0, null returned.
     * If the flight (f) == null, null returned.
     * for loop from 0 to < noOfFlights, to found the place flight,
     * if don't found return null, else found the first flight from destination.
     * 
     * @param place The place to search the first flight from destination.
     * 
     * @return A copy of departure time of the first flight from destination (place).
     */

    public Time1 firstFlightFromDestination (String place){
        if (_noOfFlights == 0) return null;
        if(place == null) return null; // check if enter null
        boolean flag = true;
        int first = 0;
        Flight highest = _flightsSchedule[0]; // initializing flight instant to be return
        for (int i = 0; i < _noOfFlights; i++){ // for loop from 0 to < noOfFlights, to found the place flight
            if (!place.equals(_flightsSchedule[i].getOrigin())){ // if place don't found
                flag = false;
            } else {
                flag = true;
                first++;
                if(first == 1) // the first time we found the place
                    highest = _flightsSchedule[i];
                if(first != 1 && _flightsSchedule[i].getDeparture().before(highest.getDeparture())) // check if i before what we found
                    highest = _flightsSchedule[i];
            } // End of if
        } // End of for
        if(flag == false) // if place don't found return null 
            return null; 
        else
            return new Time1 (highest.getDeparture()); // avoid aliasing 
    }
    
    /**
     * Calculate How many full flights.
     * If the counter of flights is 0, return err -1.
     * For loop from 0 to < noOfFlights, to found how many full flights,
     * for each found of full flight the counter plus by 1.
     * 
     * @return The number of the full flight.
     */

    public int howManyFullFlights(){
        if (_noOfFlights == 0) return (-1); // if no flights, err return -1
        int fullFlightsNum = 0; // counter full flight
        for (int i = 0; i < _noOfFlights; i++){ // for loop from 0 to < noOfFlights, to found how many full flights
            if(_flightsSchedule[i].getIsFull())
                fullFlightsNum++;
        }
        return fullFlightsNum;
    }
    
    /**
     * Calculate how many flights between the cities.
     * If the counter of flights is 0, return err -1.
     * if city1 or city2 are null, return err -2
     * For loop from 0 to < noOfFlights, to found how many flights between the cities,
     * for each flight found the counter plus by 1.
     * 
     * @param city1 The first city place to be check how many flights between.
     * @param city2 The second city place to be check how many flights between.
     * 
     * @return The number of the flight between city1 to city2.
     */

    public int howManyFlightsBetween (String city1, String city2){
        if (_noOfFlights == 0) return (-1); // if no flights, return err -1
        if(city1 == null || city2 == null) return (-2); // if city are null, return err -2
        int sum = 0; // counter how many
        String strOrigin, strDest;
        for (int i = 0; i < _noOfFlights; i++){  // for loop from 0 to < noOfFlights, to found how many flights between the cities
            strOrigin = _flightsSchedule[i].getOrigin();
            strDest = _flightsSchedule[i].getDestination();
            if (city1.equals(strOrigin) && (city2.equals(strDest)) ||
            (city2.equals(strOrigin) && (city1.equals(strDest))))
                sum++;
        }
        // System.out.println("The city1 " + city1 + ", the city2 " + city2);
        return sum;
    }
    
    /**
     * Calculate the most popular destination.
     * If the counter of flights is 0, return null.
     * External for loop from 0 to < noOfFlights, to found the first most popular destination,
     * Internal for loop to equals the most popular destination by itself to found how many flights, and counter plus by 1.
     * If found another one that bigger from temp most, make a swap between them.
     * 
     * @return String of the most popular Destination.
     */

    public String mostPopularDestination(){
        if(_noOfFlights == 0) return null;
        int key, most = 1;
        String temp; // repesent temp destination to check if most
        String mostPopular = _flightsSchedule[0].getDestination();  // initializing destination to be return
        for (int i = 0; i < _noOfFlights; i++){ // for loop to found the most popular destination
            temp = _flightsSchedule[i].getDestination(); // initializing temp most destination
            key = 1;
            for (int k = 1; k < _noOfFlights; k++){ // for loop to equals the most popular destination by itself
                if (temp.equals(_flightsSchedule[k].getDestination())){
                    key++; // counter
                }
            } // End of internal for
            if (key > most){ 
                most = key; // swap to the most popular destination
                mostPopular = _flightsSchedule[i].getDestination();
            }
        } // End of external for
        // System.out.println("The most Popular num: " + most);
        return mostPopular;
    }

    /**
     * Calculate the most expensive ticket.
     * If the counter of flights is 0, return null.
     * For loop from 0 to < noOfFlights, to found the the most expensive ticket,
     * if found another one that bigger from temp most, make a swap between them.
     * 
     * @return The flight with the most expensive ticket.
     */
    
    public Flight mostExpensiveTicket(){
        if(_noOfFlights == 0) return null;
        int temp;
        int key = _flightsSchedule[0].getPrice();
        Flight mostExpensive = _flightsSchedule[0]; // initializing the most expensive flight to be return
        for (int i = 0; i < _noOfFlights; i++){  // for loop to found the most expensive ticket.
            temp = _flightsSchedule[i].getPrice();  // initializing the temp most expensive ticket.
            if (temp > key){
                key = _flightsSchedule[i].getPrice(); // swap to the most expensive ticket
                mostExpensive = _flightsSchedule[i];
            }
        } // End of for
        // System.out.println("The most Expensive: " + key);
        return new Flight (mostExpensive); // avoid aliasing
    }

    /**
     * Calculate the longest flight.
     * If the counter of flights is 0, return null.
     * For loop from 0 to < noOfFlights, to found the the longest flight,
     * if found another one that bigger from temp most, make a swap between them.
     * 
     * @return The flight with the longest flight duration.
     */
    
    public Flight longestFlight () {
        if(_noOfFlights == 0) return null;
        int temp;
        int key = _flightsSchedule[0].getFlightDuration();
        Flight longestFlight = _flightsSchedule[0]; // initializing the most expensive flight to be return
        for (int i = 0; i < _noOfFlights; i++){  // for loop to found the most expensive ticket.
            temp = _flightsSchedule[i].getFlightDuration(); // initializing the temp most expensive ticket.
            if (temp > key){
                key = _flightsSchedule[i].getFlightDuration();  // swap to the most expensive ticket
                longestFlight = _flightsSchedule[i];
            }
        }
        // System.out.println("The longest Flight: " + key);
        return new Flight (longestFlight); // avoid aliasing
    }
}
