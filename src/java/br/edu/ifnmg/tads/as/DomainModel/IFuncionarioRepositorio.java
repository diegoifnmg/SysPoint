/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.DomainModel;

import javax.ejb.Remote;

/**
 *
 * @author Maike
 */
@Remote
public interface IFuncionarioRepositorio extends RepositorioGenerico<Funcionario> {

    public Funcionario Login(Funcionario funcionario);
}
