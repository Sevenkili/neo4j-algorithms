/*
 * Copyright (c) 2013-2015 GraphAware
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

package com.graphaware.module.algo.generator.config;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Unit test for {@link ErdosRenyiConfig}.
 */
public class ErdosRenyiConfigTest {

    @Test
    public void shouldCorrectlyEvaluateValidConfig() {
        assertFalse(new ErdosRenyiConfig(-1, 5).isValid());
        assertFalse(new ErdosRenyiConfig(0, 5).isValid());
        assertFalse(new ErdosRenyiConfig(1, 5).isValid());
        assertFalse(new ErdosRenyiConfig(2, 5).isValid());
        assertFalse(new ErdosRenyiConfig(3, 5).isValid());
        assertTrue(new ErdosRenyiConfig(4, 5).isValid());
        assertTrue(new ErdosRenyiConfig(5, 5).isValid());
        assertTrue(new ErdosRenyiConfig(10000, 5).isValid());

        assertFalse(new ErdosRenyiConfig(10000, -1).isValid());
        assertFalse(new ErdosRenyiConfig(10000, 0).isValid());
        assertTrue(new ErdosRenyiConfig(10000, 1).isValid());

        assertTrue(new ErdosRenyiConfig(10_000, 5_000 * (10_000 - 1)).isValid());
        assertFalse(new ErdosRenyiConfig(10_000, 5_000 * (10_000 - 1) + 1).isValid());
        assertFalse(new ErdosRenyiConfig(50_000, 25_000 * (50_000 - 1) + 1).isValid());

        assertTrue(new ErdosRenyiConfig(10_000_000, 500_000_000).isValid());
        assertTrue(new ErdosRenyiConfig(100_000_000, 500_000_000).isValid());
        assertTrue(new ErdosRenyiConfig(1_000_000_000, 2_000_000_000).isValid());
        assertTrue(new ErdosRenyiConfig(50_000, 1_249_974_999).isValid());
        assertTrue(new ErdosRenyiConfig(50_000, 1_249_975_000).isValid());
        assertFalse(new ErdosRenyiConfig(50_000, 1_249_975_001).isValid());
        assertFalse(new ErdosRenyiConfig(50_000, 1_249_975_002).isValid());
    }
}
