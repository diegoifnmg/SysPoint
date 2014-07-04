/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifnmg.tads.as.Infraestrutura;

import br.edu.ifnmg.tads.as.DomainModel.Administrador;
import br.edu.ifnmg.tads.as.DomainModel.IAdministradorRepositorio;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author Diego
 */
@Stateless(name = "IAdministradorRepositorio")
public class AdministradorDAO extends GenericoDAO<Administrador> implements IAdministradorRepositorio{

    public AdministradorDAO() {
        super(Administrador.class);
    }

    
    @Override
    public List<Administrador> Buscar(Administrador obj) {
         // Corpo da consulta
        String consulta = "select f from Administrador f WHERE f.ativo = 1 AND f.id != 0 ";

        // A parte where da consulta
        String filtro = " ";


        // Verifica campo por campo os valores que serão filtrados
        if (obj != null) {
            //Nome
            if (obj.getNome() != null && obj.getNome().length() > 0) {
                filtro += " AND f.nome like '%"+obj.getNome()+"%' ";
              
            }
            //Id
            if (obj.getId() != null && obj.getId() > 0) {
                
                filtro += " AND f.id like '%"+obj.getId()+"%'";
                
            }
            //Cpf
            if (obj.getCpf() != null && obj.getCpf().length() > 0) {
                
                filtro += " AND f.cpf like '%"+obj.getCpf()+"%'";
                
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
    public boolean Apagar(Administrador obj) {
        try {
            Query query = manager.createQuery("Update Administrador a set a.ativo = 0 WHERE a.id =:id");
            query.setParameter("id", obj.getId());
            query.executeUpdate();

            return true;

        } catch (Exception ex) {
            ex.printStackTrace();

            return false;
        }
    }
}
