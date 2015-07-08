package fr.infini.domobox.webservices;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.infini.domobox.model.entite.Capteur;
import fr.infini.domobox.model.service.CapteurServices;
import fr.infini.domobox.redis.ChannelType;
import fr.infini.domobox.redis.service.RedisService;
import fr.infini.domobox.utils.RedisHandler;

@Path("/capteur")
public class CapteurRestService {

	private final CapteurServices capteurService = CapteurServices.getInstance();
	
	private final RedisService redisService = RedisService.getInstance();
	
	@POST
	public Response ajouterCapteur(Capteur capteur){
		//Ajout de la box à l'application
		capteurService.ajouterCapteur(capteur);
		
		//Ajout d'un nouveau channel de notification
		redisService.addChannel(RedisHandler.INSTANCE.getRedisName(capteur), ChannelType.CAPTEUR);
		return Response.ok(capteur).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response listerCapteur() {
		List<Capteur> capteurs = capteurService.listerCapteur();
		GenericEntity<List<Capteur>> entity = new GenericEntity<List<Capteur>>(capteurs) {};
		return Response.ok(entity).build();
	}
	
	@GET
	@Path("{capteurId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response searchBox(@PathParam("capteurId") long id) {
		Capteur capteur = capteurService.rechercherCapteur(id);
		return Response.ok(capteur).build();
	}
	
	@DELETE
	@Path("{capteurId}")
	public Response retirerBox(@PathParam("capteurId") long id){
		Capteur capteur = capteurService.rechercherCapteur(id);
		if(capteur != null){
			capteurService.supprimerCapteur(capteur);
			redisService.removeChannel(RedisHandler.INSTANCE.getRedisName(capteur));
			
			return Response.ok().build();
		} else {
			return Response.noContent().build();
		}
	}
}
