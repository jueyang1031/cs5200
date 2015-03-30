package edu.neu.cs5200.ds.movie.manager;

import java.util.List;

import edu.neu.cs5200.ds.movie.dao.CastDao;
import edu.neu.cs5200.ds.movie.entities.Cast;

public class CastManager {

	private CastDao castDao;
	
	public CastManager() {
		super();
		this.castDao = new CastDao();
	}
	
	public void createCast(Cast newCast) {
		castDao.createCast(newCast);
	}
	
	public List<Cast> readAllCast() {
		return castDao.readAllCasts();
	}
	
	public List<Cast> readAllCastForActor(int actorId) {
		return castDao.readAllCastsForActor(actorId);
	}
	
	public List<Cast> readAllCastForMovie(int movieId) {
		return castDao.readAllCastsForMovie(movieId);
	}
	
	public Cast readCastForId(int castId) {
		return castDao.readCastForId(castId);
	}
	
	public void updateCast(int castId, Cast newCast) {
		castDao.updateCast(castId, newCast);
	}
	
	public void deleteCast(int castId) {
		castDao.deleteCast(castId);
	}
}
