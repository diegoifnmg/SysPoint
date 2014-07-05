/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Infraestrutura;

import br.edu.ifnmg.tads.as.DomainModel.Cargo;
import br.edu.ifnmg.tads.as.DomainModel.ICargoRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Diego
 */
@Stateless(name = "ICargoRepositorio")
public class CargoDAO extends GenericoDAO<Cargo> implements ICargoRepositorio{
    
    public CargoDAO() {
        super(Cargo.class);
    }
    
    @Override
    public List<Cargo> Buscar(Cargo obj) {
         // Corpo da consulta
        String consulta = "select c from Cargo c WHERE c.ativo = 1 AND c.cargoid != 0 ";

        // A parte where da consulta
        String filtro = " ";


        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND c.nome like '%"+obj.getNome()+"%' ";
              
            }
            
            // Se houver filtros, coloca o "where" na consulta
            if (filtro.length() > 0) {
                consulta += filtro;
            }
        }

        // Cria a consulta no JPA
        Query query = manager.createQuery(consulta);

        // Executa a consulta
        return query.getResultList();

    }
    
    @Override
    public boolean Apagar(Cargo obj) {
        try {
            Query query = manager.createQuery("Update Cargo c set c.ativo = 0 WHERE c.id =:cargoid");
            query.setParameter("id", obj.getCargoid());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
    
}
