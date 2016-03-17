<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="Collection/modIt">
        <html>
            <body>
                <head>
                    <title>XSLT</title>
                </head>
                <h2>Customers</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Name</th>
                        <th>Phone</th>
                        <th>Address</th>
                    </tr>
                    <xsl:for-each select="entry/value">
                        <tr>
                            <td><xsl:value-of select="Number"/></td>
                            <td><xsl:value-of select="Name"/></td>
                            <td><xsl:value-of select="Phone_number"/></td>
                            <td><xsl:value-of select="Adress"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>