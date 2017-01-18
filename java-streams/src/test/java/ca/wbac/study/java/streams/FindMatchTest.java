package ca.wbac.study.java.streams;

import org.junit.Test;

import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.junit.MatcherAssert.assertThat;


public class FindMatchTest {

    @Test
    public void testMatching() {
        Stream<String> food = Stream.of("Sandwich", "Sandwich", "Burger");

        assertThat(food.anyMatch(item -> item.equals("Burger")), is(null));
        assertThat(food.allMatch(item -> item.equals("Burger")), is(null));
        assertThat(food.noneMatch(item -> item.equals("Burger")), is(null));
    }

    @Test
    public void testBetterBeingGenerous() {

        Stream<Person> teamLunchStream = Stream.of(
                Person.create("Kabir").drink("Coke Zero"),
                Person.create("Anthony").drink("Beer", "Beer", "Beer"),
                Person.create("Nelson").drink("Beer"),
                Person.create("Fab").drink("Beer", "Beer", "Water"),
                Person.create("Wander").drink("Water"),
                Person.create("John M").drink("Beer")
        );

        Person winner = teamLunchStream
                .filter(person -> person.drank("Beer"))
                .findAny()
                .get(); //todo: remove me

        assertThat(winner.getName(), is(""));
    }
}
