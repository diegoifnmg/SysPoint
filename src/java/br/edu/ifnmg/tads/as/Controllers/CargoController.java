/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Controllers;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author Diego
 */
@Named(value = "cargoController")
@SessionScoped
public class CargoController implements Serializable {

    /**
     * Creates a new instance of CargoController
     */
    public CargoController() {
    }
}
