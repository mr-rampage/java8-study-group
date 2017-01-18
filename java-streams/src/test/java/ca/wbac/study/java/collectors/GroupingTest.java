package ca.wbac.study.java.collectors;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class GroupingTest {

    private List<Pet> pets;
    private Stream<Pet> petStream;

    @Before
    public void setUp() {
        pets = new ArrayList<>(Arrays.asList(
                Pet.create("Fluffy", Pet.Type.CAT, 1),
                Pet.create("Maximus", Pet.Type.DOG, 10),
                Pet.create("George", Pet.Type.DOG, 15),
                Pet.create("Pete", Pet.Type.FISH, 5)));
        petStream = pets.stream();
    }

    @Test
    public void testGroupingBy() {
        Map<Pet.Type, List<Pet>> result = new HashMap<Pet.Type, List<Pet>>() {
            {
                put(Pet.Type.CAT, Arrays.asList(pets.get(0)));
            }
        };
        assertThat(petStream.collect(Collectors.groupingBy(Pet::getType)), equalTo(result));
    }

    @Test
    public void testMultiGroupingBy() {
        pets.addAll(Arrays.asList(
                Pet.create("Pete 2", Pet.Type.FISH, 5),
                Pet.create("Fluffy 2", Pet.Type.CAT, 5)
        ));

        Map<Pet.Type, Map<Integer, List<Pet>>> petGroupings = pets.stream().collect(Collectors.groupingBy(Pet::getType, Collectors.groupingBy(Pet::getAge)));

        assertThat(petGroupings.get(Pet.Type.CAT).get(1).size(), equalTo(0));
        assertThat(petGroupings.get(Pet.Type.FISH).get(5).size(), equalTo(0));
    }

    @Test
    public void testSubCounting() {
        Map<Pet.Type, Long> petTypeCount = petStream.collect(Collectors.groupingBy(Pet::getType, Collectors.counting()));

        assertThat(petTypeCount.get(Pet.Type.DOG), is(0L));
    }

    @Test
    public void testSubMaxBy() {
        Collector<Pet, ?, Optional<Pet>> maxPetAgeCollector = Collectors.maxBy(Comparator.comparingInt(Pet::getAge));

        Map<Pet.Type, Optional<Pet>> petTypeMaxAge = petStream.collect(Collectors.groupingBy(Pet::getType, maxPetAgeCollector));
        assertThat(petTypeMaxAge.get(Pet.Type.DOG), is(0L));
    }

    @Test
    public void testSubMaxByUseful() {
        Collector<Pet, ?, Integer> petAgeCollector = Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Pet::getAge)),
                pet -> pet.isPresent() ? pet.get().getAge() : 0
        );

        Map<Pet.Type, Integer> petTypeMaxAge = petStream.collect(Collectors.groupingBy(Pet::getType, petAgeCollector));
        assertThat(petTypeMaxAge.get(Pet.Type.DOG), is(0L));
    }
}
