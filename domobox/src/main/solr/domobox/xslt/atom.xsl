<?xml version='1.0' encoding='UTF-8'?>

<xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>

	<xsl:output method="xml" encoding="utf-8" media-type="application/xml"/>

	<xsl:template match='/'>
		<xsl:variable name="query" select="response/lst[@name='responseHeader']/lst[@name='params']/str[@name='q']"/>
		<feed xmlns="http://www.w3.org/2005/Atom">
			<title>Example Solr Atom 1.0 Feed</title>
			<subtitle>
			This has been formatted by the sample "example_atom.xsl" transform -
			use your own XSLT to get a nicer Atom feed.
			</subtitle>
			<author>
				<name>Apache Solr</name>
				<email>solr-user@lucene.apache.org</email>
			</author>
			<link rel="self" type="application/atom+xml" href="http://localhost:8983/solr/q={$query}&amp;wt=xslt&amp;tr=atom.xsl"/>
			<updated>
				<xsl:value-of select="response/result/doc[position()=1]/date[@name='timestamp']"/>
			</updated>
			<id>tag:localhost,2007:example</id>
			<xsl:apply-templates select="response/result/doc"/>
		</feed>
	</xsl:template>

	<xsl:template match="doc">
		<xsl:variable name="id" select="str[@name='id']"/>
		<entry>
			<title><xsl:value-of select="str[@name='name']"/></title>
			<link href="http://localhost:8983/solr/select?q={$id}"/>
			<id>tag:localhost,2007:<xsl:value-of select="$id"/></id>
			<summary><xsl:value-of select="arr[@name='features']"/></summary>
			<updated><xsl:value-of select="date[@name='timestamp']"/></updated>
		</entry>
	</xsl:template>

</xsl:stylesheet>
