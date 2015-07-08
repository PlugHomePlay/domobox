package fr.infini.domobox.webservices;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.infini.domobox.exceptions.TechnicalException;
import fr.infini.domobox.security.model.Utilisateur;
import fr.infini.domobox.security.service.SecurityService;
import fr.infini.domobox.security.service.SessionService;

@Path("/sso")
public class SSORestService {

	private SecurityService securityService = SecurityService.getInstance();
	
	private SessionService sessionService = SessionService.getInstance();
	
	@GET
	@Path("{login}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response authentifier(@PathParam("login") String login, @PathParam("password") String password){
		
		try {
			Utilisateur utilisateur = securityService.authenticate(login, password);
			String jeton = securityService.creerJeton();
			sessionService.ajouterSession(utilisateur, jeton);
			return Response.ok(jeton).build();
		} catch (TechnicalException e) {
			return Response.ok(e).build();
		}
	}
	
	@POST
	public Response ajouterUtilisateur(Utilisateur utilisateur) throws TechnicalException {
		//Ajout de la box à l'application
		securityService.creerUtilisateur(utilisateur.getIdentifiant(), utilisateur.getPassword(), utilisateur.getEmail());
		
		return Response.ok(utilisateur).build();
	}
}
