/*
 * Copyright (c) 2013-2016 GraphAware
 *
 * This file is part of the GraphAware Framework.
 *
 * GraphAware Framework is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package com.graphaware.module.algo.generator.utils;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

/**
 * Utility to allow for weighted reservoir sampling
 * using A-ES (Algorithm of Efraimidis and Spirakis) algorithm from:
 *
 * Weighted Random Sampling over Data Streams
 * by Pavlos S. Efraimidis
 * (Democritus University of Thrace)
 *
 * I needed only the first element. The class could easily be extended to
 * mimic ReservoirSampler.
 *
 */
public class WeightedReservoirSampler {
    private final Random random;

    /**
     * Create a new sampler with a certain reservoir size.
     */
    public WeightedReservoirSampler() {
        random = new Random();
    }


    /**
     * Returns an integer at random, weighted according to its index
     * @param weights weights to sample from
     * @return index chosen according to the weight supplied
     */
    public int randomIndexChoice(List<Integer> weights) {
        int result = 0, index;
        double maxKey = 0.0;
        double u, key;
        int weight;

        for (ListIterator<Integer> it = weights.listIterator(); it.hasNext(); ) {
            index = it.nextIndex();
            weight = it.next();
            u = random.nextDouble();
            key = Math.pow(u, (1.0/weight)); // Protect from zero division?

            if (key > maxKey) {
                maxKey = key;
                result = index;
            }
        }

        return result;
    }

    /**
     * Returns an integer at random, weighted according to its index,
     * omitting given indices
     * @param weights weights to sample from
     * @param omitIndices indices to omit from sampling
     * @return index chosen according to the weight supplied
     */
    public int randomIndexChoice(List<Integer> weights, Set<Integer> omitIndices) {
        int result = 0, index;
        double maxKey = 0.0;
        double u, key;
        int weight;

        for (ListIterator<Integer> it = weights.listIterator(); it.hasNext(); ) {
            index = it.nextIndex();
            weight = it.next();

            if (omitIndices.contains(index))
                continue;

            u = random.nextDouble();
            key = Math.pow(u, (1.0 / weight));

            if (key > maxKey) {
                maxKey = key;
                result = index;
            }
        }

        return result;
    }


    /**
     * Returns an integer at random, weighted according to its index,
     * omitting a single index.
     * This is very specific override used in the Simple graph
     * algorithm.
     * @param weights list of weights to sample from
     * @param omit index to omit from sampling
     * @return index chosen according to the weight supplied
     */
    public int randomIndexChoice(List<Integer> weights, int omit) {
        int result = 0, index;
        double maxKey = 0.0;
        double u, key;
        int weight;

        for (ListIterator<Integer> it = weights.listIterator(); it.hasNext(); ) {
            index = it.nextIndex();
            weight = it.next();

            if (index == omit)
                continue;

            u = random.nextDouble();
            key = Math.pow(u, (1.0/weight));

            if (key > maxKey) {
                maxKey = key;
                result = index;
            }
        }

        return result;
    }
}
