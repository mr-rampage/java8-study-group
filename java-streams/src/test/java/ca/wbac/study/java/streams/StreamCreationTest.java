package ca.wbac.study.java.streams;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.assertTrue;

public class StreamCreationTest {

    @Test
    public void testArraysToStream() {
        Person[] sharedServiceArray = {
                Person.create("Kabir"),
                Person.create("Wander"),
                Person.create("Jean"),
                Person.create("Anton"),
                Person.create("John M."),
                Person.create("Matthew")
        };

        Stream<Person> sharedServiceStream = null;
        assertTrue(sharedServiceStream instanceof Stream);
    }

    @Test
    public void testListToStream() {
        List<Person> sharedServiceList = Arrays.asList(
                Person.create("Kabir"),
                Person.create("Wander"),
                Person.create("Jean"),
                Person.create("Anton"),
                Person.create("John M."),
                Person.create("Matthew")
        );

        Stream<Person> sharedServiceStream = null;
        assertTrue(sharedServiceStream instanceof Stream);
    }

    @Test
    public void testDirectCreation() {
        Stream<Person> sharedServiceStream = Stream.of(
                Person.create("Kabir"),
                Person.create("Wander"),
                Person.create("Jean"),
                Person.create("Anton"),
                Person.create("John M."),
                Person.create("Matthew")
        );
        assertTrue(sharedServiceStream instanceof Stream);
    }

    @Test
    public void testFileToStream() {
        URL sharedServiceNamesFileUrl = ClassLoader.getSystemResource("shared-services-names.txt");
        try (Stream<String> sharedServiceStream = Files.lines(Paths.get(sharedServiceNamesFileUrl.toURI()))) {
            assertTrue(sharedServiceStream instanceof Stream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testResourceToStream() {
        InputStream sharedServiceNamesFileStream = ClassLoader.getSystemResourceAsStream("shared-services-names.txt");
        try (Stream<String> sharedServiceStream = new BufferedReader(new InputStreamReader(sharedServiceNamesFileStream)).lines()) {
            assertTrue(sharedServiceStream instanceof Stream);
        }
    }

}
