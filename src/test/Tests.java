package test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;

import model.Organization;
import model.Post;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jcraft.jsch.JSchException;

import service.OrgService;
import service.PostService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/ApplicationContext.xml" })
public class Tests {
	@Autowired
	private PostService pService;
	@Autowired
	private OrgService oService;
	
	/**
	 * @throws JSchException
	 */
	@Test
	public void testAddAndRetrieve() throws JSchException {
		Organization lscs = new Organization("La Salle Computer Society");
		lscs.addPost(new Post("Hello World!", "Event 1", Calendar.getInstance().getTime()));
		lscs.addPost(new Post("Hello World!", "Event 2", Calendar.getInstance().getTime()));
		lscs.addPost(new Post("Hello World!", "Event 3", Calendar.getInstance().getTime()));
		
		oService.addOrg(lscs);
		
	}

}
