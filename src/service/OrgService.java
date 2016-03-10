package service;

import org.springframework.stereotype.Repository;

import model.Organization;

@Repository
public class OrgService extends JpaService {
	public void addOrg(Organization o){
		openTransaction();
		try{
			entityManager.persist(o);
		} finally {
			closeTransaction();
		}
	}
}
