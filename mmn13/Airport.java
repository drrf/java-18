
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
        int index;
        String headline = "The flights for airport " + _airport + " are:";
        System.out.println (headline);
        for (index = 0; index <= _noOfFlights; index ++){
            if(_flightsSchedule[index] != null)
                System.out.println(_flightsSchedule[index].toString());
        }
        return ("");
    }

    private boolean noOfFlight (){
        return _noOfFlights < MAX_FLIGHTS;
    }

    private boolean checkDestOrigin (){
        int index;
        if(_flightsSchedule[_noOfFlights - 1].getDestination().equals(_airport) ||
        _flightsSchedule[_noOfFlights - 1].getOrigin().equals(_airport))
            return true;
        else
            return false;
    }

    public boolean addFlight (Flight f){
        if (_noOfFlights == MAX_FLIGHTS) return false; // check if array full
        
        _flightsSchedule [_noOfFlights++] = new Flight (f); // add flight and count++
        if (checkDestOrigin()){
            for (int index = _noOfFlights; index < _noOfFlights; index++){
                _flightsSchedule [index] = new Flight (f);
            }
            return true;
        } else {
            _noOfFlights--;
            return false;
        }
    } // End of if

    public boolean removeFlight(Flight f){
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
        for (int index = 0; index < _noOfFlights; index++){
            if (!place.equals(_flightsSchedule[index].getOrigin()) && (!place.equals(_flightsSchedule[index].getDestination()))){
                flag = false;
            } else {
                flag = true;
                first++;
                if(first == 1)
                    highest = _flightsSchedule[index];
                if(first != 1 && _flightsSchedule[index].getDeparture().before(highest.getDeparture()))
                    highest = _flightsSchedule[index];
            }
        }
        if(flag = false)
            return null;
        else
            return new Time1 (highest.getDeparture()); // avoid aliasing 
    }

    public int howManyFullFlights(){
        int fullFlightsNum = 0;
        for (int index = 0; index < _noOfFlights; index++){
            if(_flightsSchedule[index].getIsFull())
                fullFlightsNum++;
        }
        return fullFlightsNum;
    }

    public int howManyFlightsBetween (String city1, String city2){
        int sum = 0;
        for (int index = 0; index < _noOfFlights; index++){
            if (city1.equals(_flightsSchedule[index].getOrigin()) &&
            (city2.equals(_flightsSchedule[index].getDestination())) ||
            (city2.equals(_flightsSchedule[index].getOrigin()) &&
                (city1.equals(_flightsSchedule[index].getDestination()))))
                sum++;
        }
        return sum;
    }

    public String mostPopularDestination(){
        int key, most = 1;
        String temp;
        String mostPopular = _flightsSchedule[0].getDestination();
        for (int index = 0; index < _noOfFlights; index++){
            temp = _flightsSchedule[index].getDestination();
            key = 1;
            for (int k = 1; k < _noOfFlights; k++){
                if (temp.equals(_flightsSchedule[k].getDestination())){
                    key++;
                }
            }
            if (key > most){
                most = key;
                mostPopular = _flightsSchedule[index].getDestination();
            }
        }
        return mostPopular;
    }

    public Flight mostExpensiveTicket(){
        int temp;
        int key = _flightsSchedule[0].getPrice();
        Flight mostExpensive = _flightsSchedule[0];
        for (int index = 1; index < _noOfFlights; index++){
            temp = _flightsSchedule[index].getPrice();
            if (temp > key){
                mostExpensive = _flightsSchedule[index];
                key = _flightsSchedule[index].getPrice();
            }
        }
        return new Flight (mostExpensive); // avoid aliasing
    }

    public Flight longestFlight () {
        int temp;
        int key = _flightsSchedule[0].getFlightDuration();
        Flight longestFlight = _flightsSchedule[0];
        for (int index = 0; index < _noOfFlights; index++){
            temp = _flightsSchedule[index].getFlightDuration();
            if (temp > key){
                longestFlight = _flightsSchedule[index];
                key = _flightsSchedule[index].getFlightDuration();
            }
        }
        return new Flight (longestFlight); // avoid aliasing
    }
}
