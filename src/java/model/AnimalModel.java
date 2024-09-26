/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author fgmrr
 */
public class AnimalModel {
  
    private String color;
    private String especie;
    private String tipo_animal;
    private String tipo_alimento;
    private Double peso;
    private String habitad;
    private String altura;
    private Integer id;

    public AnimalModel() {
        
    }

    public AnimalModel(String color, String especie, String tipo_animal, String tipo_alimento, Double peso, String habitad, String altura, Integer id) {
        this.color = color;
        this.especie = especie;
        this.tipo_animal = tipo_animal;
        this.tipo_alimento = tipo_alimento;
        this.peso = peso;
        this.habitad = habitad;
        this.altura = altura;
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getTipo_animal() {
        return tipo_animal;
    }

    public void setTipo_animal(String tipo_animal) {
        this.tipo_animal = tipo_animal;
    }

    public String getTipo_alimento() {
        return tipo_alimento;
    }

    public void setTipo_alimento(String tipo_alimento) {
        this.tipo_alimento = tipo_alimento;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public String getHabitad() {
        return habitad;
    }

    public void setHabitad(String habitad) {
        this.habitad = habitad;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   

    @Override
    public String toString() {
        return "Animal{" + "Id=" + id + "color=" + color + ", especie=" + especie + ", tipo_animal=" + tipo_animal + ", tipo_alimento=" + tipo_alimento +", peso=" + peso + ", habitad=" + habitad + ", altura=" + altura + '}';
    } 
    
}
