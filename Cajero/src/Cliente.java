
import java.io.Serializable;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ANDRES ROMERO
 */
public class Cliente implements Serializable{
    private String nombre;
    private Transaccion transaccion;
    private long costo;
    private Date fechaNacimiento;

    public Cliente(String nombre, Transaccion transaccion, long costo, Date fechaNacimiento) {
        this.nombre = nombre;
        this.transaccion = transaccion;
        this.costo = costo;
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Transaccion getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Transaccion transaccion) {
        this.transaccion = transaccion;
    }

    public long getCosto() {
        return costo;
    }

    public void setCosto(long costo) {
        this.costo = costo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    @Override
    public String toString() {
        return " nombre = "+nombre+", transaccion = "+transaccion+", costo = "+costo;
    }
}
