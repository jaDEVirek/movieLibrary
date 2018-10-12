package utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Movie;
import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FileDeserializer {

    private File inputFile;
    private ObjectMapper mapper;

    public FileDeserializer(File file) {
        this.inputFile = file;
        this.mapper = new ObjectMapper();
    }

    public List<Movie> getDataFromFile() throws IllegalAccessException, IOException {
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
        return mapper.readValue(inputFile, new TypeReference<List<Movie>>() {
        });
    }

    private List<Movie> deserializeDataFromXML() {
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
            String director = child.select("director").text();
            String productionDate = child.select("productionDate").text();
            String genre = child.select("genre").text();
            List<String> actor = child.select("actors").select("actor").eachText();
            movieList.add(new Movie(title, director, productionDate, actor, genre));
        }
        return movieList;
    }

}
