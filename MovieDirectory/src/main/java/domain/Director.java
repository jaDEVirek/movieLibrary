package domain;


import java.util.ArrayList;
import java.util.List;

public class Director extends Person {

    private List<Movie> directedMovies = new ArrayList();

    public Director(String fullName) {
        String[] s = fullName.split(" ");
        this.setFirstName(s[0]);
        this.setSecondName(s[1]);
    }

    public List<Movie> getDirectedMovies() {
        return directedMovies;
    }
    
    public void addMovieToDirector(Movie movie) {
        directedMovies.add(movie);
    }
}
