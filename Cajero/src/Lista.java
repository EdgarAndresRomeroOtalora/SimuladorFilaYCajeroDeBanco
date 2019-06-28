
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import util.Util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ANDRES ROMERO
 */
public class Lista implements Serializable {

    private Nodo nodoFilaGeneral;
    private Nodo nodoFilaPreferencial;
    private Nodo nodoCajeroGeneral;
    private Nodo nodoCajeroPreferencial;
    static int cambioFila = 0;

    final String NOM_ARCHIVO_Lista = "lista.dat";
    util.Util util = new Util();
    ArrayList<Object> objetosLista = new ArrayList<>();

    public Lista() {
        nodoFilaGeneral = null;
        nodoFilaPreferencial = null;
        nodoCajeroGeneral = null;
        nodoCajeroPreferencial = null;
    }

    public boolean generalHabilitado() {
        String habilitado = VistaCajero.cbxHabilitadoGeneral.getSelectedItem().toString();
        if (habilitado.equals("habilitado")) {
            return true;
        } else {
            return false;
        }
    }

    public boolean preferencialHabilitado() {
        String habilitado = VistaCajero.cbxHabilitadoPreferencial.getSelectedItem().toString();
        if (habilitado.equals("habilitado")) {
            return true;
        } else {
            return false;
        }
    }

    public int conocerEdad() throws ParseException {
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = (Date) formatoFecha.parse(VistaCajero.txtFechaNacimiento.getText());
        Date fechaActual = new Date();
        int edad = (int) ((fechaActual.getYear() - fechaNacimiento.getYear()));
        return edad;
    }

    public Nodo valor() {
        String nombre = VistaCajero.txtIngresaNombre.getText();
        String transaccion = VistaCajero.cbxIngresarTransaccion.getSelectedItem().toString();
        long costo = Long.valueOf(VistaCajero.txtIngresaValor.getText());
        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNacimiento = null;
        try {
            fechaNacimiento = (Date) formatoFecha.parse(VistaCajero.txtFechaNacimiento.getText());
        } catch (Exception e) {
        }

        Nodo valor = new Nodo(nombre, Transaccion.valueOf(transaccion), costo, fechaNacimiento);
        return valor;
    }

