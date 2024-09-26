<%-- 
    Document   : actualizar_animal.jsp
    Created on : 24 sept 2024, 23:20:41
    Author     : fgmrr
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="configuration.ConnectionBD"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualizar Animal</title>
    </head>
     <body> 
        <h2>Actualizar Animal</h2> 
        <%
            String id = request.getParameter("id");
            String color = "";
            String especie = "";
            String tipo_animal = "";
            String tipo_alimento = "";
            Double peso = 0.0;
            String habitad = "";
            String altura = "";
            ConnectionBD conexion = new ConnectionBD();
            Connection connection = conexion.getConnectionBD();
            PreparedStatement statement = null;
            ResultSet resultSet = null;
            try {

                // Consulta para obtener los datos del animal por ID 
                String sql = "SELECT color, especie, tipo_animal, tipo_alimento, peso, habitad, altura"
                        + " FROM animal WHERE id LIKE ?";
                statement = connection.prepareStatement(sql);
                statement.setString(1, id);
                resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    color = resultSet.getString("color");
                    especie = resultSet.getString("especie");
                    tipo_animal = resultSet.getString("tipo_animal");
                    tipo_alimento = resultSet.getString("tipo_alimento");
                    peso = resultSet.getDouble("peso");
                    habitad = resultSet.getString("habitad");
                    altura = resultSet.getString("altura");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (resultSet != null) {
                        resultSet.close();
                    }
                    if (statement != null) {
                        statement.close();
                    }
                    if (connection != null) {
                        connection.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        %> 

        <!-- Formulario con los datos del animal para actualizar --> 
        <form id="formActualizarAnimal"> 
            Color: <br>
            <input type="text" id="txt_color" value="<%= color%>" ><br>
            Especie: <br>
            <input type="text" id="txt_especie" value="<%= especie%>" ><br>
            Tipo de Animal: <br>
            <input type="text" id="txt_tipo_animal" value="<%= tipo_animal%>" ><br>
            Tipo de Alimento: <br>
            <input type="text" id="txt_tipo_alimento" value="<%= tipo_alimento%>" ><br>
            Peso: <br>
            <input type="number" id="txt_peso" value="<%= peso%>" ><br>
            Habitad: <br>
            <input type="text" id="txt_habitad" value="<%= habitad%>"><br> 
            Altura: <br>
            <input type="text" id="txt_altura" value="<%= altura%>" ><br>
            <input type="button" value="Actualizar" onclick="actualizarAnimal()"> 
        </form> 
        <div id="resultado"></div> 
        <script>
            function actualizarAnimal() {
                const color = document.getElementById("txt_color").value;
                const especie = document.getElementById("txt_especie").value;
                const tipo_animal = document.getElementById("txt_tipo_animal").value;
                const tipo_alimento = document.getElementById("txt_tipo_alimento").value;
                const peso = document.getElementById("txt_peso").value;
                const habitad = document.getElementById("txt_habitad").value;
                const altura = document.getElementById("txt_altura").value;
                
                const datos = new URLSearchParams();
                datos.append("color", color);
                datos.append("especie", especie);
                datos.append("tipo_animal", tipo_animal);
                datos.append("tipo_alimento", tipo_alimento);
                datos.append("peso", peso);
                datos.append("habitad", habitad);
                datos.append("altura", altura);
                console.log(datos );
                var urlParams = new URLSearchParams(window.location.search);
                var id22 = urlParams.get("id");
                console.log("Id  " + id22);
                fetch("${pageContext.request.contextPath}/animal?id="+id22, {
                    method: "PUT",
                    body: datos,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded'
                    }
                })
                        .then(response => response.text())
                        .then(data => {
                            document.getElementById("resultado").innerText = data;
                        })
                        .catch(error => {
                            document.getElementById("resultado").innerText = "Error al actualizar el animal.";
                            console.error('Error:', error);
                        });
            }

        </script> 
    </body> 
</html>
