/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Infraestrutura;

import br.edu.ifnmg.tads.as.DomainModel.IRegistroPontoRepositorio;
import br.edu.ifnmg.tads.as.DomainModel.RegistroPonto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Diego
 */
@Stateless
public class RegistroPontoDAO extends GenericoDAO<RegistroPonto> implements IRegistroPontoRepositorio {
   
    public RegistroPontoDAO() {
        super(RegistroPonto.class);
    }
    
    @Override
    public List<RegistroPonto> Buscar(RegistroPonto obj) {
         // Corpo da consulta
        String consulta = "select rp from RegistroPonto rp WHERE rp.ativo = 1 AND rp.id != 0 ";

        // A parte where da consulta
        String filtro = " ";
      
        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Executa a consulta
        return query.getResultList();

    }

}
