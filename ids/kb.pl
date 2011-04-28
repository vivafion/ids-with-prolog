/* KB DEFINITIVA */


/* connessione tcp */
/* pacchetti:

   CLIENT: syn, seq = x
   SERVER: syn, ack= x + 1, seq = y
   si ferma qua lo scanner! 
   CLIENT: ack = y + 1, seq = x + 1
	
*/



/* pacchetto(source_port,dest_port,flag,source_ip,dest_ip,seq,ack)*/

:- dynamic pacchetto/7.

% pacchetto(25,25,"syn",host1,host2,10,0).
% pacchetto(25,25,"syn",host2,host1,11,20).
% pacchetto(25,25,host1,host2,21,11).


% pacchetto(26,26,"syn",host1,host2,10,0).
% pacchetto(26,26,"syn",host2,host1,11,20).
% pacchetto(26,26,host1,host2,21,11).
 


% pacchetto(27,27,"syn",host1,host2,10,0).
% pacchetto(27,27,"syn",host2,host1,11,20).
% pacchetto(27,27,host1,host2,21,11).



:- dynamic connessione_tcp/4.
/*connessione_tcp(host1,host2,25,25).*/

/*
:- X is 10.

:- X_PLUS_ONE is 11.

:- Y is 20.

:- Y_PLUS_ONE is 21.


	

*/

pacchetto(51283,22,syn,host2,host1,0,0).
pacchetto(22,51283,syn,host1,host2,0,1).

pacchetto(51285,80,syn,host2,host1,0,0).
pacchetto(80,51285,syn,host1,host2,0,1).



% pacchetto(26,26,syn,host1,host2,10,0).
% pacchetto(26,26,syn,host2,host1,11,20).

 


% pacchetto(27,27,syn,host1,host2,10,0).
% pacchetto(27,27,syn,host2,host1,11,20).




connessione_tcp(SOURCE,DESTINATION,SD,DP):-
	pacchetto(SD,DP,syn,SOURCE,DESTINATION,X,0),pacchetto(DP,SD,syn,DESTINATION,SOURCE,_,Y),Y is X+1.


% connessione_tcp(host1,host2,25,25).
% connessione_tcp(host1,host2,26,26).
% connessione_tcp(host1,host2,27,27).
 



/*aggiungere connessioni*/
tcp_scan(SOURCE,DESTINATION):-
	connessione_tcp(SOURCE,DESTINATION,A,B),connessione_tcp(SOURCE,DESTINATION,C,D)
	,A \== B,A \== C,A \== D,B \== C,B \== D.

main:-
	
	write('Prolog ids '), nl,
	write('X = '),
	flush_output,	
	read(X), 
	write('Y = '),
	read(Y),
	tcp_scan(X,Y),
	
	statistics(runtime, [_,T]),
	write('CPU time = '), write(T), write(' msec'), nl.
