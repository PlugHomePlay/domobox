package fr.csn.suiviFormation.jsfui.util;

/**
 * Class générée à partir des déclarations faces-config.xml.
 * @author dchallas
 */
public class NavigationConst {

	private NavigationConst(){
		// constantes.
	}
	
	/** 
	 * from-outcome : error .<br> 
	 * to-view-id:/pages/applicationError.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ERROR = "error";
	
	/** 
	 * from-outcome : security .<br> 
	 * to-view-id:/pages/applicationSecurityError.xhtml .<br>
	 * redirect = true.
	 */
	public static final String SECURITY = "security";
	
	/** 
	 * from-outcome : accesrecette .<br> 
	 * to-view-id:/pages/applicationAccesRecetteSecurityError.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ACCESRECETTE = "accesrecette";
	
	/** 
	 * from-outcome : changeclereal .<br> 
	 * to-view-id:/pages/applicationChangeCleRealSecurityError.xhtml .<br>
	 * redirect = true.
	 */
	public static final String CHANGECLEREAL = "changeclereal";
	
	/** 
	 * from-outcome : noclereal .<br> 
	 * to-view-id:/pages/applicationNoCleRealSecurityError.xhtml .<br>
	 * redirect = true.
	 */
	public static final String NOCLEREAL = "noclereal";
	
	/** 
	 * from-outcome : unauthorizedclereal .<br> 
	 * to-view-id:/pages/applicationUnauthorizedCleSecurityError.xhtml .<br>
	 * redirect = true.
	 */
	public static final String UNAUTHORIZEDCLEREAL = "unauthorizedclereal";
	
	/** 
	 * from-outcome : viewExpired .<br> 
	 * to-view-id:/pages/viewExpired.xhtml .<br>
	 * redirect = true.
	 */
	public static final String VIEW_EXPIRED = "viewExpired";
	
	/** 
	 * from-outcome : success .<br> 
	 * to-view-id:/pages/accueil/accueil.xhtml .<br>
	 * redirect = true.
	 */
	public static final String SUCCESS = "success";
	
	/** 
	 * from-outcome : redirectAccueil .<br> 
	 * to-view-id:/pages/accueil/accueil.xhtml .<br>
	 * redirect = true.
	 */
	public static final String REDIRECT_ACCUEIL = "redirectAccueil";
	
	/** 
	 * from-outcome : redirectCguNotAccepted .<br> 
	 * to-view-id:/pages/accueil/cgu.xhtml .<br>
	 * redirect = true.
	 */
	public static final String REDIRECT_CGU_NOT_ACCEPTED = "redirectCguNotAccepted";
	
	/** 
	 * from-outcome : ACC_UTI_01 .<br> 
	 * to-view-id:/pages/accueil/accueilNotaire.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ACC_UTI_01 = "ACC_UTI_01";
	
	/** 
	 * from-outcome : ACC_UTI_02 .<br> 
	 * to-view-id:/pages/accueil/accueilInstance.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ACC_UTI_02 = "ACC_UTI_02";
	
	/** 
	 * from-outcome : ACC_UTI_03 .<br> 
	 * to-view-id:/pages/accueil/accueilCsn.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ACC_UTI_03 = "ACC_UTI_03";
	
	/** 
	 * from-outcome : PLAN_UTI_01 .<br> 
	 * to-view-id:/pages/formation/planNotaire.xhtml .<br>
	 * redirect = true.
	 */
	public static final String PLAN_UTI_01 = "PLAN_UTI_01";
	
	/** 
	 * from-outcome : PLAN_UTI_02 .<br> 
	 * to-view-id:/pages/formation/planInstance.xhtml .<br>
	 * redirect = true.
	 */
	public static final String PLAN_UTI_02 = "PLAN_UTI_02";
	
	/** 
	 * from-outcome : PLAN_UTI_05 .<br> 
	 * to-view-id:/pages/formation/ficheFormationNotaireCreate.xhtml .<br>
	 * redirect = true.
	 */
	public static final String PLAN_UTI_05 = "PLAN_UTI_05";
	
	/** 
	 * from-outcome : ADM_UTI_01 .<br> 
	 * to-view-id:/pages/administration/utilisateursList.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ADM_UTI_01 = "ADM_UTI_01";
	
	/** 
	 * from-outcome : ADM_TDR_TAB .<br> 
	 * to-view-id:/pages/administration/referenceList.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ADM_TDR_TAB = "ADM_TDR_TAB";
	
	/** 
	 * from-outcome : ADM_TDR_04 .<br> 
	 * to-view-id:/pages/administration/organismeFormationList.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ADM_TDR_04 = "ADM_TDR_04";
	
	/** 
	 * from-outcome : ADM_TDR_05 .<br> 
	 * to-view-id:/pages/administration/intituleFormationList.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ADM_TDR_05 = "ADM_TDR_05";
	
	/** 
	 * from-outcome : ADM_TDR_07 .<br> 
	 * to-view-id:/pages/administration/equivalenceList.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ADM_TDR_07 = "ADM_TDR_07";
	
	/** 
	 * from-outcome : ADM_TDR_08 .<br> 
	 * to-view-id:/pages/administration/droitsAccesCollaborateursOffice.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ADM_TDR_08 = "ADM_TDR_08";
	
	/** 
	 * from-outcome : ADM_TDR_09 .<br> 
	 * to-view-id:/pages/administration/droitsAccesCollaborateursInstance.xhtml .<br>
	 * redirect = true.
	 */
	public static final String ADM_TDR_09 = "ADM_TDR_09";
	
	/** 
	 * from-outcome : STAT_UTI_TAB .<br> 
	 * to-view-id:/pages/statistiques/statHome.xhtml .<br>
	 * redirect = true.
	 */
	public static final String STAT_UTI_TAB = "STAT_UTI_TAB";
	
	/** 
	 * from-outcome : STAT_UTI_01 .<br> 
	 * to-view-id:/pages/statistiques/statSituationNotaire.xhtml .<br>
	 * redirect = true.
	 */
	public static final String STAT_UTI_01 = "STAT_UTI_01";
	
	/** 
	 * from-outcome : STAT_UTI_02 .<br> 
	 * to-view-id:/pages/statistiques/statRubriqueFormation.xhtml .<br>
	 * redirect = true.
	 */
	public static final String STAT_UTI_02 = "STAT_UTI_02";
	
	/** 
	 * from-outcome : STAT_UTI_03 .<br> 
	 * to-view-id:/pages/statistiques/statTypeFormation.xhtml .<br>
	 * redirect = true.
	 */
	public static final String STAT_UTI_03 = "STAT_UTI_03";
	
	/** 
	 * from-outcome : STAT_UTI_04 .<br> 
	 * to-view-id:/pages/statistiques/statOrganismeFormation.xhtml .<br>
	 * redirect = true.
	 */
	public static final String STAT_UTI_04 = "STAT_UTI_04";
	
	/** 
	 * from-outcome : STAT_UTI_05 .<br> 
	 * to-view-id:/pages/statistiques/statConsolidation.xhtml .<br>
	 * redirect = true.
	 */
	public static final String STAT_UTI_05 = "STAT_UTI_05";
	
}
