package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;
import com.gridnine.testing.model.Segment;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlightFilterMoreThanGroundTime implements FlightFilter {
    private final long maxTimeOnGroundSeconds;

    public FlightFilterMoreThanGroundTime(long maxTime, TimeUnit timeUnit) {
        this.maxTimeOnGroundSeconds = timeUnit.toSeconds(maxTime);
    }

    @Override
    public boolean filter(Flight flight) {
        long totalTime = 0;
        List<Segment> segments = flight.getSegments();
        if (segments.size() <= 1) {
            return true;
        }
        Segment previous = null;
        for (Segment segment : segments) {
            if (previous == null) {
                previous = segment;
                continue;
            }

            totalTime += Duration.between(previous.getArrivalDate(), segment.getDepartureDate()).toSeconds();

            previous = segment;
        }
        return totalTime <= maxTimeOnGroundSeconds;
    }
}