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
public class DepartamentoDAO extends GenericoDAO<Departamento> implements IDepartamentoRepositorio {

   
    public DepartamentoDAO() {
        super(Departamento.class);
    }

    @Override
    public List<Departamento> Buscar(Departamento obj) {
         // Corpo da consulta
        String consulta = "select d from Departamento d WHERE d.ativo = 1 AND d.id != 0 ";

        // A parte where da consulta
        String filtro = " ";


        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND d.nome like '%"+obj.getNome()+"%' ";
              
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                
                filtro += " AND d.id like '%"+obj.getId()+"%'";
                
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
    public boolean Apagar(Departamento obj) {
        try {
            Query query = manager.createQuery("Update Departamento d set d.ativo = 0 WHERE d.id =:id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
}
