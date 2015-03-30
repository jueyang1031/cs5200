package edu.neu.cs5200.ds.movie.manager;

import java.util.ArrayList;
import java.util.List;

import edu.neu.cs5200.ds.movie.dao.MovieDao;
import edu.neu.cs5200.ds.movie.entities.Movie;

public class MovieManager {
	
	private MovieDao movieDao;
	
	
	public MovieManager() {
		super();
		this.movieDao = new MovieDao();
	}

	public void createMovie(Movie newMovie) {
		movieDao.create(newMovie);
	}
	
	public List<Movie> readAllMovies(){
		List<Movie> movies = new ArrayList<Movie>();
		movies = movieDao.readAllMovies();
		return movies;
	}
	
	public Movie readMovie(int movieId){
		Movie movie = new Movie();
		movie = movieDao.findById(movieId);
		return movie;
	}
	
	public void updateMovie(int movieId, Movie movie) {
		movieDao.updateMovie(1, movie);
	}
	
	public void deleteMovie(int movieId) {
		movieDao.deleteMovie(movieId);
	}
}
