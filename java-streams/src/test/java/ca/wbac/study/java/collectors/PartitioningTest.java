package ca.wbac.study.java.collectors;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class PartitioningTest {

    private List<Pet> pets;
    private Stream<Pet> petStream;
    private Predicate<Pet> isDog = pet -> pet.getType().equals(Pet.Type.DOG);

    @Before
    public void setUp() {
        pets = new ArrayList<>(Arrays.asList(
                Pet.create("Fluffy", Pet.Type.CAT, 1),
                Pet.create("Maximus", Pet.Type.DOG, 10),
                Pet.create("George", Pet.Type.DOG, 15),
                Pet.create("Pete", Pet.Type.FISH, 5)));
        petStream = pets.stream();

        Predicate<Pet> isDog = pet -> pet.getType().equals(Pet.Type.DOG);
    }

    @Test
    public void testPartitioning() {
        Map<Boolean, List<Pet>> partitionedPets = petStream.collect(Collectors.partitioningBy(isDog));
        assertThat(partitionedPets.get(true).size(), is(0));
        assertThat(partitionedPets.get(false).size(), is(0));
    }

    @Test
    public void testPartitioningByAge() {
        Collector<Pet, ?, Pet> petMaxAgeCollector = Collectors.collectingAndThen(
                Collectors.maxBy(Comparator.comparingInt(Pet::getAge)),
                Optional::get
        );
        Map<Boolean, Pet> partitionedOldestPet = petStream.collect(Collectors.partitioningBy(isDog, petMaxAgeCollector));

        Pet oldestDog = partitionedOldestPet.get(true);
        Pet oldestNotDog = partitionedOldestPet.get(false);

        assertThat(oldestDog.getAge(), is(0));
        assertThat(oldestNotDog.getName(), is(""));
    }
}
