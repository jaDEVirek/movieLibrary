package movie_library_test;

import domain.Actor;
import domain.Director;
import domain.Movie;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.FileDeserializer;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestCases {
    private File xmlFile;
    private File jsonFile;

    @BeforeClass
    public void initFakedData() {
        xmlFile = new File("src/main/java/source/movies.xml");
        jsonFile = new File("src/main/java/source/movies.json");
    }

    @Test
    public void shouldRecognizeFileExtension() {
        //Given
        FileDeserializer xmlFileDeserialize = new FileDeserializer(xmlFile);
        FileDeserializer jsonFileDeserialize = new FileDeserializer(jsonFile);

        //When
        String actualXMLExtension = xmlFileDeserialize.getExtensionForTest();
        String actualJSONExtension = jsonFileDeserialize.getExtensionForTest();

        //Then
        Assert.assertEquals("xml", actualXMLExtension);
        Assert.assertEquals("json", actualJSONExtension);
        Assert.assertNotEquals("xml", actualJSONExtension);
    }

    @Test
    public void shouldReturnExpectedMovie() throws ParseException, IOException, IllegalAccessException {
        //Given
        FileDeserializer xmlFileDeserialize = new FileDeserializer(xmlFile);
        Date productionDate = new SimpleDateFormat("MM-dd-yyyy", Locale.US).parse("14-02-2013");
        Actor first = new Actor("Bruce Willis");
        Actor second = new Actor("Jai Courtney");
        List<Actor> actorList = Arrays.asList(first, second);
        Movie expectedMovie = new Movie("Szklana Pulapka 5", new Director("John Moore"), productionDate, actorList,"Action" );
        
        //When
        List<Movie> dataFromFile = xmlFileDeserialize.getDataFromFile();
        Movie actualMovie = dataFromFile.get(1);

        //Then
        Assert.assertEquals(actualMovie,expectedMovie );
        Assert.assertEquals("Action", actualMovie.getGenre());
        Assert.assertEquals(first.getFullName(), actualMovie.getActors().get(0).getFullName());
    }
}


