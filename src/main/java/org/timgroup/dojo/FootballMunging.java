package org.timgroup.dojo;

import fj.data.Stream;

import static java.lang.Integer.valueOf;

public class FootballMunging {

    private GenericMunging<String> munging;

    public FootballMunging(Stream<String> lines) {
        this.munging = new GenericMunging<String>(lines);
    }

    public String teamWithSmallestDifferenceBetweenForAndAgainst() {
        return munging.munge(new Converter<String>() {
            @Override
            public GenericMunging.MungingData<String> convert(String[] fields) {
                return new GenericMunging.MungingData<String>(fields[1], Math.abs(valueOf(fields[6]) - valueOf(fields[8])));
            }
        });
    }
}
