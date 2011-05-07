package ids;

import java.util.Hashtable;
import java.util.Vector;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpl.Atom;
import jpl.Compound;
import jpl.Integer;
import jpl.Query;
import jpl.Term;
import jpl.Variable;


public class Analyzer extends Thread{
	
	private Blackboard blackboard;
	/* numero pacchetti da leggere */
	//private int n;
	/*lista ip diversi da ip host per cui fare inferenza*/
	
	
	
	//Analyzer(int n) { this.n = n; }
	
	Analyzer() {}
	
	public void assertPacket(Packet p){
		
		if(p instanceof TCPPacket){
		
		TCPPacket tcp_packet = (TCPPacket)p;
		//System.out.println(tcp_packet);
		Term t1 = new jpl.Integer(tcp_packet.src_port);
	    Term t2 = new jpl.Integer(tcp_packet.dst_port);
	    Term t3 = null;
	    Integer t7 = null;
	    if (tcp_packet.syn)
	    	t3 = new Atom("syn");
	    
	    if(tcp_packet.rst){
	    	t3 = new Atom("rst");
	    
	    }
	    Term t4 = new Atom(tcp_packet.src_ip.toString());
		
	    Term t5 = new Atom(tcp_packet.dst_ip.toString());
	    
		Long l_t6 = new Long(tcp_packet.sequence);
	    Integer t6 = new Integer(l_t6);
	    
	    
	    if(tcp_packet.ack){
	    	Long l = new Long(tcp_packet.ack_num);
	    	t7 = new Integer(l);
	    
	    }
	      
	    else
	    	t7 = new Integer(0);
	    
	    if (t3 != null){
	    	Term arg_t[] = { t1,t2,t3,t4,t5,t6,t7 };
	    	Term pair = new Compound( "pacchetto", arg_t );
	    	System.out.println(pair);
	    
	    	Query assert_query = 
		         new Query( 
		                   "assert", 
		                   pair );
		     
		     //System.out.println("asserisco");
	    	assert_query.oneSolution();
	    }
	    else{
	    	Term arg_t[] = { t1,t2,t4,t5,t6,t7};
	    	Term pair = new Compound( "pacchetto", arg_t );
	    	System.out.println(pair);
	    	Query assert_query = 
		         new Query( 
		                   "assert", 
		                   pair );
		     
		     //System.out.println("asserisco");
	    	assert_query.oneSolution();
	    }
	    
	    
	    
	    
		}
	
		
	}
	

	public void query(){
		System.out.println("eseguo query");
		Term args2[] = { 
				//new Atom("/192.168.0.5"),
				//new Atom("/192.168.0.4"),
				new Variable("X"),
				new Variable("Y"),
				//new Variable("W"),
				//new Variable("Z"),
			};
			Query query = 
				new Query(
					//"porta_chiusa",
					"tcp_scan",
					//	"connessione_tcp",
					//	"main"
					args2 
					);
			
						
			if(query.hasSolution()){
				System.out.println(query.oneSolution());
				System.out.println("scanning rilevato!");
			    System.exit(1);
			}
			/*
			 while (query.hasMoreElements()){
			     Term bound_to_x = (Term)((Hashtable) query.nextElement()).get("X");
			     Term bound_to_y = (Term)((Hashtable) query.nextElement()).get("Y");
			     //Term bound_to_w = (Term)((Hashtable) query.nextElement()).get("W");
			     System.out.println("X="+bound_to_x);
			     System.out.println("Y="+bound_to_y);
			     //System.out.println("W="+bound_to_w);
			     //System.out.println("Z="+bound_to_z);
			 }
			*/
			
	}

	/*
	public void run(){
		
		synchronized (blackboard) {
				
			
		System.out.println("mi hanno svegliato!");
		Vector<Packet> packets = this.blackboard.read(this.blackboard.getPackets().size());
			
		for (int i = 0; i < packets.size(); i++) {
			this.assertPacket(packets.get(i));
			
		}
		blackboard.notifyAll();
		}
		this.query();
		}
	*/	
	


	public Blackboard getBlackboard() {
		return blackboard;
	}


	public void setBlackboard(Blackboard blackboard) {
		this.blackboard = blackboard;
	}
}
