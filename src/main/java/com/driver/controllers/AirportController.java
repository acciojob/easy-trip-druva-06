package com.driver.controllers;

import com.driver.AirportService;
import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import com.driver.services.AirportService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
public class AirportController {
<<<<<<< HEAD
    AirportService airportService = new AirportService();
=======
    AirportService airportservice=new AirportService();
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    @PostMapping("/add_airport")
    public String addAirport(@RequestBody Airport airport){
        airportservice.addAirport(airport);
        //Simply add airport details to your database
        //Return a String message "SUCCESS"
        airportService.addAirport(airport);
        return "SUCCESS";
    }

    @GetMapping("/get-largest-aiport")
    public String getLargestAirportName(){
        return airportservice.getLargestAirportName();
        //Largest airport is in terms of terminals. 3 terminal airport is larger than 2 terminal airport
<<<<<<< HEAD
        //In case of a tie return the Lexicographically smallest airportName
        return airportService.getLargestAirportName();
=======
        //Incase of a tie return the Lexicographically smallest airportName


>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }

    @GetMapping("/get-shortest-time-travel-between-cities")
    public double getShortestDurationOfPossibleBetweenTwoCities(@RequestParam("fromCity") City fromCity, @RequestParam("toCity")City toCity){
        return   airportservice.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity)  ;

<<<<<<< HEAD
        //Find the duration by finding the shortest flight that connects these 2 cities directly
        //If there is no direct flight between 2 cities return -1.
       return airportService.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);
=======
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }
    //Find the duration by finding the shortest flight that connects these 2 cities directly
    //If there is no direct flight between 2 cities return -1.




    @GetMapping("/get-number-of-people-on-airport-on/{date}")
    public int getNumberOfPeopleOn(@PathVariable("date") Date date,@RequestParam("airportName")String airportName){

        //Calculate the total number of people who have flights on that day on a particular airport
        //This includes both the people who have come for a flight and who have landed on an airport after their flight

<<<<<<< HEAD
        return airportService.getNumberOfPeopleOn(date,airportName);
=======
        return airportservice.getNumberOfPeopleOn(date,airportName);
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }

    @GetMapping("/calculate-fare")
    public int calculateFlightFare(@RequestParam("flightId")Integer flightId){

        //Calculation of flight prices is a function of number of people who have booked the flight already.
        //Price for any flight will be : 3000 + noOfPeopleWhoHaveAlreadyBooked*50
        //Suppose if 2 people have booked the flight already : the price of flight for the third person will be 3000 + 2*50 = 3100
        //This will not include the current person who is trying to book, he might also be just checking price

<<<<<<< HEAD
       return airportService.calculateFlightFare(flightId);
=======
        return airportservice.calculateFlightFare(flightId);
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3

    }


    @PostMapping("/book-a-ticket")
    public String bookATicket(@RequestParam("flightId")Integer flightId,@RequestParam("passengerId")Integer passengerId){

        //If the numberOfPassengers who have booked the flight is greater than : maxCapacity, in that case :
        //return a String "FAILURE"
        //Also if the passenger has already booked a flight then also return "FAILURE".
        //else if you are able to book a ticket then return "SUCCESS"
<<<<<<< HEAD
        return airportService.bookATicket(flightId,passengerId);
=======

        return airportservice.bookATicket(flightId,passengerId);
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }

    @PutMapping("/cancel-a-ticket")
    public String cancelATicket(@RequestParam("flightId")Integer flightId,@RequestParam("passengerId")Integer passengerId){

        //If the passenger has not booked a ticket for that flight or the flightId is invalid or in any other failure case
        // then return a "FAILURE" message
        // Otherwise return a "SUCCESS" message
        // and also cancel the ticket that passenger had booked earlier on the given flightId

<<<<<<< HEAD
       return airportService.cancelATicket(flightId,passengerId);
=======
        return airportservice.cancelATicket(flightId,passengerId);
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }


    @GetMapping("/get-count-of-bookings-done-by-a-passenger/{passengerId}")
    public int countOfBookingsDoneByPassengerAllCombined(@PathVariable("passengerId")Integer passengerId){

        //Tell the count of flight bookings done by a passenger: This will tell the total count of flight bookings done by a passenger :
<<<<<<< HEAD
       return airportService.countOfBookingsDoneByPassengerAllCombined(passengerId);
=======
        return airportservice.countOfBookingsDoneByPassengerAllCombined( passengerId);
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }

    @PostMapping("/add-flight")
    public String addFlight(@RequestBody Flight flight){
<<<<<<< HEAD

        airportService.addFlight(flight);
        //Return a "SUCCESS" message string after adding a flight.
       return "SUCCESS";
=======
        airportservice.addFlight(flight);
        //Return a "SUCCESS" message string after adding a flight.
        return "SUCCESS";
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }


    @GetMapping("/get-aiportName-from-flight-takeoff/{flightId}")
    public String getAirportNameFromFlightId(@PathVariable("flightId")Integer flightId){

        //We need to get the starting airportName from where the flight will be taking off (Hint think of City variable if that can be of some use)
        //return null incase the flightId is invalid or you are not able to find the airportName

<<<<<<< HEAD
        return airportService.getAirportNameFromFlightId(flightId);
=======
        return airportservice.getAirportNameFromFlightId(flightId);
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }


    @GetMapping("/calculate-revenue-collected/{flightId}")
    public int calculateRevenueOfAFlight(@PathVariable("flightId")Integer flightId){

        //Calculate the total revenue that a flight could have
        //That is of all the passengers that have booked a flight till now and then calculate the revenue
        //Revenue will also decrease if some passenger cancels the flight


<<<<<<< HEAD
        return airportService.calculateRevenueOfAFlight(flightId);
=======
        return airportservice.calculateRevenueOfAFlight( flightId);
>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
    }


    @PostMapping("/add-passenger")
    public String addPassenger(@RequestBody Passenger passenger){
        airportservice.addPassenger(passenger);
        //Add a passenger to the database
        //And return a "SUCCESS" message if the passenger has been added successfully.
<<<<<<< HEAD
        airportService.addPassenger(passenger);
=======

>>>>>>> afcc317afb66ee07c92b4bbe8b9035db089a9de3
        return "SUCCESS";
    }

}