/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifnmg.tads.as.Infraestrutura;

import br.edu.ifnmg.tads.as.DomainModel.Administrador;
import br.edu.ifnmg.tads.as.DomainModel.Funcionario;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Diego
 */
public class AdministradorDAO extends GenericoDAO<Administrador> {
    
    public AdministradorDAO() {
        super(Administrador.class);
    }
        
    public List<Administrador> Buscar(Administrador obj) {
        String Consulta = "select l from Funcionario l";
        if (obj != null) {
            Consulta = Consulta + " where l.nome like '%" + obj.getNome() + "%'";
        }
        Query q = manager.createQuery(Consulta);
        return q.getResultList();
    }

    
}
