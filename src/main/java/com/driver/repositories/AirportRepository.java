package com.driver.repositories;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;

import java.util.*;

public class AirportRepository {
    HashMap<String,Airport> airportDb = new HashMap<>();
    HashMap<Integer,Flight> flightDb = new HashMap<>();
    HashMap<Integer,Passenger> passengerDb = new HashMap<>();
    HashMap<Integer, List<Integer>> flightPassengerDb = new HashMap<>();
    public void addAirport(Airport airport){
        airportDb.put(airport.getAirportName(),airport);
    }
    public void addFlight(Flight flight){
        flightDb.put(flight.getFlightId(),flight);
        flightPassengerDb.put(flight.getFlightId(),new ArrayList<>());
    }
    public void addPassenger(Passenger passenger){
        passengerDb.put(passenger.getPassengerId(),passenger);
    }

    public String getLargestAirportName() {
        int mx = Integer.MIN_VALUE;
        String maxAirport = "";
        for(Airport airport: airportDb.values()){
            if(airport.getNoOfTerminals() >= mx){
                mx = airport.getNoOfTerminals();
                if(maxAirport.equals("")){
                    maxAirport = airport.getAirportName();
                }
                else{
                    if(maxAirport.compareTo(airport.getAirportName()) > 0){
                        maxAirport = airport.getAirportName();
                    }
                }
            }
        }
        return maxAirport;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        double mn = Integer.MAX_VALUE;
        for(Flight flight: flightDb.values()){
            if(flight.getFromCity().equals(fromCity) && flight.getToCity().equals(toCity)){
                mn = Math.min(mn,flight.getDuration());
            }
        }
        return mn;
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        if(flightDb.get(flightId).getMaxCapacity() == flightPassengerDb.get(flightId).size())
            return "FAILURE";
        for(Integer currPassengerId: flightPassengerDb.get(flightId)){
            if(Objects.equals(currPassengerId, passengerId)) return "FAILURE";
        }
        flightPassengerDb.get(flightId).add(passengerId);
        return "SUCCESS";
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        if(!flightPassengerDb.containsKey(flightId)) return "FAILURE";
        boolean flightBooked = false;
        for(Integer currPassengerId: flightPassengerDb.get(flightId)){
            if (Objects.equals(currPassengerId, passengerId)) {
                flightBooked = true;
                break;
            }
        }
        if(!flightBooked) return "FAILURE";
        for(int i=0;i<flightPassengerDb.get(flightId).size();i++){
            if(Objects.equals(flightPassengerDb.get(flightId).get(i), passengerId)){
                flightPassengerDb.get(flightId).remove(i);
                break;
            }
        }
        return "SUCCESS";
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        int totBooking = 0;
        for(Integer flightId: flightPassengerDb.keySet()){
            for(Integer currPassengerId: flightPassengerDb.get(flightId)){
                if(Objects.equals(currPassengerId,passengerId)) totBooking++;
            }
        }
        return totBooking;
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int totPeopleCount = 0;
        City city = airportDb.get(airportName).getCity();
        for(Integer flightId: flightPassengerDb.keySet()){
            Flight flight = flightDb.get(flightId);
            if(flight.getFlightDate().compareTo(date) == 0
                    && flight.getFromCity().equals(city)
                    && flight.getToCity().equals(city)){
                totPeopleCount+=flightPassengerDb.get(flightId).size();
            }
        }
        return totPeopleCount;
    }

    public int calculateFlightFare(Integer flightId) {
        return 3000 + (flightPassengerDb.get(flightId).size()*50);
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        int totNoOfPassengers = flightPassengerDb.get(flightId).size();
        return (3000*totNoOfPassengers)*(50*(totNoOfPassengers*(totNoOfPassengers+1))/2);
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        if(!flightDb.containsKey(flightId)) return null;
        City city = flightDb.get(flightId).getFromCity();
        for(Airport airport: airportDb.values()){
            if(airport.getCity().equals(city)) return airport.getAirportName();
        }
        return null;
    }
}
