<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="Collection/modIt">
        <html>
            <body>
                <head><title>XSLT</title></head>
                <h2>Orders</h2>
                <table border="1">
                    <tr bgcolor="#9acd32">
                        <th>ID</th>
                        <th>Customer id</th>
                        <th>Tariff id</th>
                        <th>Date</th>
                        <th>Sum</th>
                        <th>Service</th>
                        <th>Status</th>
                        <th>Prev order Id</th>
                    </tr>
                    <xsl:for-each select="entry/value">
                        <tr>
                            <td><xsl:value-of select="Number"/></td>
                            <td><xsl:value-of select="Customer_number"/></td>
                            <td><xsl:value-of select="Tariff_number"/></td>
                            <td><xsl:value-of select="Order_date"/></td>
                            <td><xsl:value-of select="Order_sum"/></td>
                            <td><xsl:value-of select="Service_ID"/></td>
                            <td><xsl:value-of select="Status"/></td>
                            <td><xsl:value-of select="Previous_order_ID"/></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

</xsl:stylesheet>