package com.github.alina;

public class Main {
    public static void main(String[] args){
        // Successful Booking test (task 1)
        System.out.println("Test1; Booking succesfull");
        FlightService.bookFlight(1, 1, 3);

        // Booking with invalid Flight ID test
        System.out.println("\nTest 2: Booking with Invalid Flight ID");
        try {
            FlightService.bookFlight(1, 231, 3);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // Parameters: valid customer ID, valid flight ID, and excessive number of passengers
        try {
            FlightService.bookFlight(1, 1, 500);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
