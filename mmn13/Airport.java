
/**
 * Write a description of class Airport here.
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

    public Airport (String city){
        _airport = city;
        _flightsSchedule = new Flight [MAX_FLIGHTS];
        _noOfFlights = 0;
    }

    public String toString(){
        String str = "The flights for airport " + _airport + "today are:\n";
        // System.out.println (headline);
        for (int i = 0; i <= _noOfFlights; i++){
            if(_flightsSchedule[i] != null){
                str += _flightsSchedule[i].toString() + "\n";
            }
        }
        return (str);
    }

    public boolean addFlight (Flight f){
        if (_noOfFlights == MAX_FLIGHTS) return false; // check if array full
        
        if(f == null) return false;
        
        if (f.getOrigin().equals(this._airport) || f.getDestination().equals(this._airport)){ // check if is from this airport
            _flightsSchedule [_noOfFlights++] = new Flight (f); // add flight and count++
            return true;
        } else {
            return false;
        }// End of if
    } 

    public boolean removeFlight(Flight f){
        if(f == null) return false;
        
        int i;
        for (i = 0; i < _noOfFlights; i++){
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

    public Time1 firstFlightFromDestination (String place){
        if(_noOfFlights == 0) return null; // check if empty array
        boolean flag = true;
        int first = 0;
        Flight highest = _flightsSchedule[0];
        for (int i = 0; i < _noOfFlights; i++){
            if (!place.equals(_flightsSchedule[i].getOrigin())){
                flag = false;
            } else {
                flag = true;
                first++;
                if(first == 1)
                    highest = _flightsSchedule[i];
                if(first != 1 && _flightsSchedule[i].getDeparture().before(highest.getDeparture()))
                    highest = _flightsSchedule[i];
            }
        }
        if(flag = false)
            return null;
        else
            return new Time1 (highest.getDeparture()); // avoid aliasing 
    }

    public int howManyFullFlights(){
        int fullFlightsNum = 0;
        for (int i = 0; i < _noOfFlights; i++){
            if(_flightsSchedule[i].getIsFull())
                fullFlightsNum++;
        }
        return fullFlightsNum;
    }

    public int howManyFlightsBetween (String city1, String city2){
        if(city1 == null || city2 == null) return (-1); // if null err return -1
        int sum = 0;
        String strOrigin, strDest;
        for (int i = 0; i < _noOfFlights; i++){
            strOrigin = _flightsSchedule[i].getOrigin();
            strDest = _flightsSchedule[i].getDestination();
            if (city1.equals(strOrigin) && (city2.equals(strDest)) ||
            (city2.equals(strOrigin) && (city1.equals(strDest))))
                sum++;
        }
        // System.out.println("The city1 " + city1 + ", the city2 " + city2);
        return sum;
    }

    public String mostPopularDestination(){
        if(_noOfFlights == 0) return null;
        int key, most = 1;
        String temp;
        String mostPopular = _flightsSchedule[0].getDestination();
        for (int i = 0; i < _noOfFlights; i++){
            temp = _flightsSchedule[i].getDestination();
            key = 1;
            for (int k = 1; k < _noOfFlights; k++){
                if (temp.equals(_flightsSchedule[k].getDestination())){
                    key++;
                }
            }
            if (key > most){
                most = key;
                mostPopular = _flightsSchedule[i].getDestination();
            }
        }
        // System.out.println("The most Popular num: " + most);
        return mostPopular;
    }

    public Flight mostExpensiveTicket(){
        if(_noOfFlights == 0) return null;
        int temp;
        int key = _flightsSchedule[0].getPrice();
        Flight mostExpensive = _flightsSchedule[0];
        for (int i = 0; i < _noOfFlights; i++){
            temp = _flightsSchedule[i].getPrice();
            if (temp > key){
                mostExpensive = _flightsSchedule[i];
                key = _flightsSchedule[i].getPrice();
            }
        }
        // System.out.println("The most Expensive: " + key);
        return new Flight (mostExpensive); // avoid aliasing
    }

    public Flight longestFlight () {
        if(_noOfFlights == 0) return null;
        int temp;
        int key = _flightsSchedule[0].getFlightDuration();
        Flight longestFlight = _flightsSchedule[0];
        for (int i = 0; i < _noOfFlights; i++){
            temp = _flightsSchedule[i].getFlightDuration();
            if (temp > key){
                longestFlight = _flightsSchedule[i];
                key = _flightsSchedule[i].getFlightDuration();
            }
        }
        // System.out.println("The longest Flight: " + key);
        return new Flight (longestFlight); // avoid aliasing
    }
}
