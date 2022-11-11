package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.LocalDateTime;
import java.util.List;

public class FlightFilterDepartBeforeNow implements FlightFilter {
    @Override
    public boolean filter(Flight flight) {
        LocalDateTime now = LocalDateTime.now();
        List<Segment> segments = flight.getSegments();
        for (Segment segment : segments) {
            if (segment.getDepartureDate().isBefore(now)) {
                return false;
            }
        }
        return true;
    }
}