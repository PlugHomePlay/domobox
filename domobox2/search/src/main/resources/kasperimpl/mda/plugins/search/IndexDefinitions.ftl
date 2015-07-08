package fr.csn.suiviFormation.domain;

import kasper.domain.model.DtObject;

/**
 * Attention cette classe est générée automatiquement !
 * 
 * @author dchallas
 */
public enum IndexDefinitions {
	
<#list indexDefinitionList as indexDefinition>
	/** ${indexDefinition}. */
	${indexDefinition} (//
		${indexDefinition.indexDtDefinition.classCanonicalName}.class, //
		${indexDefinition.resultDtDefinition.classCanonicalName}.class),
	</#list>
	;

	private final Class<? extends DtObject> indexDtDefinitionClass;
	private final Class<? extends DtObject> resultDtDefinitionClass;

	private IndexDefinitions(final Class<? extends DtObject> indexDtDefinitionClass, final Class<? extends DtObject> resultDtDefinitionClass) {
		this.indexDtDefinitionClass = indexDtDefinitionClass;
		this.resultDtDefinitionClass = resultDtDefinitionClass;
	}

	/**
	 * Donne la valeur de name.
	 * 
	 * @return la valeur de name.
	 */
	public String getName() {
		return name();
	}

	/**
	 * Donne la valeur de indexDtDefinitionClass.
	 * 
	 * @return la valeur de indexDtDefinitionClass.
	 */
	public Class<? extends DtObject> getIndexDtDefinitionClass() {
		return indexDtDefinitionClass;
	}

	/**
	 * Donne la valeur de resultDtDefinitionClass.
	 * 
	 * @return la valeur de resultDtDefinitionClass.
	 */
	public Class<? extends DtObject> getResultDtDefinitionClass() {
		return resultDtDefinitionClass;
	}
	
	/**
	 * @return nom de la definition
	 */
	public String getDefinitionName() {
		// déjà préfixé par IDX
		return name(); // DefinitionUtil.getPrefixedName(name(), IndexDefinition.class);
	}

}
