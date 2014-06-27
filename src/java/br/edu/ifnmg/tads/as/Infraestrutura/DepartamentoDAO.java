/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.tads.as.Infraestrutura;

import br.edu.ifnmg.tads.as.DomainModel.Departamento;
import br.edu.ifnmg.tads.as.DomainModel.IDepartamentoRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Maike Jordan
 */
@Stateless(name = "IDepartamentoRepositorio")
public class DepartamentoDAO extends GenericoDAO<Departamento> implements IDepartamentoRepositorio{

    public DepartamentoDAO() {
        super(Departamento.class);
    }

    @Override
    public List<Departamento> Buscar(Departamento obj) {
        String Consulta = "select d from Departamento d";
        if (obj != null) {
            Consulta = Consulta + " where d.nome like '%" + obj.getNome() + "%'";
        }
        Query q = manager.createQuery(Consulta);
        return q.getResultList();
    }

}
