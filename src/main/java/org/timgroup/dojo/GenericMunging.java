package org.timgroup.dojo;

import fj.data.Stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenericMunging<T> {

    private final Stream<String> lines;

    public GenericMunging(Stream<String> lines) {
        this.lines = lines;
    }

    public T munge(Converter<T> converter) {
        final List<MungingData<T>> mungingData = new ArrayList<MungingData<T>>();
        for (String line : lines) {
            final String cleanLine = line.trim().replaceAll("\\*", "");
            if (cleanLine.matches("^\\d.*")) {
                final String[] fields = cleanLine.split("\\s+");
                mungingData.add(converter.convert(fields));
            }
        }
        Collections.sort(mungingData);
        return mungingData.get(0).label;
    }

    public static class MungingData<T> implements Comparable<MungingData<T>> {
        final T label;
        final int spread;

        public MungingData(T label, int spread) {
            this.label = label;
            this.spread = spread;
        }

        @Override
        public int compareTo(MungingData<T> tSpread) {
            return spread - tSpread.spread;
        }
    }

}
