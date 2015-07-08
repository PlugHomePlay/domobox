package ${dtDefinition.packageName};

import kasper.domain.metamodel.DtDefinition;
import kasper.domain.metamodel.DtField;
import kasper.domain.metamodel.annotation.Field;
import kasper.domain.model.DtObject;
import kasper.domain.util.DtObjectUtil;

/**
 * Attention cette classe est générée automatiquement !
 * Objet de données Abstract${dtDefinition.classSimpleName}
 */
@kasper.domain.metamodel.annotation.DtDefinition<#if !dtDefinition.persistent>(persistent = false)</#if>
public final class ${dtDefinition.classSimpleName} implements DtObject {
	/** SerialVersionUID. */
	private static final long serialVersionUID = 1L;
	/** Liste des champs. */
	public static class FD{

		// enum
		private FD() {
			/* non instanciable */
		} 
	
		/** 
		 * Nom de la définition.
		 */
		public static String URN = "${dtDefinition.urn}";

		<#list dtDefinition.dtComputedFields as dtField>
		/** 
		 * Code du champ "${dtField.nameLowerCase}".<br/>
		 * @see ${dtDefinition.classSimpleName}#get${dtField.nameLowerCase}()
		 */
		public static String ${dtField.name?upper_case} = "${dtField.name}";

		</#list>	
		<#list dtDefinition.dtFields as dtField>
		/** 
		 * Code du champ : ${dtField.nameLowerCase}.<br>
		 * @see ${dtDefinition.classSimpleName}#get${dtField.nameLowerCase}()
		 */
		public static String ${dtField.name?upper_case} = "${dtField.name}";

		</#list>	
		/** @return Definition de ${dtDefinition.classSimpleName}. */
		public static DtDefinition getDtDefinition(){
			return DtObjectUtil.findDtDefinition(${dtDefinition.classSimpleName}.class);
		}

		<#list dtDefinition.dtFields as dtField>
		/** @return Definition du champ ${dtField.nameLowerCase} */
		public static DtField get${dtField.nameLowerCase}DtField(){ 
			return getDtDefinition().getField(${dtField.name?upper_case});
		}
		
		</#list>	
		<#list dtDefinition.dtComputedFields as dtField>
		/** @return Definition du champ ${dtField.nameLowerCase} */
		public static DtField get${dtField.nameLowerCase}DtField(){ 
			return getDtDefinition().getField(${dtField.name?upper_case});
		}
		
		</#list>	
	}
	<#list dtDefinition.dtFields as dtField>
	private ${dtField.javaType} ${dtField.nameLowerCase?uncap_first};
	</#list>
	<#list dtDefinition.associations as association>
	<#if association.navigable>
		<#if association.multiple>
	private kasper.domain.model.DtList<${association.returnType}> ${association.role?uncap_first};
		<#else>
	private ${association.returnType} ${association.role?uncap_first};
		</#if>
	</#if>
	</#list>

	<#list dtDefinition.dtFields as dtField>
	/**
	 * Champ : ${dtField.type}.
	 * Récupère la valeur de la propriété '${dtField.display}'. 
	 * @return ${dtField.javaType} ${dtField.nameLowerCase?uncap_first} <#if dtField.notNull><b>Obligatoire</b></#if>
	 */
	<#list annotations(dtField.dtField, dtField.dtDefinition) as annotation>
	${annotation}
	</#list>
	public final ${dtField.javaType} get${dtField.nameLowerCase}() {
		return ${dtField.nameLowerCase?uncap_first};
	}

	/**
	 * Champ : ${dtField.type}.
	 * Définit la valeur de la propriété '${dtField.display}'.
	 * @param ${dtField.nameLowerCase?uncap_first} ${dtField.javaType} <#if dtField.notNull><b>Obligatoire</b></#if>
	 */
	public final void set${dtField.nameLowerCase}(final ${dtField.javaType} ${dtField.nameLowerCase?uncap_first}) {
		this.${dtField.nameLowerCase?uncap_first} = ${dtField.nameLowerCase?uncap_first};
	}

