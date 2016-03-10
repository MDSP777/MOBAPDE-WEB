package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String contents;
	private String title;
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	@ManyToOne
	private Organization org;
	
	protected Post(){}

	public Post(String contents, String title, Date date) {
		super();
		this.contents = contents;
		this.title = title;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public String getContents() {
		return contents;
	}

	public String getTitle() {
		return title;
	}

	public Date getDate() {
		return date;
	}

	public Organization getOrg() {
		return org;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setOrg(Organization org) {
		this.org = org;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", contents=" + contents + ", title=" + title
				+ ", date=" + date + ", org=" + org + "]";
	}
	
	
}
