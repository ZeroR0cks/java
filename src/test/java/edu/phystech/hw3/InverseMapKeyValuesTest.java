package edu.phystech.hw3;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InverseMapKeyValuesTest {

    public static <K, V> Map<V, Collection<K>> inverse(Map<? extends K, ? extends V> map) {

        Collection<K> ones = new HashSet<>();
        Collection<K> twos = new HashSet<>();
        V oneKey = null;
        V twoKey = null;

        for (Map.Entry<? extends K, ? extends V> entry : map.entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            if (oneKey == null || oneKey.equals(value)) {
                oneKey = value;
                ones.add(key);
            } else {
                twoKey = value;
                twos.add(key);
            }
        }

        if (twoKey == null) {
            return Map.of(oneKey, ones);
        } else {
            return Map.of(oneKey, ones, twoKey, twos);
        }
    }

    @Test
    void noDuplicateValuesTest() {
        Map<Integer, String> originalMap = Map.of(1, "one", 2, "two");
        Map<String, Collection<Integer>> expectedMap = Map.of("one", List.of(1), "two", List.of(2));

        Map<String, Collection<Integer>> actualMap = inverse(originalMap);

        Assertions.assertEquals(expectedMap.size(), actualMap.size());

        expectedMap.forEach((key, value) -> {
            var entry = actualMap.get(key);
            Assertions.assertNotNull(entry);
            Assertions.assertEquals(new HashSet<>(value), new HashSet<>(entry));
        });

    }

    @Test
    void duplicateValuesTest() {
        Map<Integer, String> originalMap = Map.of(1, "one", 2, "two", 3, "two");
        Map<String, Collection<Integer>> expectedMap = Map.of("one", List.of(1), "two", List.of(2, 3));

        Map<String, Collection<Integer>> actualMap = inverse(originalMap);

        Assertions.assertEquals(expectedMap.size(), actualMap.size());

        expectedMap.forEach((key, value) -> {
            var entry = actualMap.get(key);
            Assertions.assertNotNull(entry);
            Assertions.assertEquals(new HashSet<>(value), new HashSet<>(entry));
        });

    }
}

