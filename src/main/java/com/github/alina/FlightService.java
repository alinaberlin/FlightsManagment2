package com.github.alina;

import java.sql.*;

public class FlightService {

    public static void bookFlight(int customerId, int flightId, int numberOfPassengers) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            insertNewBooking(customerId,flightId, numberOfPassengers,conn);
            flightUpdate(flightId, numberOfPassengers, conn);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void bookFlightConectivityIssue(int customerId, int flightId, int numberOfPassengers) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            insertNewBooking(customerId,flightId, numberOfPassengers,conn);
            Thread.sleep(1000*60);
            flightUpdate(flightId, numberOfPassengers, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void flightUpdate(int flightId, int numbersOfPassengers, Connection conn) throws SQLException {
        //update seats available in the flight table
        String updateFlight = "update flight " +
                "set seats_available = seats_available - ? " +
                "where id = ? and seats_available - ? > 0  ";
        try (PreparedStatement flightUpdate = conn.prepareStatement(updateFlight);) {
            flightUpdate.setInt(1, numbersOfPassengers);
            flightUpdate.setInt(2, flightId);
            flightUpdate.setInt(3, numbersOfPassengers);
            int affectedRows = flightUpdate.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update flight failed, not rows affected");
            }

        }
    }

    private static void insertNewBooking(int costumerId, int flightId, int numberOfPassangers, Connection conn) throws SQLException {
        System.out.println("Connected to the database successfully");
        String insertBooking = "insert into booking (customer_id, flight_id, booking_date, nr_of_passengers, status) " +
                "values (?, ? , ?, ?, ?)";
        try (PreparedStatement bookingUpdate = conn.prepareStatement(insertBooking)) {
            bookingUpdate.setInt(1, costumerId);
            bookingUpdate.setInt(2, flightId);
            bookingUpdate.setDate(3, new java.sql.Date(System.currentTimeMillis()));
            bookingUpdate.setInt(4, numberOfPassangers);
            bookingUpdate.setString(5, "status");
            bookingUpdate.executeUpdate();
            int affectedRows = bookingUpdate.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Update flight failed, not rows affected");
            }
        }
    }

}

