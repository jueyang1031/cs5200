package edu.neu.cs5200.ds.movie.entities;

import java.util.Date;
import java.util.List;



public class Movie {
	private int id;
	private String title;
	private String posterImage;
	private Date releaseDate;
	private List<Comment> comments;
	private List<Cast> casts;

	public int getId() {
		return id;
	}
	public void setId(int i) {
		this.id = i;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPosterImage() {
		return posterImage;
	}
	public void setPosterImage(String posterImage) {
		this.posterImage = posterImage;
	}
	public Date getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Cast> getCasts() {
		return casts;
	}
	public void setCasts(List<Cast> casts) {
		this.casts = casts;
	}
}
