import java.io.FileInputStream;
import alice.tuprolog.*;

public class Main {


	/* inizializza ambiente JPL */
	public static void init(Prolog engine){
		
/*		JPL.init();

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
*/
	
		//Prolog engine = new Prolog();
		
		try { 
			Theory kb = new Theory(new FileInputStream("/home/p1mps/ids-with-prolog/ids/kb.pl")); 
			engine.setTheory(kb);
		}
		catch (Exception e) {
			e.printStackTrace();
			
			
		}
		finally {}
	}
	
	
	public static void main(String[] args) {

		Prolog engine = new Prolog();
			
		Analyzer analyzer = new Analyzer(engine);
		
		Sniffer sniffer = new Sniffer();
		
		init(engine);
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
		
		//sniffer.readFile("/home/p1mps/ids-with-prolog/ids/true_scan.pcap");
		//analyzer.query();
		//analyzer.initializeKB("/home/p1mps/ids-with-prolog/ids/true_scan.pcap",10);
		analyzer.initializeKB("/home/p1mps/ids-with-prolog/ids/kb.pl",10);
		//sniffer.readFile("/home/p1mps/ids-with-prolog/ids/true_scan.pcap");
		sniffer.start();
		//analyzer.query();
	}

}
