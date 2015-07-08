<#ftl ns_prefixes={"d":"http://java.sun.com/xml/ns/javaee"}>
<#macro toUnderScore camelCase>
${camelCase?uncap_first?replace("[A-Z]", "_$0", 'r')?upper_case}</#macro>
<@pp.dropOutputFile />
<@pp.changeOutputFile name="/fr/csn/suiviFormation/jsfui/util/NavigationConst.java" />
package fr.csn.suiviFormation.jsfui.util;

/**
 * Class générée à partir des déclarations faces-config.xml.
 * @author dchallas
 */
public class NavigationConst {

	private NavigationConst(){
		// constantes.
	}
	
	<#assign ncList = pp.doc["d:faces-config"]["d:navigation-rule[d:from-view-id/text() = '*' ]"]["d:navigation-case"]>
	<#list ncList as nc>
		<#assign fo = nc["d:from-outcome"]>
		<#assign tvi =nc["d:to-view-id"]>
		<#assign r = nc["d:redirect"]?? >
	/** 
	 * from-outcome : ${fo?string} .<br> 
	 * to-view-id:${tvi?string} .<br>
	 * redirect = ${r?string}.
	 */
	public static final String <#if fo?contains("_")>${fo}<#else><@toUnderScore fo /></#if> = "${fo?string}";
<#--
	// private static final int <@toUnderScore tvi?replace("/pages/","")?replace(".xhtml","")?replace("/"," ")?capitalize?replace(" ","") /> = 0;
	// private static final int ${tvi?replace("/pages/","")?replace(".xhtml","")?split('/')?last} = 0 ;
-->
	
	</#list>
}
