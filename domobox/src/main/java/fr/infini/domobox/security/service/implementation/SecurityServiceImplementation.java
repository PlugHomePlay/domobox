package fr.infini.domobox.security.service.implementation;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

import fr.infini.domobox.exceptions.TechnicalException;
import fr.infini.domobox.exceptions.security.AuthentificationFailureException;
import fr.infini.domobox.exceptions.security.CreateCredentialException;
import fr.infini.domobox.exceptions.security.SecurityInconsistantException;
import fr.infini.domobox.security.dao.UtilisateurDao;
import fr.infini.domobox.security.model.Utilisateur;
import fr.infini.domobox.security.service.SecurityService;

public enum SecurityServiceImplementation implements SecurityService {

	INSTANCE;

	private final static int ITERATION_NUMBER = 1000;

	private UtilisateurDao dao = UtilisateurDao.getInstance();
	
	@Override
	public Utilisateur authenticate(String login, String password)
			throws TechnicalException {

		boolean authenticated = false;
		Utilisateur user = null;

		try {
			boolean userExist = true;

			if (login == null || password == null) {
				userExist = false;
				login = "";
				password = "";
			}

			user = dao.findByIdentifiant(login);

			if (user == null) {
				user = new Utilisateur();
				user.setPassword("000000000000000000000000000=");
				user.setSalt("00000000000=");
				userExist = false;
			}

			byte[] bDigest = base64ToByte(user.getPassword());
			byte[] bSalt = base64ToByte(user.getSalt());

			byte[] proposedDigest = getHash(ITERATION_NUMBER, password, bSalt);

			authenticated = Arrays.equals(proposedDigest, bDigest) && userExist;

			if (!authenticated) {
				throw new AuthentificationFailureException(null);
			}

		} catch (IOException | NoSuchAlgorithmException exception) {
			throw new SecurityInconsistantException(exception);
		}

		return user;
	}

	@Override
	public boolean creerUtilisateur(String login, String password, String email)
			throws TechnicalException {
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setIdentifiant(login);
		utilisateur.setEmail(email);
		
		try {
			if (login != null && !login.isEmpty()  && password != null && !password.isEmpty()) {
				
				SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
				
				byte[] bSalt = new byte[8];
				random.nextBytes(bSalt);
				
				byte[] bDigest = getHash(ITERATION_NUMBER, password, bSalt);
				
				utilisateur.setPassword(byteToBase64(bDigest));
				utilisateur.setSalt(byteToBase64(bSalt));
				
				dao.save(utilisateur);
				
				return true;
			}
			
			return false;
		} catch (Exception exception) {
			throw new CreateCredentialException(exception);
		}
	}

	@Override
	public String creerJeton() throws TechnicalException {
		try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			
			byte[] bJeton = new byte[16];
			random.nextBytes(bJeton);
			
			return byteToBase64(bJeton);
		} catch (NoSuchAlgorithmException exception) {
			throw new CreateCredentialException(exception);
		}
	}

	private byte[] base64ToByte(String data) throws IOException {
		Base64 base64 = new Base64();
		return base64.decode(data);

	}

	private String byteToBase64(byte[] data) {
		Base64 base64 = new Base64();
		base64.encode(data);
		return new String(base64.encode(data)).replaceAll("[\r\n]+", "");
	}

	private byte[] getHash(int iterationNb, String password, byte[] salt)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {

		MessageDigest digest = MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(salt);
		byte[] input = digest.digest(password.getBytes("UTF-8"));
		for (int i = 0; i < iterationNb; i++) {
			digest.reset();
			input = digest.digest(input);
		}
		return input;
	}
}
