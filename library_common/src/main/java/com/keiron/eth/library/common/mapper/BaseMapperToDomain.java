package com.keiron.eth.library.common.mapper;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMapperToDomain<SOURCE, TARGET> {

    /**
     * Transforms a type into another
     *
     * @param toBeTransformed source that will be transformed
     * @return the transformed object
     */
    public abstract TARGET mapToDomain(SOURCE toBeTransformed);

    /**
     * Transforms a list of types into another type
     *
     * @param list list of sources that will be transformed
     * @return the list of transformed objects
     */
    public List<TARGET> mapToDomain(List<SOURCE> list) {
        List<TARGET> targetList = new ArrayList<>();

        for (SOURCE source : list) {
            targetList.add(mapToDomain(source));
        }

        return targetList;
    }
}
