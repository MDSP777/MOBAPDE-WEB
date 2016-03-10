package service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

@Repository
public class JpaService {

    protected EntityManagerFactory entityManagerFactory;
    protected EntityManager entityManager;
    
    @PersistenceUnit
	 public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
    	this.entityManagerFactory = entityManagerFactory;
    }
	 
	 void openTransaction(){
		 if(entityManager==null || !entityManager.isOpen()){
			 entityManager = entityManagerFactory.createEntityManager();
			 entityManager.getTransaction().begin();
		 }
	 }

	 void closeTransaction(){
		 if(entityManager.isOpen()){
			 entityManager.getTransaction().commit();
			 entityManager.close();
		 }
	 }
}
