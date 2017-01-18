package ca.wbac.study.java.streams;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.ToIntBiFunction;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ReduceTest {

    private final Map<String, Integer> menu = Collections.unmodifiableMap(new HashMap<String, Integer>() {
        {
            put("Sandwich", 5);
            put("Steak", 15);
            put("Salad", 4);
            put("Soup", 3);
            put("Hamburger", 7);

            put("Beer", 7);
            put("Water", 0);
            put("Soda", 2);
        }
    });

    @Test
    public void testReduceBill() {
        Stream<String> orderStream = Stream.of("Steak", "Soup", "Salad");

        Integer initialValue = 0;
        BinaryOperator<Integer> sumItems = (accumulator, element) -> accumulator + element;

        Integer total = orderStream.map(menu::get).reduce(initialValue, sumItems);
        assertThat(total, is(22));
    }

    private final Stream<Person> teamLunchStream = Stream.of(
            Person.create("Nelson").eat("Sandwich", "Soup").drink("Beer", "Water"),
            Person.create("Jackie").eat("Steak").drink("Soda"),
            Person.create("BoHao").eat("Salad", "Soup").drink("Beer", "Beer"),
            Person.create("John L").eat("Hamburger").drink("Soda")
    );


    @Test
    public void testTeamLunchFoodOnly() {
        Integer foodBill = 0;

        assertThat(foodBill, is(37));
    }

    @Test
    public void testTeamLunchDrinksOnly() {
        Integer drinkBill = 0;

        assertThat(drinkBill, is(25));
    }

    @Test
    public void testTeamLunchFoodAndDrink() {
        Integer bill = 0;

        assertThat(bill, is(37));
    }
}
