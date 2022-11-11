package com.gridnine.testing;

import com.gridnine.testing.filter.*;
import com.gridnine.testing.model.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        List<Flight> allFlights = FlightBuilder.createFlights();

        FlightFilter filterDepartBeforeNow = new FlightFilterDepartBeforeNow();
        FlightFilter filterArrivalBeforeDepart = new FlightFilterArrivalBeforeDepart();
        FlightFilter filterMoreThanGroundTime = new FlightFilterMoreThanGroundTime(2, TimeUnit.HOURS);
        FlightFilter filterComposite = FlightFilterBuilder.of(
                filterDepartBeforeNow,
                filterArrivalBeforeDepart,
                filterMoreThanGroundTime
        ).build();

        filterAndPrintFlights("Flights without depart in past:", allFlights, filterDepartBeforeNow);

        filterAndPrintFlights("Flights without arrival before depart:", allFlights, filterArrivalBeforeDepart);

        filterAndPrintFlights("Flights with no more than two hours on ground:", allFlights, filterMoreThanGroundTime);

        filterAndPrintFlights("Flights with all filters above:", allFlights, filterComposite);
    }

    private static void filterAndPrintFlights(String message, List<Flight> flights, FlightFilter filter) {
        List<Flight> flightsFiltered = filter.filter(flights);
        printFlights(message, flightsFiltered);
    }

    private static void printFlights(String message, List<Flight> flights) {
        System.out.println(message);

        for (Flight flight : flights)
            System.out.println("- " + flight);

        System.out.println();
    }
}