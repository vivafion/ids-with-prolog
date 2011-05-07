package ids;

import jpcap.NetworkInterface;
import jpl.Atom;
import jpl.JPL;
import jpl.Query;
import jpl.Term;


public class Main {


	/* inizializza ambiente JPL */
	public static void init(){
		
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

		
	}
	
	
	public static void main(String[] args) {

			
		Analyzer analyzer = new Analyzer();
		
		Sniffer sniffer = new Sniffer();
		
		init();
		/* non serve +
		Blackboard blackBoard = new Blackboard(); 
		analyzer.setBlackboard(blackBoard);
		sniffer.setBlackboard(blackBoard);
		*/
		sniffer.setAnalyzer(analyzer);
		
		/* cattura pacchettti "live" */
		/*
		System.out.println("avvio sniffer");
		sniffer.start();
		*/
		
		/* legge file pacchetti catturati */
		System.out.println("leggo pacchetti");
		sniffer.readFile("/home/p1mps/ids-with-prolog/ids/true_scan.pcap");
		
		
		
	}

}
