package System;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class DAO <E> {

private static EntityManagerFactory emf;
private EntityManager em;

@SuppressWarnings("unused")
private Class <E> classe;

static {
	try {
		emf = Persistence.createEntityManagerFactory("JavaBanco_v2");
	} catch (Exception e) {
		
	}
}

public DAO() {
	
}

public DAO(Class<E> classe) {
  this.classe = classe;
  em = emf.createEntityManager();
}

public List<E> listarTodos() {
    String jpql = "SELECT e FROM " + classe.getSimpleName() + " e";
    return em.createQuery(jpql, classe).getResultList();
}

public DAO<E> abrir(){
	em.getTransaction().begin();
	return this;
}

public DAO<E> adcionar(E entidade){
	em.persist(entidade);
	return this;
}

public DAO<E> excluir(E entidade){
    entidade = em.merge(entidade);
    em.remove(entidade);
    return this;
}
public DAO<E> atualizar(E entidade){
	em.merge(entidade);
	return this;
}

public DAO<E> fechar(){
	em.getTransaction().commit();
    if (em.isOpen()) {
        em.close();
    }
	return this;
}


public Client BuscarCliente(String cpf) {
	try {
        String jpql = "SELECT c FROM Client c WHERE c.cpf = :cpf";

        return em.createQuery(jpql, Client.class)
                 .setParameter("cpf", cpf)
                 .getSingleResult();

    } catch (Exception e) {
        return null;
    }
}

public Client BuscarPorId(Integer id) {
	try {
		String jpql = "SELECT c FROM Client c WHERE c.id = :id";
		
		return em.createQuery(jpql, Client.class)
				.setParameter("id", id)
				.getSingleResult();
		
	} catch (Exception e) {
		return null;
	}
}

public Client BuscarPixCPF(String pix_cpf) {
	try {
		String jpql = "SELECT c FROM Client c WHERE c.pix_cpf = :pix_cpf";
		
		return em.createQuery(jpql, Client.class)
				.setParameter("pix_cpf", pix_cpf)
				.getSingleResult();
		
	} catch (Exception e) {
		return null;
	}
}

public Client BuscarPixTELEFONE(String pix_celular) {
	try {
		String jpql = "SELECT c FROM Client c WHERE c.pix_celular = :pix_celular";
		
		return em.createQuery(jpql, Client.class)
				.setParameter("pix_celular", pix_celular)
				.getSingleResult();
		
	} catch (Exception e) {
		return null;
	}
}

public Client BuscarPixEMAIL(String pix_email) {
	try {
		String jpql = "SELECT c FROM Client c WHERE c.pix_email = :pix_email";
		
		return em.createQuery(jpql, Client.class)
				.setParameter("pix_email", pix_email)
				.getSingleResult();
		
	} catch (Exception e) {
		return null;
	}
}

public Client BuscarPixALEATORIO(String pix_aleatorio) {
	try {
		String jpql = "SELECT c FROM Client c WHERE c.pix_aleatorio = :pix_aleatorio";
		
		return em.createQuery(jpql, Client.class)
				.setParameter("pix_aleatorio", pix_aleatorio)
				.getSingleResult();
		
	} catch (Exception e) {
		return null;
	}
}

public Investment buscarPorClienteETipo(Client cliente, TipoInvestimento tipo) {

    String jpql = "SELECT i FROM Investment i " +
                  "WHERE i.cliente = :cliente " +
                  "AND i.tipo = :tipo " +
                  "AND i.ativo = true";

    try {
        return em.createQuery(jpql, Investment.class)
                .setParameter("cliente", cliente)
                .setParameter("tipo", tipo)
                .getSingleResult();

    } catch (NoResultException e) {
        return null;
    }
}

public void atualizarRendimento(Investment investimento) {

    investimento.setValorRendido(investimento.calcularValorAtual());

    abrir();
    em.merge(investimento);
    fechar();
}

}
