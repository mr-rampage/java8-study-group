package ca.wbac.study.java.streams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Person {
    private String name;
    private final List<String> consumed = new ArrayList<>();
    private final List<String> drank = new ArrayList<>();
    private Person() {}

    static Person create(String name) {
        Person person = new Person();
        person.setName(name);
        return person;
    }

    String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    Person eat(String... meal) {
        this.consumed.addAll(Arrays.asList(meal));
        return this;
    }

    List<String> ate() {
        return Collections.unmodifiableList(this.consumed);
    }

    Person drink(String... beverages) {
        this.drank.addAll(Arrays.asList(beverages));
        return this;
    }

    List<String> drank() {
        return Collections.unmodifiableList(this.drank);
    }

    Person sleep() {
        this.consumed.clear();
        this.drank.clear();
        return this;
    }
}
