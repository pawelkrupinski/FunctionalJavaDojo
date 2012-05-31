package org.timgroup.dojo;

import fj.data.Stream;

import static java.lang.Integer.valueOf;

public class WeatherMunging {
    private GenericMunging<Integer> munging;

    public WeatherMunging(Stream<String> lines) {
        this.munging = new GenericMunging<Integer>(lines);
    }

    public int dayWithTheSmallestTemperatureSpread() {
        return munging.munge(new Converter<Integer>() {
            @Override public GenericMunging.MungingData<Integer> convert(String[] fields) {
                return new GenericMunging.MungingData<Integer>(valueOf(fields[0]), valueOf(fields[1]) - valueOf(fields[2]));
            }
        });
    }
}
