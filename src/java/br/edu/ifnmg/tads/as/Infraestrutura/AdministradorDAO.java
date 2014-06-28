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
        String Consulta = "select a from Administrador a";
        if (obj != null) {
            Consulta = Consulta + " where a.nome like '%" + obj.getNome() + "%'";
        }
        Query q = manager.createQuery(Consulta);
        return q.getResultList();
    }

}
