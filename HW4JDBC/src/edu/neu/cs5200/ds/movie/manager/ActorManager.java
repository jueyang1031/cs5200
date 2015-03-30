package edu.neu.cs5200.ds.movie.manager;

import java.util.List;

import edu.neu.cs5200.ds.movie.dao.ActorDao;
import edu.neu.cs5200.ds.movie.entities.Actor;

public class ActorManager {

	private ActorDao actorDao;
	
	public ActorManager() {
		super();
		this.actorDao = new ActorDao();
	}
	
	public void createActor(Actor newActor) {
		actorDao.create(newActor);
	}
	
	public List<Actor> readAllActors() {
		return actorDao.readAllActors();
	}
	
	public Actor readActor(int actorId) {
		return actorDao.findById(actorId);
	}
	
	public void updateActor(int actorId, Actor actor) {
		actorDao.updateActor(actorId, actor);
	}
	
	public void deleteActor(int actorId) {
		actorDao.deleteActor(actorId);
	}

}
