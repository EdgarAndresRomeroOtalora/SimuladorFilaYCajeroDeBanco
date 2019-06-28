
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
public class Nodo implements Serializable{
    private Nodo siguiente;
    private Cliente valor;

    public Nodo(String nombre, Transaccion transaccion, long costo, Date fecha) {
        Cliente cliente=new Cliente(nombre, transaccion, costo, fecha);
        valor = cliente;
        siguiente = null;
        }

    public Nodo getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }

    public Cliente getValor() {
        return valor;
    }

    public void setValor(Cliente valor) {
        this.valor = valor;
    }
}
