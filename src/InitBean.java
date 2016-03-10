import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class InitBean {
	public InitBean(){
		try{
			java.util.Properties config = new java.util.Properties();
	        config.put("StrictHostKeyChecking", "no");
	        JSch jsch = new JSch();
	        Session session=jsch.getSession("ec2-user", "ec2-54-254-231-42.ap-southeast-1.compute.amazonaws.com", 22);
	        session.setPassword("gioantonvelez");
	        session.setConfig(config);
	        session.connect();
	        int assigned_port = session.setPortForwardingL(5656, "ec2-54-254-231-42.ap-southeast-1.compute.amazonaws.com", 3306);
	        
	        System.out.println(assigned_port);
		} catch (Exception e){
			
		}
	}
}