    public void AgregarAlFinalFila() {
        Nodo valor = valor();
        Date fechaActual = new Date();
        int edad = (int) ((fechaActual.getYear() - valor.getValor().getFechaNacimiento().getYear()));
        if (edad <= 55) {
            if (nodoFilaGeneral == null) {
                nodoFilaGeneral = valor;
            } else {
                Nodo aux = nodoFilaGeneral;
                while (aux.getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(valor);
            }
        } else {
            if (nodoFilaPreferencial == null) {
                nodoFilaPreferencial = valor;
            } else {
                Nodo aux = nodoFilaPreferencial;
                while (aux.getSiguiente() != null) {
                    aux = aux.getSiguiente();
                }
                aux.setSiguiente(valor);
            }
        }
        
    }

    public void AgregarAlInicioCajeroGeneral(Nodo valor) {
        if (valor != null) {
            Nodo nodo1 = new Nodo(valor.getValor().getNombre(), valor.getValor().getTransaccion(), valor.getValor().getCosto(), valor.getValor().getFechaNacimiento());
            if (nodoCajeroGeneral == null) {
                nodoCajeroGeneral = nodo1;
            } else {
                nodo1.setSiguiente(nodoCajeroGeneral);
                nodoCajeroGeneral = nodo1;
            }
        }
    }

    public void AgregarAlInicioCajeroPreferencial(Nodo valor) {
        if (valor != null) {
            Nodo nodo1 = new Nodo(valor.getValor().getNombre(), valor.getValor().getTransaccion(), valor.getValor().getCosto(), valor.getValor().getFechaNacimiento());
            if (nodoCajeroPreferencial == null) {
                nodoCajeroPreferencial = nodo1;
            } else {
                nodo1.setSiguiente(nodoCajeroPreferencial);
                nodoCajeroPreferencial = nodo1;
            }
        }
    }

    public void borrarPrimeroFilaGeneral() {
        if (nodoFilaGeneral != null) {
            nodoFilaGeneral = nodoFilaGeneral.getSiguiente();
        }
    }

    public void borrarPrimeroFilaPreferencia() {
        if (nodoFilaPreferencial != null) {
            nodoFilaPreferencial = nodoFilaPreferencial.getSiguiente();
        }
    }

    public void cajeroGeneral() {
        if (generalHabilitado() && preferencialHabilitado()) {
            if (nodoFilaGeneral != null) {
                VistaCajero.txtAtendidaGeneral.setText(nodoFilaGeneral.getValor().getNombre());
                AgregarAlInicioCajeroGeneral(nodoFilaGeneral);
                borrarPrimeroFilaGeneral();
            }else{
                VistaCajero.txtAtendidaGeneral.setText(nodoFilaPreferencial.getValor().getNombre());
                AgregarAlInicioCajeroGeneral(nodoFilaPreferencial);
                borrarPrimeroFilaPreferencia();
            }
        } else {
            if (generalHabilitado() && !preferencialHabilitado()) {
                if (nodoFilaGeneral != null && nodoFilaPreferencial != null) {
                    if (cambioFila == 0) {
                        VistaCajero.txtAtendidaGeneral.setText(nodoFilaGeneral.getValor().getNombre());
                        AgregarAlInicioCajeroGeneral(nodoFilaGeneral);
                        borrarPrimeroFilaGeneral();
                        cambioFila = 1;
                    } else {
                        VistaCajero.txtAtendidaGeneral.setText(nodoFilaPreferencial.getValor().getNombre());
                        AgregarAlInicioCajeroGeneral(nodoFilaPreferencial);
                        borrarPrimeroFilaPreferencia();
                        cambioFila = 0;
                    }
                } else {
                    if (nodoFilaGeneral == null && nodoFilaPreferencial != null) {
                        VistaCajero.txtAtendidaGeneral.setText(nodoFilaPreferencial.getValor().getNombre());
                        AgregarAlInicioCajeroGeneral(nodoFilaPreferencial);
                        borrarPrimeroFilaPreferencia();
                    }
                    if (nodoFilaGeneral != null && nodoFilaPreferencial == null) {
                        VistaCajero.txtAtendidaGeneral.setText(nodoFilaGeneral.getValor().getNombre());
                        AgregarAlInicioCajeroGeneral(nodoFilaGeneral);
                        borrarPrimeroFilaGeneral();
                    }
                }
            }
        }
    }

    public void cajeroPreferencial() {
        if (generalHabilitado() && preferencialHabilitado()) {
            if (nodoFilaPreferencial != null) {
                VistaCajero.txtAtendidaPreferencial.setText(nodoFilaPreferencial.getValor().getNombre());
                AgregarAlInicioCajeroPreferencial(nodoFilaPreferencial);
                borrarPrimeroFilaPreferencia();
            }else{
               VistaCajero.txtAtendidaPreferencial.setText(nodoFilaGeneral.getValor().getNombre()); 
               AgregarAlInicioCajeroPreferencial(nodoFilaGeneral);
               borrarPrimeroFilaGeneral();
            }
        } else {
            if (!generalHabilitado() && preferencialHabilitado()) {
                if (nodoFilaGeneral != null && nodoFilaPreferencial != null) {
                    if (cambioFila == 0) {
                        VistaCajero.txtAtendidaPreferencial.setText(nodoFilaPreferencial.getValor().getNombre());
                        AgregarAlInicioCajeroPreferencial(nodoFilaPreferencial);
                        borrarPrimeroFilaPreferencia();
                        cambioFila = 1;
                    } else {
                        VistaCajero.txtAtendidaPreferencial.setText(nodoFilaGeneral.getValor().getNombre());
                        AgregarAlInicioCajeroPreferencial(nodoFilaGeneral);
                        borrarPrimeroFilaGeneral();
                        cambioFila = 0;
                    }
                } else {
                    if (nodoFilaGeneral == null && nodoFilaPreferencial != null) {
                        VistaCajero.txtAtendidaPreferencial.setText(nodoFilaPreferencial.getValor().getNombre());
                        AgregarAlInicioCajeroPreferencial(nodoFilaPreferencial);
                        borrarPrimeroFilaPreferencia();
                    }
                    if (nodoFilaGeneral != null && nodoFilaPreferencial == null) {
                        VistaCajero.txtAtendidaPreferencial.setText(nodoFilaGeneral.getValor().getNombre());
                        AgregarAlInicioCajeroPreferencial(nodoFilaGeneral);
                        borrarPrimeroFilaGeneral();
                    }
                }
            }
        }
    }

    public void Listar() {
        getSerializar();
        getDesSerializar();
        String listaGeneral = "";
        String listaPreferencia = "";
        Nodo auxGeneral = nodoFilaGeneral;
        while (auxGeneral != null) {
            listaGeneral = listaGeneral + auxGeneral.getValor().getNombre() + "\n";
            auxGeneral = auxGeneral.getSiguiente();
        }
        Nodo auxPreferencial = nodoFilaPreferencial;
        while (auxPreferencial != null) {
            listaPreferencia = listaPreferencia + auxPreferencial.getValor().getNombre() + "\n";
            auxPreferencial = auxPreferencial.getSiguiente();
        }
        VistaCajero.txtFilaGeneral.setText(listaGeneral);
        VistaCajero.txtFilaPreferencial.setText(listaPreferencia);
        Nodo auxRetirosCaja1 = nodoCajeroGeneral;
        long retirosCaja1 = 0;
        while (auxRetirosCaja1 != null) {
            if (auxRetirosCaja1.getValor().getTransaccion() == Transaccion.retiro) {
                retirosCaja1 = retirosCaja1 + auxRetirosCaja1.getValor().getCosto();
            }
            auxRetirosCaja1 = auxRetirosCaja1.getSiguiente();
        }
        VistaCajero.txtRetirosCajero1.setText(retirosCaja1 + "");
        Nodo auxRetirosCaja2 = nodoCajeroPreferencial;
        long retirosCaja2 = 0;
        while (auxRetirosCaja2 != null) {
            if (auxRetirosCaja2.getValor().getTransaccion() == Transaccion.retiro) {
                retirosCaja2 = retirosCaja2 + auxRetirosCaja2.getValor().getCosto();
            }
            auxRetirosCaja2 = auxRetirosCaja2.getSiguiente();
        }
        VistaCajero.txtRetirosCajero2.setText(retirosCaja2 + "");
        long retirosTotal = retirosCaja1 + retirosCaja2;
        VistaCajero.txtRetirosTotal.setText(retirosTotal + "");
        
    }

    public boolean horarioAtencion() {
        Date hora = new Date();
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm:ss");
        String strHora = formatoHora.format(hora);
        Date horaApertura = null;
        Date horaSalida = null;
        try {
            hora = formatoHora.parse(strHora);
            horaApertura = formatoHora.parse(VistaCajero.txtHoraApertura.getText());
            horaSalida = formatoHora.parse(VistaCajero.txtHoraCierre.getText());
        } catch (Exception e) {

        }
        int horaAperturas = (int) horaApertura.getTime();
        int horaSalidas = (int) horaSalida.getTime();
        int horaActual = (int) hora.getTime();
        //System.out.println(horaAperturas + " " + horaSalidas + " " + horaActual);
        if (horaActual >= horaAperturas && horaActual <= horaSalidas) {
            return true;
        } else {
            return false;
        }
    }

    public boolean datosIngresadosCorrectos() {
        if (!VistaCajero.txtIngresaNombre.getText().isEmpty() && !VistaCajero.txtIngresaValor.getText().isEmpty()
                && !VistaCajero.txtFechaNacimiento.getText().isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "hay campos vacios");
            return false;
        }
    }

    public void getDesSerializar() {
        if (util.desSerializar(NOM_ARCHIVO_Lista) != null) {
            objetosLista = (ArrayList<Object>) util.desSerializar(NOM_ARCHIVO_Lista);
            nodoFilaGeneral = (Nodo) objetosLista.get(0);
            nodoFilaPreferencial = (Nodo) objetosLista.get(1);
            nodoCajeroGeneral = (Nodo) objetosLista.get(2);
            nodoCajeroPreferencial = (Nodo) objetosLista.get(3);
            objetosLista.clear();
        }
    }
    
    public void getSerializar(){
        objetosLista.add(nodoFilaGeneral);
        objetosLista.add(nodoFilaPreferencial);
        objetosLista.add(nodoCajeroGeneral);
        objetosLista.add(nodoCajeroPreferencial);
        util.serializar(NOM_ARCHIVO_Lista, objetosLista);
    }
}
