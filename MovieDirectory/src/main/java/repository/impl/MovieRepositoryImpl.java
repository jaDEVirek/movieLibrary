package repository.impl;

import domain.Actor;
import domain.Movie;
import repository.MovieRepository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 */
public class MovieRepositoryImpl implements MovieRepository {

    private List<Movie> movieList;


    public MovieRepositoryImpl(List<Movie> movieList) {
        this.movieList = movieList;
    }


    @Override
    public List<Movie> getMovieList() {
        return this.movieList;
    }

    @Override
    public List<Movie> findMoviesByActorName(String actorName) {
        List<Movie> movieResult = new ArrayList<>();
        for (Movie mv : movieList) {
            List<Actor> actors = mv.getActors();
            if (actors.stream()
                    .anyMatch(t -> t.getFullName().contains(actorName))) {
                movieResult.add(mv);
            }
        }
        return movieResult;
    }

    @Override
    public List<Movie> sortMovieByGenre() {
        List<Movie> collectedList = movieList.stream()
                .sorted(Comparator.comparing(Movie::getGenre))
                .collect(Collectors.toList());
        return collectedList;
    }

    @Override
    public List<Movie> sortMovieByProductionDate() {
        List<Movie> collectedList = movieList.stream()
                .sorted(Comparator.comparing(Movie::getProductionDate))
                .collect(Collectors.toList());
        return collectedList;
    }

    @Override
    public List<Movie> sortMovieByGenreAndDate() {
        List<Movie> collectedList = movieList.stream()
                .sorted(Comparator.comparing(Movie::getProductionDate).thenComparing(Movie::getGenre))
                .collect(Collectors.toList());
        return collectedList;
    }

    @Override
    public Optional<Movie> getMovieByTittle(String tittle) {
        return this.movieList.stream()
                .filter(t -> t.getTitle().equals(tittle))
                .findAny();
    }


}
