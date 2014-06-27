/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.tads.as.Infraestrutura;

import br.edu.ifnmg.tads.as.DomainModel.Funcionario;
import br.edu.ifnmg.tads.as.DomainModel.IFuncionarioRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author MAIKE
 */
@Stateless(name = "IFuncionarioRepositorio")
public class FuncionarioDAO extends GenericoDAO<Funcionario> implements IFuncionarioRepositorio{

    public FuncionarioDAO() {
        super(Funcionario.class);
    }

    @Override
    public List<Funcionario> Buscar(Funcionario obj) {
        String Consulta = "select f from Funcionario f";
        if (obj != null) {
            Consulta = Consulta + " where f.nome like '%" + obj.getNome() + "%'";
        }
        Query q = manager.createQuery(Consulta);
        return q.getResultList();
    }

}
