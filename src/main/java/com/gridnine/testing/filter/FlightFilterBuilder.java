package com.gridnine.testing.filter;

import com.gridnine.testing.model.Flight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FlightFilterBuilder {
    private final List<FlightFilter> filters;

    private FlightFilterBuilder(List<FlightFilter> filters) {
        this.filters = new ArrayList<>(filters);
    }

    public static FlightFilterBuilder of(FlightFilter... filters) {
        return new FlightFilterBuilder(Arrays.asList(filters));
    }

    public static FlightFilterBuilder builder() {
        return new FlightFilterBuilder(Collections.emptyList());
    }

    public FlightFilterBuilder addFilter(FlightFilter filter) {
        filters.add(filter);
        return this;
    }

    public FlightFilter build() {
        return new FlightFilterComposite(filters);
    }
}

class FlightFilterComposite implements FlightFilter {
    private final List<FlightFilter> filters;

    FlightFilterComposite(List<FlightFilter> filters) {
        this.filters = new ArrayList<>(filters);
    }

    @Override
    public boolean filter(Flight flight) {
        for (FlightFilter filter : filters) {
            if (!filter.filter(flight))
                return false;
        }
        return true;
    }
}