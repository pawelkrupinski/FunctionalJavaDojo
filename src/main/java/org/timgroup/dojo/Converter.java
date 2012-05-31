package org.timgroup.dojo;

public interface Converter<T> {
    GenericMunging.MungingData<T> convert(String[] fields);
}
