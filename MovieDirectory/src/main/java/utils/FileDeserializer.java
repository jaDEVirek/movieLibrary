package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Actor;
import domain.Director;
import domain.Movie;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class FileDeserializer {

    private File inputFile;
    private ObjectMapper mapper;

    public FileDeserializer(File file) {
        this.inputFile = file;
        this.mapper = new ObjectMapper();
    }

    public List<Movie> getDataFromFile() throws IllegalAccessException, IOException, ParseException {
        switch (this.getFileExtension()) {
            case "json":
                return this.deserializeDataFromJson();
            case "xml":
                return this.deserializeDataFromXML();
            default:
                throw new IllegalAccessException("This type of file is unsupported ! ");
        }

    }

    private String getFileExtension() {
        String fileName = this.inputFile.getName();
        return Optional.of(fileName)
                .filter(fn -> fn.contains("."))
                .map(fn -> fn.substring(fileName.lastIndexOf(".") + 1))
                .get();
    }


    private List<Movie> deserializeDataFromJson() throws IOException {
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return mapper.readValue(inputFile, new TypeReference<List<Movie>>() {
        });
    }

    private List<Movie> deserializeDataFromXML() throws ParseException {
        Document document = null;
        List<Movie> movieList = new ArrayList<>();
        try {
            document = Jsoup.parse(inputFile, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        Element root = document.select("movieList").first();
        Elements children = root.children();
        for (Element child : children) {
            String title = child.select("title").text();
            Director director = new Director(child.select("director").text());  
            String productionDate = child.select("productionDate").text();
            String genre = child.select("genre").text();
            List<String> actors = child.select("actors").select("actor").eachText();
            Movie movie = new Movie(title,
                    director,
                    new SimpleDateFormat("MM-dd-yyyy", Locale.US).parse(productionDate),
                    getActorsAsObjects(actors),
                    genre);
            movieList.add(movie);
            director.addMovieToDirector(movie);
        }
        return movieList;
    }

    private List<Actor> getActorsAsObjects(List<String> strings) {
        List<Actor> actors = new ArrayList<>();
        for (String str : strings) {
            Actor newActor = new Actor(str);
            actors.add(newActor);
        }
        return actors;
    }

}
