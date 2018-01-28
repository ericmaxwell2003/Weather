package com.acme.weather.model.api;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class FibinacciTest {

    public int fibRecurssion(int n) {
        if(n < 0) {
            throw new IllegalArgumentException();
        } else if(n <= 1) {
            return n;
        } else {
            return fibRecurssion(n-1) + fibRecurssion(n-2);
        }
    }

    public int fibIterative(int n) {

        if(n < 0) {
            throw new IllegalArgumentException();
        } else if(n <= 1) {
            return n;
        } else {
            int[] fibSeq = new int[n+1];

            fibSeq[0] = 0;
            fibSeq[1] = 1;

            for(int i = 2; i <= n; i++) {
                fibSeq[i] = fibSeq[i-1] + fibSeq[i-2];
            }

            return fibSeq[n];
        }
    }

    @Test
    public void testSeed0() {
        assertEquals(0, fibRecurssion(0));
    }

    @Test
    public void testSeed1() {
        assertEquals(1, fibRecurssion(1));
    }

    @Test
    public void testFibSeq() {
        int[] series = {0,1,1,2,3,5,8,13,21};

        for(int i = 0; i < series.length; i++) {
            assertEquals(series[i], fibRecurssion(i));
            assertEquals(fibIterative(i), fibRecurssion(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidN() {
        fibRecurssion(-1);
        fibIterative(-1);
    }

}
