package edu.neu.cs5200.hw5.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.codehaus.jackson.annotate.JsonIgnore;

import java.util.List;


/**
 * The persistent class for the site database table.
 * 
 */
@Entity
@NamedQuery(name="Site.findAll", query="SELECT s FROM Site s")
public class Site implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private double latitude;

	private double longitude;

	private String name;

	//bi-directional many-to-one association to Tower
	@JsonIgnore
	@OneToMany(mappedBy="site", fetch=FetchType.LAZY)
	private List<Tower> towers;

	public Site() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tower> getTowers() {
		return this.towers;
	}

	public void setTowers(List<Tower> towers) {
		this.towers = towers;
	}

	public Tower addTower(Tower tower) {
		getTowers().add(tower);
		tower.setSite(this);

		return tower;
	}

	public Tower removeTower(Tower tower) {
		getTowers().remove(tower);
		tower.setSite(null);

		return tower;
	}

}