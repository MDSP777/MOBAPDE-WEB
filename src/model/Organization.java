package model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Organization {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String email;
	private String location;
	@OneToMany(mappedBy="org", cascade=CascadeType.ALL)
	private Collection<Post> posts;
	
	protected Organization(){}

	public Organization(String name) {
		super();
		this.name = name;
		posts = new ArrayList<Post>();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getEmail() {
		return email;
	}

	public String getLocation() {
		return location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", description="
				+ description + ", email=" + email + ", location=" + location
				+ "]";
	}
	
	public Collection<Post> getPosts(){
		return new ArrayList(posts);
	}
	
	public void addPost(Post p){
		posts.add(p);
		p.setOrg(this);
	}
}
