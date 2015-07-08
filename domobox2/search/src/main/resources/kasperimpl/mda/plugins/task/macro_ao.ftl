<#macro generateHeader taskDefinitions>
	/************************* Liste des taches. *************************/
<#list taskDefinitions as taskDefinition>
	/** Tache ${taskDefinition.urn} */
	private static final String ${taskDefinition.urn} = "${taskDefinition.urn}";
</#list>


	/************************* Liste des attributs des taches. *************************/
<#list taskDefinitions as taskDefinition>
	<#list taskDefinition.attributes as taskAttribute>
	/** Constante de param�tre de la tache ${taskAttribute.name}. */
	private static final String ${taskAttribute.constantName} = "${taskAttribute.name}";

	</#list>
</#list>
	private final WorkManager workManager;
</#macro> 
   
<#macro generateBody taskDefinitions>

	/**
	 * Cr�ation d'une tache.
	 * @param task Type de la tache
	 * @return Builder de la tache
	 */
	private TaskBuilder createTaskBuilder(final String task) {
		final TaskDefinition taskDefinition = Home.getNameSpace().resolve(task, TaskDefinition.class);
		return new TaskBuilder(taskDefinition);
	}

<#list taskDefinitions as taskDefinition>
	/**
	 * Execute la tache ${taskDefinition.urn}.
	<#list taskDefinition.inAttributes as taskAttribute>
	 * @param ${taskAttribute.variableName} ${taskAttribute.dataType} <#if !taskAttribute.notNull>(peut �tre null)</#if>
	</#list>
     <#if taskDefinition.out>
	 * @return <#if !taskDefinition.outAttribute.notNull>Option de </#if>${taskDefinition.outAttribute.dataType} ${taskDefinition.outAttribute.variableName}
	</#if>
	*/
	public <#if taskDefinition.out><#if !taskDefinition.outAttribute.notNull>Option<</#if>${taskDefinition.outAttribute.dataType}<#if !taskDefinition.outAttribute.notNull>></#if><#else>void</#if> ${taskDefinition.methodName}(<#list taskDefinition.inAttributes as taskAttribute>final <#if !taskAttribute.notNull>Option<</#if>${taskAttribute.dataType}<#if !taskAttribute.notNull>></#if> ${taskAttribute.variableName}<#if taskAttribute_has_next>, </#if></#list>) {
		final Task task = createTaskBuilder(${taskDefinition.urn})//
	<#list taskDefinition.inAttributes as taskAttribute>
				.setValue(${taskAttribute.constantName}, ${taskAttribute.variableName}<#if !taskAttribute.notNull>.getOrElse(null)</#if>)//
    </#list>
				.build();
    <#if taskDefinition.out>
		final TaskResult taskResult = workManager.process(task);
		return <#if !taskDefinition.outAttribute.notNull>Option.option(</#if>taskResult.<${taskDefinition.outAttribute.dataType}> getValue(${taskDefinition.outAttribute.constantName})<#if !taskDefinition.outAttribute.notNull>)</#if>;
    <#else>
		workManager.process(task);
    </#if>
	}

</#list>
</#macro>
