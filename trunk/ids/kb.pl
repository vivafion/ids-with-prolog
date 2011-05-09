/* KB DEFINITIVA */
/*gestire RST!*/

/* connessione tcp */
/* pacchetti:

   CLIENT: syn, seq = x
   SERVER: syn, seq = y,ack= x + 1,

   CLIENT: seq = x + 1,ack = y + 1
   se porta chiusa
   CLIENT: rst,seq = x + 1,ack = y + 1
   
*/



/* pacchetto(source_port,dest_port,flag,source_ip,dest_ip,seq,ack)*/
/* pacchetto(source_port,dest_port,flag,source_ip,dest_ip,seq,ack,rst)*/


%:-dynamic pacchetto(6).


% pacchetto(25,25,syn,host1,host2,10,0).
% pacchetto(25,25,syn,host2,host1,20,11).

% pacchetto(26,26,syn,host1,host2,10,0).
% pacchetto(26,26,syn,host2,host1,20,11).



% pacchetto(25,25,host1,host2,11,21).
% pacchetto(26,26,host1,host2,11,21).
 


% pacchetto(27,27,syn,host1,host2,10,0).
% pacchetto(27,27,syn,host2,host1,11,20).
% pacchetto(27,27,host1,host2,21,11).






connessione_tcp(SOURCE,DESTINATION,SD,DP):-
	pacchetto(SD,DP,syn,SOURCE,DESTINATION,X,0)% syn, seq = x
	,pacchetto(DP,SD,syn,DESTINATION,SOURCE,Y,Z)%seq = y,ack= x + 1,
	,pacchetto(SD,DP,SOURCE,DESTINATION,Z,W)%seq = x + 1,ack = y + 1
	,Z is X+1,W is Y+1.



porta_chiusa(SOURCE,DESTINATION,SD,DP):-
	pacchetto(SD,DP,syn,SOURCE,DESTINATION,X,0)% syn, seq = x
	%,pacchetto(DP,SD,syn,DESTINATION,SOURCE,Y,Z)%seq = y,ack= x + 1,
	,pacchetto(DP,SP,rst,DESTINATION,SOURCE,0,Z)%seq = x + 1,ack = y + 1
	,Z is X+1.%W is Y+1.
	

/*aggiungere connessioni*/
tcp_scan(SOURCE,DESTINATION):-
	connessione_tcp(SOURCE,DESTINATION,A,B),connessione_tcp(SOURCE,DESTINATION,C,D),connessione_tcp(SOURCE,DESTINATION,E,F)
 	,A \== B,A \== C,A \== D,B \== C,C \== E, A \== E,B \== F.


tcp_scan(SOURCE,DESTINATION):-
	porta_chiusa(SOURCE,DESTINATION,A,B),porta_chiusa(SOURCE,DESTINATION,C,D),porta_chiusa(SOURCE,DESTINATION,E,F)
	,A \== B,A \== C,A \== D,B \== C,B \== D,C \== E, D \==F,A \== E,B \== F.

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



/*connessione_tcp(host1,host2,25,25).*/

/*
:- X is 10.

:- X_PLUS_ONE is 11.

:- Y is 20.

:- Y_PLUS_ONE is 21.


	

*/
/*
pacchetto(51283,22,syn,host2,host1,0,0).
pacchetto(22,51283,syn,host1,host2,0,1).

pacchetto(51285,80,syn,host2,host1,0,0).
pacchetto(80,51285,syn,host1,host2,0,1).

*/

% pacchetto(26,26,syn,host1,host2,10,0).
% pacchetto(26,26,syn,host2,host1,11,20).

 


% pacchetto(27,27,syn,host1,host2,10,0).
% pacchetto(27,27,syn,host2,host1,11,20).


% pacchetto(22, 51283, syn, '/192.168.0.4', '/192.168.0.5', '1498510119').
% pacchetto(22, 51283, syn, '/192.168.0.4', '/192.168.0.5', '1498510119').
% pacchetto(3306, 51286, '/192.168.0.4', '/192.168.0.5', '0').
% pacchetto(51286, 3306, syn, '/192.168.0.5', '/192.168.0.4', '1624034330').
% pacchetto(3306, 51286, '/192.168.0.4', '/192.168.0.5', '0').
% pacchetto(51286, 3306, syn, '/192.168.0.5', '/192.168.0.4', '1624034330').
% pacchetto(3306, 51286, '/192.168.0.4', '/192.168.0.5', '0').
% pacchetto(51286, 3306, syn, '/192.168.0.5', '/192.168.0.4', '1624034330').
% pacchetto(3306, 51284, '/192.168.0.4', '/192.168.0.5', '0').
% pacchetto(51284, 3306, syn, '/192.168.0.5', '/192.168.0.4', '1895168320').
% pacchetto(3306, 51284, '/192.168.0.4', '/192.168.0.5', '0').
% pacchetto(51284, 3306, syn, '/192.168.0.5', '/192.168.0.4', '1895168320').
% pacchetto(51285, 80, '/192.168.0.5', '/192.168.0.4', '-468569687').
% pacchetto(51285, 80, '/192.168.0.5', '/192.168.0.4', '-468569687').
% pacchetto(80, 51285, syn, '/192.168.0.4', '/192.168.0.5', '1494340259').
% pacchetto(51285, 80, syn, '/192.168.0.5', '/192.168.0.4', '-468569688').
% pacchetto(3306, 51284, '/192.168.0.4', '/192.168.0.5', '0').
% pacchetto(51284, 3306, syn, '/192.168.0.5', '/192.168.0.4', '1895168320').
% pacchetto(22, 51283, syn, '/192.168.0.4', '/192.168.0.5', '1498510119').
% pacchetto(51283, 22, syn, '/192.168.0.5', '/192.168.0.4', '-339277096').

