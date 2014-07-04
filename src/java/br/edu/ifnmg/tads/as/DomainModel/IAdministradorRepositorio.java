/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.DomainModel;

/**
 *
 * @author Diego
 */
public interface IAdministradorRepositorio extends RepositorioGenerico<Administrador>{
    
    //public Administrador porLogin(String login) throws Exception;   TIREI PQ TAVA DANDO ERRO NO CADASTRO DE ADMIN
    
    public Administrador Login(Administrador admin);
}
