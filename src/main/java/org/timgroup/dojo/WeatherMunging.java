package org.timgroup.dojo;

import fj.data.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.valueOf;

public class WeatherMunging {
    private final Stream<String> lines;

    public WeatherMunging(Stream<String> lines) {
        this.lines = lines;
    }

    public int dayWithTheSmallestTemperatureSpread() {
        final List<WeatherData> data = new ArrayList<WeatherData>();
        for (String line : lines) {
            final String cleanLine = line.trim().replaceAll("\\*", "");
            if (cleanLine.matches("^\\d.*")) {
                final String[] fields = cleanLine.split("\\s+");
                data.add(new WeatherData(valueOf(fields[0]), valueOf(fields[1]), valueOf(fields[2])));
            }
        }
        Collections.sort(data);
        return data.get(0).day;
    }

    public static class WeatherData implements Comparable<WeatherData> {
        private final int day;
        private final int spread;

        public WeatherData(int day, int max, int min) {
            this.day = day;
            this.spread = max - min;
        }

        @Override
        public int compareTo(WeatherData weatherData) {
            return spread - weatherData.spread;
        }
    }
}
