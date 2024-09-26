/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author fgmrr
 */
public class CustomResponse {
    private Integer httpCode; 
    private String mensaje; 
    private Object data;

    public CustomResponse(Integer httpCode, String mensaje, Object data) {
        this.httpCode = httpCode;
        this.mensaje = mensaje;
        this.data = data;
    }
}
