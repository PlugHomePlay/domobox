package fr.infini.domobox.security.service.implementation;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ConcurrentHashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import fr.infini.domobox.exceptions.security.SecurityException;
import fr.infini.domobox.exceptions.security.SessionExpireException;
import fr.infini.domobox.exceptions.security.SessionInexistanteException;
import fr.infini.domobox.security.model.Utilisateur;
import fr.infini.domobox.security.service.SessionService;

public enum SessionServiceImplementation implements SessionService {
	
	INSTANCE;

	private static final int DUREEDEVIESESSION = 6000;
	
	private static final ConcurrentHashMap<String, SessionUtilisateur> SESSIONS = new ConcurrentHashMap<String, SessionUtilisateur>();
	
	private static final ConcurrentHashMap<Utilisateur, String> SESSIONUTILISATEUR = new ConcurrentHashMap<Utilisateur, String>();
	
	@Override
	public void ajouterSession(Utilisateur utilisateur, String cleSession) {
		// Rechercher si une session existe pour cet utilisateur
		if (SESSIONUTILISATEUR.containsKey(utilisateur)) {
			// On révoque les anciennes
			SESSIONS.remove(SESSIONUTILISATEUR.get(utilisateur));
			SESSIONUTILISATEUR.remove(cleSession);
		}
		// On ajoute la nouvelle session
		SESSIONS.put(cleSession, new SessionUtilisateur(new Date(), utilisateur));
		
		SESSIONUTILISATEUR.put(utilisateur, cleSession);		
	}

	@Override
	public void validerSession(String cleSession) throws SecurityException {
		if (SESSIONS.containsKey(cleSession)) {
			SessionUtilisateur session = SESSIONS.get(cleSession);
			// Vérifier que la session est toujours valide
			GregorianCalendar calendar = new GregorianCalendar();
			
			calendar.setTime(SESSIONS.get(cleSession).getDateMaj());
			Date present = new Date();
			calendar.add(Calendar.MILLISECOND, DUREEDEVIESESSION);
			if (present.after(calendar.getTime())) {
				SESSIONS.remove(cleSession);
				SESSIONUTILISATEUR.remove(session.getUtilisateur());
				throw new SessionExpireException(null);
			}
			SESSIONS.get(cleSession).setDateMaj(present);
		} else {
			throw new SessionInexistanteException(null);
		}
	}
	
	@AllArgsConstructor
	@NoArgsConstructor
	@Getter
	@Setter
	private class SessionUtilisateur {
		
		private Date dateMaj;
		
		private Utilisateur utilisateur;
	}
}