	</#list>
	<#list dtDefinition.dtComputedFields as dtField>
	/**
	 * Champ : ${dtField.type}.
	 * Récupère la valeur de la propriété calculée '${dtField.display}'. 
	 * @return ${dtField.javaType} ${dtField.nameLowerCase?uncap_first} <#if dtField.notNull><b>Obligatoire</b></#if>
	 */
	<#list annotations(dtField.dtField, dtField.dtDefinition) as annotation>
	${annotation}
	</#list>
	public final ${dtField.javaType} get${dtField.nameLowerCase}() {
		${dtField.javaCode}
	}

	</#list>
	<#if dtDefinition.associations?has_content>
	<#list dtDefinition.associations as association>
	<#if association.navigable>
	/**
	 * Association : ${association.label}.
	 * @return <#if association.multiple>kasper.domain.model.DtList<${association.returnType}><#else>${association.returnType}</#if>
	 */
	<#if association.multiple>
	public final kasper.domain.model.DtList<${association.returnType}> get${association.role?cap_first}List() {
//		return this.<${association.returnType}> getList(get${association.role?cap_first}ListURI());
		if (DtObjectUtil.getId(this) == null) return new kasper.domain.model.DtList<${association.returnType}>(${association.returnType}.class);
		final kasper.domain.metamodel.association.DtListURIForAssociation fkDtListURI = get${association.role?cap_first}DtListURI();
		kasper.kernel.util.Assertion.notNull(fkDtListURI);
		//---------------------------------------------------------------------
		//On est toujours dans un mode lazy.
		if (${association.role?uncap_first} == null) {
			${association.role?uncap_first} = kasper.kernel.Home.getContainer().getManager(kasper.persistence.PersistenceManager.class).getBroker().getList(fkDtListURI);
		}
		return ${association.role?uncap_first};
	}

	/**
	 * Association URI: ${association.label}.
	 * @return URI de l'association
	 */
	<#list annotations(association.associationNode) as annotation>
    ${annotation}
	</#list>
	public final kasper.domain.metamodel.association.DtListURIForAssociation get${association.role?cap_first}DtListURI() {
		return kasper.domain.util.DtObjectUtil.createDtListURI(this, "${association.urn}", "${association.role}");
	}
	<#else>
	public final ${association.returnType} get${association.role?cap_first}() {
		final kasper.kernel.metamodel.URI<${association.returnType}> fkURI = get${association.role?cap_first}URI();
		if (fkURI == null) {
			return null;
		}
		//On est toujours dans un mode lazy. On s'assure cependant que l'objet associé n'a pas changé
		if (${association.role?uncap_first} != null) {
			// On s'assure que l'objet correspond à la bonne clé
			final kasper.kernel.metamodel.URI<${association.returnType}> uri = new kasper.kernel.metamodel.URI<${association.returnType}>( kasper.domain.util.DtObjectUtil.findDtDefinition(${association.role?uncap_first}), kasper.domain.util.DtObjectUtil.getId(${association.role?uncap_first}));
			if (!fkURI.toURN().equals(uri.toURN())) {
				${association.role?uncap_first} = null;
			}
		}		
		
		if (${association.role?uncap_first} == null) {
			${association.role?uncap_first} = kasper.kernel.Home.getContainer().getManager(kasper.persistence.PersistenceManager.class).getBroker().get(fkURI);
		}
		return ${association.role?uncap_first};
	}

	/**
	 * Retourne l'URI: ${association.label}.
	 * @return URI de l'association
	 */
	<#list annotations(association.associationNode) as annotation>
    ${annotation}
	</#list>
	public final kasper.kernel.metamodel.URI<${association.returnType}> get${association.role?cap_first}URI() {
		return kasper.domain.util.DtObjectUtil.createURI(this, "${association.urn}", ${association.returnType}.class);
	}
	</#if>
	<#else>

	// Association : ${association.label} non navigable
	</#if>
	</#list>
	<#else>
	//Aucune Association déclarée
	</#if>

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return DtObjectUtil.toString(this);
	}
}
