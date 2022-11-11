package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.util.List;

public class FlightFilterArrivalBeforeDepart implements FlightFilter {
    @Override
    public boolean filter(Flight flight) {
        List<Segment> segments = flight.getSegments();

        for (Segment segment : segments) {
            if (segment.getArrivalDate().compareTo(segment.getDepartureDate()) < 0)
                return false;
        }
        return true;
    }
}