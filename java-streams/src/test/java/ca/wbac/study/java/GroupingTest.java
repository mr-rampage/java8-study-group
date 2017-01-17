package ca.wbac.study.java;

import org.junit.Before;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class GroupingTest {

    private Stream<Pet> petStream;

    @Before
    public void setUp() {
        List<Pet> pets = Arrays.asList(
                Pet.create("Fluffy", PetType.CAT, 1),
                Pet.create("Maximus", PetType.DOG, 10),
                Pet.create("George", PetType.DOG, 15),
                Pet.create("Pete", PetType.FISH, 5));
        petStream = pets.stream();
    }
}
