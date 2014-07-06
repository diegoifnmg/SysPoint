/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Infraestrutura;

import br.edu.ifnmg.tads.as.DomainModel.Cargo;
import br.edu.ifnmg.tads.as.DomainModel.IRegistroAtividadeRepositorio;
import br.edu.ifnmg.tads.as.DomainModel.RegistroAtividade;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Maike Jordan
 */
@Stateless
public class RegistroAtividadeDAO extends GenericoDAO<RegistroAtividade> implements IRegistroAtividadeRepositorio {
   
    public RegistroAtividadeDAO() {
        super(Cargo.class);
    }
    
    @Override
    public List<RegistroAtividade> Buscar(RegistroAtividade obj) {
         // Corpo da consulta
        String consulta = "select ra from RegistroAtividade ra WHERE ra.ativo = 1 AND ra.id != 0 ";

        // A parte where da consulta
        String filtro = " ";
      
        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Executa a consulta
        return query.getResultList();

    }

}
