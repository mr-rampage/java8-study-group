package ca.wbac.study.java.streams;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class MapTest {

    @Test
    public void testPersonListFromResource() {
        InputStream sharedServiceNamesFileStream = ClassLoader.getSystemResourceAsStream("shared-services-names.txt");
        try (Stream<String> sharedServiceStream = new BufferedReader(new InputStreamReader(sharedServiceNamesFileStream)).lines()) {

            List<Person> sharedServices = sharedServiceStream
                    .map(Person::create)
                    .collect(Collectors.toList());

            assertThat(sharedServices.get(0).getName(), is("Kabir"));
        }
    }

    @Test
    public void testAnotherTeamLunchUsingMap() {
        List<Person> teamList = Arrays.asList(
                Person.create("Tomaz"),
                Person.create("Justin"),
                Person.create("Serge")
        );

        Stream<Person> teamLunchStream = teamList.stream();
        String[] foodOrder = {"Soup", "Sandwich"};

        List<Person> teamAfterLunchMap = teamLunchStream
                .map(person -> person.eat(foodOrder))
                .collect(Collectors.toList());

        teamAfterLunchMap.forEach(person -> assertThat(person.ate().size(), is(2)));
    }

    @Test
    public void testAnotherTeamLunchUsingForEach() {
        List<Person> teamList = Arrays.asList(
                Person.create("Tomaz"),
                Person.create("Justin"),
                Person.create("Serge")
        );

        Stream<Person> teamLunchStream = teamList.stream();
        String[] foodOrder = {"Soup", "Sandwich"};

        teamLunchStream.forEach(person -> person.eat(foodOrder));
        teamList.forEach(person -> assertThat(person.ate().size(), is(2)));
    }

    @Test
    public void testAnotherTeamLunchComparison() {
        List<Person> teamList = Arrays.asList(
                Person.create("Tomaz"),
                Person.create("Justin"),
                Person.create("Serge")
        );

        Stream<Person> teamLunchStream = teamList.stream();
        String[] foodOrder = {"Soup", "Sandwich"};

        List<Person> teamAfterLunchMap = teamLunchStream
                .map(person -> person.eat(foodOrder))
                .collect(Collectors.toList());

        teamAfterLunchMap.forEach(person -> assertThat(person.ate().size(), is(2)));
        teamList.forEach(person -> assertThat(person.ate().size(), is(2)));
        assertThat(teamAfterLunchMap == teamList, is(true));
    }

    @Test
    public void testTeamLunch() {
        Stream<Person> teamLunchStream = Stream.of(
                Person.create("Nelson").eat("Sandwich", "Soup"),
                Person.create("Jackie").eat("Steak"),
                Person.create("BoHao").eat("Salad", "Soup"),
                Person.create("John L").eat("Hamburger")
        );

        List<String> foodList = teamLunchStream
                .map(Person::ate)
                // .flatMap(List::stream)
                .collect(Collectors.toList());

        assertThat(foodList.size(), is(6));
    }

}
