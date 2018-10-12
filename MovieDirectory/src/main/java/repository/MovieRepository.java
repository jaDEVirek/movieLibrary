package repository;

import domain.Movie;

import java.util.List;
import java.util.Optional;

/**
 * 
 */
public interface MovieRepository {

    List<Movie> getMovieList();

    List<Movie> findMoviesByActorName(String actorName);

    List<Movie> sortMovieByGenre();

    List<Movie> sortMovieByProductionDate();

    List<Movie> sortMovieByGenreAndDate();

    Optional<Movie> getMovieByTittle(String tittle);

}
