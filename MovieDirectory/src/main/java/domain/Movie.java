package domain;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Simple Pojo class witch represent movie object
 */
public class Movie implements Serializable {
    private String title;
    private String director;
    private String productionDate;
    private List<String> actors;
    private String genre;


    public Movie() {
    }


    public Movie(String title, String director, String productionDate, List<String> actors, String genre) {
        this.title = title;
        this.director = director;
        this.productionDate = productionDate;
        this.actors = actors;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(String productionDate) {
        this.productionDate = productionDate;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(getTitle(), movie.getTitle()) &&
                Objects.equals(getDirector(), movie.getDirector()) &&
                Objects.equals(getProductionDate(), movie.getProductionDate()) &&
                Objects.equals(getActors(), movie.getActors()) &&
                Objects.equals(getGenre(), movie.getGenre());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDirector(), getProductionDate(), getActors(), getGenre());
    }

    @Override
    public String toString() {
        return "Movie: [" +
                "title: '" + title + '\'' +
                "| director: '" + director + '\'' +
                "| productionDate: " + productionDate +
                "| actors: " + actors +
                " genre:'" + genre + '\'' +
                ']';
    }
}
