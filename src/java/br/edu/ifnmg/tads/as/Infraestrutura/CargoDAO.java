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
        String Consulta = "select c from Cargo c";
        if (obj != null) {
            Consulta = Consulta + " where c.nome like '%" + obj.getNome() + "%'";
        }
        Query q = manager.createQuery(Consulta);
        return q.getResultList();
    }
    
}
