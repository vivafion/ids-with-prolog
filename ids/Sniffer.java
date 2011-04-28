// 1.Import the jpcap library \\

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

import jpcap.*;
import jpcap.packet.Packet;
import jpcap.packet.TCPPacket;
import jpcap.PacketReceiver;
/*import jp.ac.kobe_u.cs.prolog.lang.*;*/
import jpl.*;
import jpl.Integer;




class Sniffer implements PacketReceiver

{

public void receivePacket(Packet packet) {
	
	
	TCPPacket tcp_packet = (TCPPacket)packet;
	System.out.println(tcp_packet);
	Term t1 = new jpl.Integer(tcp_packet.src_port);
    System.out.println(tcp_packet.src_port);
    Term t2 = new jpl.Integer(tcp_packet.dst_port);
    Term t3 = null;
    if (tcp_packet.syn)
    	t3 = new Atom("syn");
    
    	
    
    Term t4 = new Atom(tcp_packet.src_ip.toString());
	System.out.println(tcp_packet.src_ip.toString());
    Term t5 = new Atom(tcp_packet.dst_ip.toString());
    System.out.println(tcp_packet.dst_ip.toString());
    Term t6 = new Atom(new Integer((int)tcp_packet.sequence).toString());
    System.out.println("cazzzo");
    System.out.println(new Integer((int)tcp_packet.sequence).toString());
    
    
    System.out.println(tcp_packet.dst_port);
    Term arg_t[] = { t1,t2,t3,t4,t5,t6};
    Term pair = new Compound( "connessione_tcp", arg_t );
     
     Query assert_query = 
         new Query( 
                   "assert", 
                   pair );
     
     System.out.println("asserisco");
	this.query();
	//asserisco connessione_tcp(host1,host2,25,25).
	/*
	 
	Term t1 = new Atom("host1");
    Term t2 = new Atom("host2");
    Term t3 = new jpl.Integer(25);
    Term t4 = new jpl.Integer(25);
    Term arg_t[] = { t1,t2,t3,t4};
    Term pair = new Compound( "connessione_tcp", arg_t );
     
     Query assert_query = 
         new Query( 
                   "assert", 
                   pair );
     assert_query.oneSolution();
	*/
	

	

}

public void query(){
	System.out.println("eseguo query");
	Term args2[] = { 
			new Atom("192.168.0.4"),
			new Atom("192.168.0.6")
			
		};
		Query query = 
			new Query( 
				"tcp_scan", 
				args2 );
		
		if(query.hasSolution())
			System.out.println("scanning rilevato!");
			
		
}

// 4.The main comes now! \\


public static void main(String[] args) throws java.io.IOException

{
	JPL.init();

	Term consult_arg[] = { 
			new Atom( "kb.pl" ) 
		};
		Query consult_query = 
			new Query( 
				"consult", 
				consult_arg );

		
		boolean consulted = consult_query.query();
		
		if ( !consulted ){
			System.err.println( "Consult failed" );
			System.exit( 1 );
		}
		NetworkInterface[] lists=jpcap.JpcapCaptor.getDeviceList();



		JpcapCaptor jpcap=JpcapCaptor.openDevice(JpcapCaptor.getDeviceList()[1],1000,false,20);
		jpcap.setFilter("tcp", true);
		Sniffer sniffer = new Sniffer();
		//jpcap.processPacket(1000000,sniffer);
		jpcap.loopPacket(-1, sniffer);
		
	

	
				
		System.out.println("inferito");
		jpcap.close();
}


}
