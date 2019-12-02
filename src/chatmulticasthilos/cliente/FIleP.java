/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatmulticasthilos.cliente;

import java.io.Serializable;
import java.util.EventObject;

/**
 *
 * @author Isaac
 */
public class FIleP extends EventObject implements Serializable
{
    private int nP;
    private long pesoF;
    private String rutaF;
    private String nombreF;
    private byte[] datos;
    private long pesoTotal;
    
    public FIleP(Object source, byte[] datosF, String nombreFi, String rutaA, long peso, int nP, long pesoT)
    {        
        super(source);
        datos = datosF;
        nombreF = nombreFi;
        rutaF = rutaA;
        pesoF = peso;
        this.nP = nP;
        pesoTotal = pesoT;
    }
    
    public FIleP(Object source, byte[] datosF)
    {
        super(source);
        datos = datosF;
    }
    

    
    public long getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(long pesoTotal) {
        this.pesoTotal = pesoTotal;
    }
    
    public int getnP() {
        return nP;
    }

    public void setnP(int nP) {
        this.nP = nP;
    }

    public byte[] getDatos() {
        return datos;
    }

    public void setDatos(byte[] datos) {
        this.datos = datos;
    }
    
    public long getPesoF() {
        return pesoF;
    }

    public void setPesoF(long pesoF) {
        this.pesoF = pesoF;
    }

    public String getRutaF() {
        return rutaF;
    }

    public void setRutaF(String rutaF) {
        this.rutaF = rutaF;
    }

    public String getNombreF() {
        return nombreF;
    }

    public void setNombreF(String nombreF) {
        this.nombreF = nombreF;
    }
}
