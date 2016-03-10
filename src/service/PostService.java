package service;

import java.util.Collection;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import model.Post;

import org.springframework.stereotype.Repository;

@Repository
public class PostService extends JpaService {
	public void addPost(Post p) {
		openTransaction();
		try{
			entityManager.persist(p);
		} finally {
			closeTransaction();
		}
	}
	
	public void updatePost(Post p){
		openTransaction();
		try{
			entityManager.merge(p);
		} finally {
			closeTransaction();
		}
	}
	
	public Collection<Post> findAll(){
		openTransaction();
		try{
			TypedQuery<Post> q = entityManager.createQuery("SELECT p FROM Post p", Post.class);
			return q.getResultList();
		} finally {
			closeTransaction();
		}
	}
	
	

}
