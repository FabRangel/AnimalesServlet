/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import com.google.gson.Gson;
import configuration.ConnectionBD;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.CustomResponse;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AnimalModel;

/**
 *
 * @author fgmrr
 */
@WebServlet("/api/data")
public class ApiServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
      private Connection conn;
    PreparedStatement ps;
    Statement statement;
    ResultSet rs;

       private Connection getConnection() throws SQLException, ClassNotFoundException {
        // Configura la conexión a tu base de datos
        String jdbcURL = "jdbc:mysql://localhost:3306/prueba2";
        String jdbcUsername = "root";
        String jdbcPassword = "";
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("Se ejecuta el doGet");
        List<AnimalModel> listaAnimales = new ArrayList<>();
        String sql = "SELECT id, color, especie, tipo_animal, tipo_alimento, peso, habitad, altura FROM animal";

        try {
            conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            // Itera sobre los resultados y crea objetos UsuarioModel
            while (rs.next()) {
                AnimalModel animal = new AnimalModel();
                animal.setId(rs.getInt("id"));
                animal.setColor(rs.getString("color"));
                animal.setEspecie(rs.getString("especie"));
                animal.setTipo_animal(rs.getString("tipo_animal"));
                animal.setTipo_alimento(rs.getString("tipo_alimento"));
                animal.setPeso(rs.getDouble("peso"));
                animal.setHabitad(rs.getString("habitad"));
                animal.setAltura(rs.getString("altura"));
                
                listaAnimales.add(animal);
            }
            //Pasa la lista de usuarios al JSP
            request.setAttribute("animales", listaAnimales);
            request.getRequestDispatcher("/views/mostrar_animales.jsp").forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al obtener los usuarios");
        }

        CustomResponse cResponse = new CustomResponse(200, "Ok", listaAnimales);

        String jsonResponse = new Gson().toJson(cResponse);
        response.getWriter().write(jsonResponse);
    }
    
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionBD conexion = new ConnectionBD();
        String id = request.getParameter("id");
        if (id == null || id.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Id not found in URL");
            return;
        }
        // Leer el body de la solicitud PUT
        StringBuilder sb = new StringBuilder();
        String line;
        try ( BufferedReader reader = request.getReader()) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        // Obtener los datos en formato URL-encoded (x-www-form-urlencoded)
        String body = sb.toString();
        // Si el cuerpo es de tipo application/x-www-form-urlencoded, los parámetros estarán en el formato clave=valor
        // Se puede usar un parseador para dividirlo
        String[] params = body.split("&");
        String[] values = new String[7];
        int i = 0;
        for (String param : params) {
            String[] keyValue = param.split("=");
            String key = keyValue[0];  // Nombre del parámetro
            String value = keyValue.length > 1 ? keyValue[1] : "";  // Valor del parámetro
            // Aquí puedes procesar los parámetros según tu lógica
            values[i] = value;
            i++;
            System.out.println("Key: " + key + ", Value: " + value);
            response.getWriter().write("Key: " + key + " -> Value: " + value + "\n");
        }

        System.out.println("Query String: " + request.getQueryString());
        System.out.println("MMMMMM: " + id);
        // Obtener los parámetros del formulario 
        String color = request.getParameter("txt_color");
        String especie = request.getParameter("txt_especie");
        String tipo_animal = request.getParameter("txt_tipo_animal");
        String tipo_alimento = request.getParameter("txt_tipo_alimento");
        String peso = request.getParameter("txt_peso");
        String habitad = request.getParameter("txt_habitad");
        String altura = request.getParameter("txt_altura");
        
        //String value = URLDecoder.decode(valor, "UTF-8");
        String sql2 = "UPDATE animal SET color = '" + URLDecoder.decode(values[0], "UTF-8")
                + "', especie = '" + URLDecoder.decode(values[1], "UTF-8")
                + "', tipo_animal = '" + URLDecoder.decode(values[2], "UTF-8")
                + "', tipo_alimento = '" + URLDecoder.decode(values[3], "UTF-8")
                + "', peso = '" + URLDecoder.decode(values[4], "UTF-8")
                + "', habitad = '" + URLDecoder.decode(values[5], "UTF-8")
                + "', altura = '" + URLDecoder.decode(values[6], "UTF-8")
                + "' WHERE id like '" + id + "'";
        System.out.println("SQL ****" + sql2);
        response.getWriter().write("SQL:**** " + sql2);
        try {
            conn = conexion.getConnectionBD();
            ps = conn.prepareStatement(sql2);

            ps.executeUpdate();
            /**
             * Crear la consulta SQL para insertar el usuario String sql =
             * "UPDATE usuario SET apellidos = ? , celular = ?, fecha_nac = ?,"
             * + "nombre = ?, hora = ?, correo = ? WHERE matricula LIKE ?";
             * ps.setString(1, apellidos); ps.setString(2, celular);
             * ps.setDate(3, fechaFinal); ps.setString(4, nombre); ps.setTime(5,
             * horaFinal); ps.setString(6, correo);
             */
            // Ejecutar la consulta 
            /**String sql = "UPDATE usuario SET apellidos = ? , celular = ?, fecha_nac = ?,"
                    + "nombre = ?, hora = ?, correo = ? WHERE matricula LIKE ?";
            ps.setString(1, values[0]);
            ps.setString(2, values[2]);
            ps.setDate(3, Date.valueOf(values[3]));
            ps.setString(4, values[0]);
            ps.setTime(5, Time.valueOf(values[6]));
            ps.setString(6, values[4]);**/
            int filasActualizadas = ps.executeUpdate();

            // Enviar respuesta de éxito 
            response.setContentType("text/plain");
            if (filasActualizadas > 0) {
                response.getWriter().write("Animal actualizado exitosamente.");
            } else {
                response.getWriter().write("No se encontró el animal para actualizar.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error al actualizar el animal." + e + "\n" + sql2);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Do deletee");
        ConnectionBD conexion = new ConnectionBD();
        String id = request.getParameter("id");
        // Validate input
        if (id == null || id.trim().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Invalid request
            return;
        }

        String sql = "DELETE FROM animal WHERE id like ?";

        try {
            conn = conexion.getConnectionBD();
            ps = conn.prepareStatement(sql);
            ps.setString(1, id);

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                response.setStatus(HttpServletResponse.SC_OK); // Eliminar exitoso 
            } else {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND); // No se encontró el usuario 
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // Error del servidor 
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
