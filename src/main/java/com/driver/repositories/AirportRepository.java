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
        return mn!=Integer.MAX_VALUE?mn:-1;
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        if(flightPassengerDb.get(flightId)!=null
                && (flightPassengerDb.get(flightId).size() < flightDb.get(flightId).getMaxCapacity())){
            List<Integer> passengers =  flightPassengerDb.get(flightId);
            if(passengers.contains(passengerId)) return "FAILURE";
            passengers.add(passengerId);
            flightPassengerDb.put(flightId,passengers);
            return "SUCCESS";
        }
        else if(flightPassengerDb.get(flightId)==null) {
            flightPassengerDb.put(flightId,new ArrayList<>());
            List<Integer> passengers =  flightPassengerDb.get(flightId);
            if(passengers.contains(passengerId)){
                return "FAILURE";
            }
            passengers.add(passengerId);
            flightPassengerDb.put(flightId,passengers);
            return "SUCCESS";
        }
        return "FAILURE";
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        List<Integer> passengers = flightPassengerDb.get(flightId);
        if(passengers == null) return "FAILURE";
        if(passengers.contains(passengerId)){
            passengers.remove(passengerId);
            return "SUCCESS";
        }
        return "FAILURE";
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
            if(flight.getFlightDate().compareTo(date) == 0) {
                if (flight.getFromCity().equals(city)
                        || flight.getToCity().equals(city)) {
                    totPeopleCount += flightPassengerDb.get(flightId).size();
                }
            }
        }
        return totPeopleCount;
    }

    public int calculateFlightFare(Integer flightId) {
        return 3000 + (flightPassengerDb.get(flightId).size()*50);
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        int totNoOfPassengers = flightPassengerDb.get(flightId).size();
        return (3000*totNoOfPassengers)+(50*(totNoOfPassengers*(totNoOfPassengers+1))/2);
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
