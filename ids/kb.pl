

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
	,Z is X+1.


% tcp_scan(X,Y):- porta_chiusa(X,Y,A1,A2),porta_chiusa(X,Y,A3,A4),porta_chiusa(X,Y,A5,A6)

% 	%porte tra loro diverse
% 	,A1\=A2,A3\=A4,A5\=A6,
% 	%porte di destinazione diverse
% 	A2\=A4,A2\=A6,A4\=A6
% 	.


%tcp_scan(X,Y):- connessione_tcp(X,Y,A1,A2),connessione_tcp(X,Y,A3,A4),connessione_tcp(X,Y,A5,A6).
	

/*aggiungere connessioni*/

main:-
	
	write('Prolog ids '), nl,
	%write('X = '),
	%flush_output,	
	%read(X), 
	%write('Y = '),
	%read(Y),
	tcp_scan(X,Y),
	statistics(runtime, [_,T]),
	write('CPU time = '), write(T), write(' msec'), nl.




% pacchetto(51513,9485,syn,'/192.168.0.4','/192.168.0.2',2461977483,0).
% pacchetto(9485,51513,rst,'/192.168.0.2','/192.168.0.4',0,2461977484).
% pacchetto(51513,9485,syn,'/192.168.0.4','/192.168.0.2',2461977483,0).
% pacchetto(9485,51513,rst,'/192.168.0.2','/192.168.0.4',0,2461977484).
% pacchetto(51513,9485,syn,'/192.168.0.4','/192.168.0.2',2461977483,0).
% pacchetto(9485,51513,rst,'/192.168.0.2','/192.168.0.4',0,2461977484).

% pacchetto(51527,5225,syn,'/192.168.0.4','/192.168.0.2',1331330302,0).
% pacchetto(5225,51527,rst,'/192.168.0.2','/192.168.0.4',0,1331330303).

% pacchetto(51513,9486,syn,'/192.168.0.4','/192.168.0.2',2461977483,0).
% pacchetto(9486,51514,rst,'/192.168.0.2','/192.168.0.4',0,2461977484).

/*connessione_tcp(host1,host2,25,25).*/

/*
:- X is 10.

:- X_PLUS_ONE is 11.

:- Y is 20.

:- Y_PLUS_ONE is 21.true_scan


	

*/

% pacchetto(51283,22,syn,host2,host1,0,0).
% pacchetto(22,51283,rst,host1,host2,0,1).

% pacchetto(51285,80,syn,host2,host1,0,0).
% pacchetto(80,51285,rst,host1,host2,0,1).



% pacchetto(26,26,syn,host1,host2,10,0).
% pacchetto(26,26,rst,host2,host1,11,20).

 


% pacchetto(27,27,syn,host1,host2,10,0).
% pacchetto(27,27,syn,host2,host1,11,20).



