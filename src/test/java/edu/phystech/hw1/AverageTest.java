package edu.phystech.hw1;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class AverageTest {

    private static double average(long... numbers) {
        if (numbers.length == 0) {
            return 0;
        }

        long sum = 0;
        for (long num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }

    @Test
    public void calculateAverage() {
        Assertions.assertEquals(5d, average(5));
        Assertions.assertEquals(4d, average(3, 5));
        Assertions.assertEquals(4d, average(3, 4, 5));

        Assertions.assertEquals(0, average());
        Assertions.assertEquals(3.5, average(1, 2, 3, 4, 5, 6));
        Assertions.assertEquals(6.5, average(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
    }
}
