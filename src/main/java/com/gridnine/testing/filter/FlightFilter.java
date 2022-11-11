package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.List;
import java.util.stream.Collectors;

public interface FlightFilter {

    boolean filter(Flight flight);

    default List<Flight> filter(List<Flight> flights) {
        return flights.stream().filter(this::filter).collect(Collectors.toList());
    }
}