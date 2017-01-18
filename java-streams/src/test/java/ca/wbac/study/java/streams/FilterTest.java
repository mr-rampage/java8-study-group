package ca.wbac.study.java.streams;

import org.junit.Test;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class FilterTest {

    @Test
    public void testTeamLunchUniqueFoodList() {
        Stream<Person> teamLunchStream = Stream.of(
                Person.create("Nelson").eat("Sandwich", "Soup"),
                Person.create("Jackie").eat("Steak"),
                Person.create("BoHao").eat("Salad", "Soup"),
                Person.create("John L").eat("Hamburger")
        );

        List<String> foodList = teamLunchStream
                .map(Person::ate)
                .flatMap(List::stream)
                .distinct()
                .collect(Collectors.toList());

        assertThat(foodList.size(), is(6));
    }

    @Test
    public void testTeamLunchWhoDrinks() {
        Stream<Person> teamLunchStream = Stream.of(
                Person.create("Anthony").drink("Beer", "Beer", "Beer"),
                Person.create("Fab").drink("Beer", "Beer", "Water"),
                Person.create("Kabir").drink("Coke Zero"),
                Person.create("Wander").drink("Water"),
                Person.create("John M").drink("Beer")
        );

        Predicate<Person> hadDrink = person -> true; //person.drank("Beer"); // todo: remove me

        List<Person> whoDrank = teamLunchStream
                .filter(hadDrink)
                .collect(Collectors.toList());

        assertThat(whoDrank.size(), is(3));
    }

    @Test
    public void testTeamLunchWhoDrankOnlyBeer() {
        Stream<Person> teamLunchStream = Stream.of(
                Person.create("Anthony").drink("Beer", "Beer", "Beer"),
                Person.create("Fab").drink("Beer", "Beer", "Water"),
                Person.create("Kabir").drink("Coke Zero"),
                Person.create("Wander").drink("Water"),
                Person.create("John M").drink("Beer")
        );

        List<Person> whoDrank = teamLunchStream
                .filter(person -> person.drank("Beer"))
                .collect(Collectors.toList());

        assertThat(whoDrank.size(), is(2));
    }

    @Test
    public void testBeingGenerous() {
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
                // todo: show skip here
                .limit(1)
                .collect(Collectors.toList())
                .get(0);

        assertThat(winner.getName(), is(""));
    }
}
