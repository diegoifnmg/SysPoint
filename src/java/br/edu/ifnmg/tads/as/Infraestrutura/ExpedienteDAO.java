/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.tads.as.Infraestrutura;

import br.edu.ifnmg.tads.as.DomainModel.Expediente;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Maike Jordan
 */
@Stateless
public class ExpedienteDAO extends GenericoDAO<Expediente>{

    public ExpedienteDAO() {
        super(Expediente.class);
    }

    @Override
    public List<Expediente> Buscar(Expediente obj) {
        String Consulta = "select e from Expediente e";
        if (obj != null) {
            Consulta = Consulta + " where e.nome like '%" + obj.getNome() + "%'";
        }
        Query q = manager.createQuery(Consulta);
        return q.getResultList();
    }

}

