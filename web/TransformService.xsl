<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="Collection/modIt">
        <html>
            <body>
                <head><title>XSLT</title></head>
                <h2>Services</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Customer id</th>
                        <th>Tariff id</th>
                        <th>Status</th>
                        <th>Actual order Id</th>
                    </tr>
                    <xsl:for-each select="entry/value">
                        <tr>
                            <td><xsl:value-of select="Number"/></td>
                            <td><xsl:value-of select="customerID"/></td>
                            <td><xsl:value-of select="tariffID"/></td>
                            <td><xsl:value-of select="status"/></td>
                            <td><xsl:value-of select="actualOrderID"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>