import java.util.Hashtable;
import java.util.Vector;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import alice.tuprolog.*;
import alice.tuprolog.Long;

public class Analyzer extends Thread{ 
	
	private Blackboard blackboard;
	private Prolog engine;
	/* numero pacchetti da leggere */
	//private int n;
	/*lista ip diversi da ip host per cui fare inferenza*/
	
	
	
	//Analyzer(int n) { this.n = n; }
	
	Analyzer(Prolog e) {this.engine = e;}
	
	public void assertPacket(Packet p){
		
		if(p instanceof TCPPacket){
		
		TCPPacket tcp_packet = (TCPPacket)p;
		//System.out.println(tcp_packet);
		Term t1 = new Int(tcp_packet.src_port);
	    Term t2 = new Int(tcp_packet.dst_port);
	    Term t3 = null;
	    Long t7 = null;
	    if (tcp_packet.syn)
	    	t3 = new Struct("syn");
	    
	    if(tcp_packet.rst){
	    	t3 = new Struct("rst");
	    
	    }
	    Term t4 = new Struct(tcp_packet.src_ip.toString());
		
	    Term t5 = new Struct(tcp_packet.dst_ip.toString());
	    
		Long t6 = new Long(tcp_packet.sequence);
	    //Long t6 = new Long(l_t6); already defined on 2p
	    
	    
	    if(tcp_packet.ack){
	    	t7 = new Long(tcp_packet.ack_num);
	    	//t7 = new Integer(l);
	    }
	      
	    else
	    	t7 = new Long(0);
	    
	    Term[] arg_t;
	    
	    if (t3 != null){
	    	Term[] arg_temp = { t1,t2,t3,t4,t5,t6,t7 };
		    arg_t = arg_temp;
	    }
	    else{
		   	Term[] arg_temp = { t1,t2,t4,t5,t6,t7 };
		    arg_t = arg_temp;
	    }
	    
    	Term pair = new Struct( "pacchetto", arg_t );
    	System.out.println(pair);

    	Term assert_query = new Struct("assert",pair );
	     
	     //System.out.println("asserisco");
    	try{
    	SolveInfo solve = engine.solve(assert_query);
    	Term solution = solve.getSolution();
    	}
    	catch(Exception e){}
    	
		}
	}
	

	public void query(){
		System.out.println("eseguo query");
		Term args2[] = { 
				//new Atom("/192.168.0.5"),
				//new Atom("/192.168.0.4"),
				new Var("X"),
				new Var("Y"),
				//new Variable("W"),
				//new Variable("Z"),
			};
			Term query = 
				new Struct(
					//"porta_chiusa",
					"tcp_scan",
					//	"connessione_tcp",
					//	"main"
					args2 
					);
			
	    	try{
	        	SolveInfo solve = engine.solve(query);
	        	Term solution = solve.getSolution();
	        	}
	        catch(Exception e){}
			
			
/*			if(query.hasSolution()){
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
