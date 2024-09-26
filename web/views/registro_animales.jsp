<%-- 
    Document   : registro_usuarios.jsp
    Created on : 23 sept 2024, 20:49:04
    Author     : fgmrr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <body>
        <h1>Registro Animales</h1>
        <form action="${pageContext.request.contextPath}/animal" method="POST">
                Color: <br>
                <input type="text" name="txt_color"><br>
                Especie <br>
                <input type="text" name="txt_especie"><br>
                Tipo de animal: <br>
                <input type="text" name="txt_tipo_animal"><br>
                Tipo de alimento: <br>
                <input type="text" name="txt_tipo_alimento"><br>
                Peso: <br>
                <input type="number" name="txt_peso"><br>
                Habitad: <br>
                <input type="text" name="txt_habitad"><br> 
                Altura: <br>
                <input type="number" name="txt_altura"><br> 
                <input type="submit" name="accion" value="Agregar">
            </form>
                <a href="${pageContext.request.contextPath}/api/data">Cargar Animales</a>
                
    </body>
</html>
