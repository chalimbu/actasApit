/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author sebastian
 */
public class Validaciones {
     public boolean validarNumeros(String cadena)
    {
        return cadena.matches("[0-9]+");
    }
}
