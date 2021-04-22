/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SDAV.Controlador;

import SDAV.Modelo.Cliente;
import SDAV.Modelo.ModeloCliente;
import java.util.List;

/**
 *
 * @author Ryzen
 */
public class ControladorCliente {
    private ModeloCliente modelo;

    public ControladorCliente(ModeloCliente modelo) {
        this.modelo = modelo;
    }
    
    public void InciarContro(){ 
        cargarlista();
    }
    
    private void cargarlista(){

       List<Cliente> lista = modelo.Listar();
        
       lista.stream().forEach(p->{
       });
   
        
    }
}
