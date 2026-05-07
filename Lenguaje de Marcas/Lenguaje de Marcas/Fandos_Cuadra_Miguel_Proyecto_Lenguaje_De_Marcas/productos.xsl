<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:output method="html" indent="yes" encoding="UTF-8"/>

<xsl:template match="/">
    <html>
        <head>
            <title>Productos transformados</title>
            <style>
                body {
                    font-family: Arial, sans-serif;
                    background-color: #f4f6f8;
                    padding: 20px;
                }
                h2 {
                    color: #1f2937;
                    text-align: center;
                }
                .contenedor {
                    display: flex;
                    flex-wrap: wrap;
                    gap: 20px;
                    justify-content: center;
                    margin-top: 30px;
                }
                .tarjeta {
                    background-color: white;
                    width: 250px;
                    padding: 18px;
                    border-radius: 12px;
                    border-left: 6px solid #f59e0b;
                    box-shadow: 0 4px 12px rgba(0,0,0,0.12);
                }
                .tarjeta h3 {
                    margin-bottom: 10px;
                    color: #111827;
                }
                .tarjeta p {
                    margin: 6px 0;
                }
            </style>
        </head>
        <body>
            <h2>Productos del gimnasio</h2>
            <div class="contenedor">
                <xsl:for-each select="//li">
                    <div class="tarjeta">
                        <h3><xsl:value-of select="normalize-space(.)"/></h3>
                        <p><strong>Precio:</strong> <xsl:value-of select="@data-precio"/> €</p>
                        <p><strong>Categoría:</strong> <xsl:value-of select="@data-categoria"/></p>
                        <p><strong>Stock:</strong> <xsl:value-of select="@data-stock"/></p>
                    </div>
                </xsl:for-each>
            </div>
        </body>
    </html>
</xsl:template>

</xsl:stylesheet>