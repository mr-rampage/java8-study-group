package ca.wbac.study.java;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;

public class CollectorsTest {
    private Stream<Pet> petStream;

    @Before
    public void setUp() {
        List<Pet> pets = Arrays.asList(
                Pet.create("Fluffy", 1),
                Pet.create("Maximus", 10),
                Pet.create("George", 15),
                Pet.create("Pete", 5));
        petStream = pets.stream();
    }

    @Test
    public void testCounting() {
        assertThat(petStream.collect(Collectors.counting()), is(0));
    }

    @Test
    public void testMaxBy() {
        Comparator<Pet> petComparator = Comparator.comparingInt(Pet::getAge);
        assertThat(petStream.collect(Collectors.maxBy(petComparator)), is(0));
    }

    @Test
    public void testMinBy() {
        Comparator<Pet> petComparator = Comparator.comparingInt(Pet::getAge);
        assertThat(petStream.collect(Collectors.minBy(petComparator)), is(0));
    }

    @Test
    public void testAverage() {
        assertThat(petStream.collect(Collectors.averagingInt(Pet::getAge)), is(0));
    }

    @Test
    public void testJoiningStrings() {
        assertThat(petStream.map(Pet::getName).collect(Collectors.joining()), is(""));
    }

    @Test
    public void testSumming() {
        assertThat(petStream.collect(Collectors.summingInt(Pet::getAge)), is(0));
    }

    @Test
    public void testSummingAgain() {
        Collector<Pet, ?, Integer> ageReducer = Collectors.reducing(0, Pet::getAge, (last, current) -> last + current);
        assertThat(petStream.collect(ageReducer), is(0));
    }

    @Test
    public void testCountingAgain() {
        Integer initialValue = 0;
        Function<Pet, Integer> petTransformer = pet -> 1;
        BinaryOperator<Integer> aggregationFunction = (last, current) -> last + current;

        Collector<Pet, ?, Integer> counter = Collectors.reducing(initialValue, petTransformer, aggregationFunction);
        assertThat(petStream.collect(counter), is(0));
    }
}
