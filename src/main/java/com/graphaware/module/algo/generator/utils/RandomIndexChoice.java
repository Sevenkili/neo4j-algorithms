package com.graphaware.module.algo.generator.utils;

import java.util.*;

/**
 * Chooses an rank at random, omiting certain indices
 */
public class RandomIndexChoice {

    private final Random random = new Random();

    /**
     * Random rank choice with indices omitted.
     * <p/>
     * Warning: this algorithm does not terminate if omitIndices contains
     * all indices from 0 to length-1. Use this only if number
     * of entries in omitIndices is much less than length.
     *
     * @param length      range to pick indices from
     * @param omitIndices indices to be omited from the selection
     * @return rank from the range specified
     */
    public long randomIndexChoice(long length, Set<Long> omitIndices) {
        while (true) {
            long choice = (long) (random.nextDouble()*length);
            if (!omitIndices.contains(choice)) {
                return choice;
            }
        }
    }

    /**
     * Random rank choice with indices omitted.
     * <p/>
     * Warning: this algorithm does not terminate if omitIndices contains
     * all indices from 0 to length-1. Use this only if number
     * of entries in omitIndices is much less than length.
     *
     * @param length      range to pick indices from
     * @param omitIndices indices to be omited from the selection
     * @return rank from the range specified
     */
    public int randomIndexChoice(int length, Set<Integer> omitIndices) {
        while (true) {
            int choice = (int) (random.nextDouble()*length);
            if (!omitIndices.contains(choice)) {
                return choice;
            }
        }
    }

    /**
     * Random rank choice with indices omitted (long)
     *
     * @param length      range to pick indices from
     * @param omitIndices indices to be omited from the selection
     * @return rank from the range specified
     */
    public long randomIndexChoice(long length, PriorityQueue<Long> omitIndices) {
        int omitLength = omitIndices.size();
        //todo this will not fly - try using BigInteger
        long choice = (long) Math.floor((length - omitLength) * random.nextDouble());

        int offset = 0;
        Iterator<Long> it = omitIndices.iterator();

        while (it.hasNext() && choice + offset >= it.next())
            offset++;

        return choice + offset;

    }

    /**
     * Random rank choice with an rank omitted (int)
     *
     * @param length range to pick indices from
     * @param omit   rank to be omited from the selection
     * @return rank from the range specified
     */
    public int randomIndexChoice(int length, int omit) {
        return new Long(randomIndexChoice(new Integer(length).longValue(), new PriorityQueue<>(Collections.<Long>singleton(new Integer(omit).longValue())))).intValue();
    }

    /**
     * Random rank choice (int)
     *
     * @param length range to pick indices from
     * @return rank from the range specified
     */
    public int randomIndexChoice(int length) {
        return random.nextInt(length);
    }
}
