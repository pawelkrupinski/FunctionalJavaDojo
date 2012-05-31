package org.timgroup.dojo;

import fj.data.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.lang.Integer.valueOf;

public class FootballMunging {

    private final Stream<String> lines;

    public FootballMunging(Stream<String> lines) {
        this.lines = lines;
    }

    public String teamWithSmallestDifferenceBetweenForAndAgainst() {
        final List<FootballData> data = new ArrayList<FootballData>();
        for (String line : lines) {
            final String cleanLine = line.trim().replaceAll("\\*", "");
            if (cleanLine.matches("^\\d.*")) {
                final String[] fields = cleanLine.split("\\s+");
                data.add(new FootballData(fields[1], valueOf(fields[6]), valueOf(fields[8])));
            }
        }
        Collections.sort(data);
        return data.get(0).team;
    }

    private class FootballData implements Comparable<FootballData> {
        private final String team;
        private int spread;

        public FootballData(String team, int forPoints, int againstPoints) {
            this.team = team;
            this.spread = Math.abs(forPoints - againstPoints);
        }

        @Override
        public int compareTo(FootballData footballData) {
            return spread - footballData.spread;
        }
    }
}
