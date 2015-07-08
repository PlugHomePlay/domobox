<?xml version='1.0' encoding='UTF-8'?>
<xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>
	<xsl:output media-type="text/xml" method="xml" indent="yes"/>

	<xsl:template match='/'>
		<add>
			<xsl:apply-templates select="response/result/doc"/>
		</add>
	</xsl:template>

	<!-- Ignore score (makes no sense to index) -->
	<xsl:template match="doc/*[@name='score']" priority="100">
	</xsl:template>

	<xsl:template match="doc">
		<xsl:variable name="pos" select="position()"/>
		<doc>
			<xsl:apply-templates>
			<xsl:with-param name="pos"><xsl:value-of select="$pos"/></xsl:with-param>
			</xsl:apply-templates>
		</doc>
	</xsl:template>

	<!-- Flatten arrays to duplicate field lines -->
	<xsl:template match="doc/arr" priority="100">
		<xsl:variable name="fn" select="@name"/>

		<xsl:for-each select="*">
			<xsl:element name="field">
				<xsl:attribute name="name"><xsl:value-of select="$fn"/></xsl:attribute>
				<xsl:value-of select="."/>
			</xsl:element>
		</xsl:for-each>
	</xsl:template>


	<xsl:template match="doc/*">
		<xsl:variable name="fn" select="@name"/>

		<xsl:element name="field">
			<xsl:attribute name="name"><xsl:value-of select="$fn"/></xsl:attribute>
			<xsl:value-of select="."/>
		</xsl:element>
	</xsl:template>

	<xsl:template match="*"/>
</xsl:stylesheet>

