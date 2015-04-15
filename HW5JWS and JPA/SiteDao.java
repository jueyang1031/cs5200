package edu.neu.cs5200.hw5.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import edu.neu.cs5200.hw5.entity.Site;

@Path("/site")
public class SiteDao {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("HW5");
	EntityManager em = factory.createEntityManager();
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Site findSite(@PathParam("id") int siteId) {
		return em.find(Site.class, siteId);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> findAllSites() {
		Query query = em.createQuery("select s from Site s");
		return query.getResultList();
	}
	
	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> updateSite(@PathParam("id") int siteId, Site site) {
		em.getTransaction().begin();
		if(em.contains(site)) {
			em.merge(site);
		}
		em.getTransaction().commit();
		return (List<Site>) findAllSites();
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> removeSite(@PathParam("id") int siteId) {
		em.getTransaction().begin();
		Site site = em.find(Site.class, siteId);
		if (site != null) {
			em.remove(site);
		}
		em.getTransaction().commit();
		return findAllSites();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<Site> createSite(Site site) {
		em.getTransaction().begin();
		em.persist(site);
		em.getTransaction().commit();
		return findAllSites();
	}
	
	public static void main(String[] args) {
		SiteDao siteDao = new SiteDao();
		Site newSite = new Site();
		newSite.setLatitude(22.3);
		newSite.setLongitude(10.9);
		newSite.setName("site3");
		//siteDao.createSite(newSite);
		
		System.out.println("findAll");
		List<Site> sites = siteDao.findAllSites();
		for(Site site : sites) {
			System.out.println(site.getName());
		}
		
		System.out.println("find2");
		System.out.println(siteDao.findSite(2).getName());
		
		System.out.println("update3");
		newSite.setName("site3 update");
		sites = siteDao.updateSite(3, newSite);
		for(Site site : sites) {
			System.out.println(site.getName());
		}
		
		System.out.println("remove3");
		sites = siteDao.removeSite(4);
		for(Site site : sites) {
			System.out.println(site.getName());
		}
	}
	
}
