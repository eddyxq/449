%References: John Fisher's and Catuscia Palamidessi's online tutorial and exercises 

%1
mother(name1, name2).
sister(X,Y) :- mother(Z,X), mother(Z,Y), \+(X = Y).
cousin(X,Y) :- mother(Z,X), mother(W,Y), sister(Z,W).
granddaughter(X,Y) :- mother(Z,X), mother(Y,Z).
descendent(X,Y) :- mother(Y,X).
descendent(X,Y) :- mother(Z,X), descendent(Z,Y).

%2
member_new(X,[X|_]).
member_new(X,[_|T]) :- member_new(X,T).

%3
subset_new([],_).
subset_new([H|T],K) :- member_new(H,K), subset_new(T,K).

%4
disjoint([],_).
disjoint([H|T],K) :- \+(member_new(H,K)), disjoint(T,K).

%5
union_new([],[],[]).
union_new(X,[],X).
union_new([],X,X).
union_new([H|T],A,B) :- member_new(H,A), union_new(T,A,B).
union_new([H|T],A,[H|Y]) :- union_new(T,A,Y).

%6
intersection_new([],_,[]).
intersection_new([H|T],K,M) :- \+(member_new(H,K)), intersection_new(T,K,M).
intersection_new([H|T],K,[H|M]) :- member_new(H,K), intersection_new(T,K,M).

%7
difference([],_,[]).
difference([H|T],K,M) :- member_new(H,K), difference(T,K,M).
difference([H|T],K,[H|M]) :- \+(member_new(H,K)), difference(T,K,M).

%8
occurrences(_,[],0).
occurrences(X,[X|T],N) :- occurrences(X,T,M), N is M + 1.
occurrences(X,[Y|T],N) :- \+(X = Y), occurrences(X,T,N).

%9
quicksort([],[]).
quicksort([H|T],Sorted) :- partition(H,T,L,R), quicksort(L,Lsorted), quicksort(R, Rsorted), append(Lsorted,[H|Rsorted],Sorted).
partition(_,[],[],[]).
partition(P,[H|T],[H|L],R) :- H =< P, partition(P,T,L,R).
partition(P,[H|T],L,[H|R]) :- H > P, partition(P,T,L,R).

%10&Additional Challenge
edge(1,2).
edge(1,4).
edge(1,3).
edge(2,3).
edge(2,5).
edge(3,4).
edge(3,5).
edge(4,5).
edge(1,2,1).
edge(1,4,3.5).
edge(1,3,2.5).
edge(2,3,1).
edge(2,5,2.5).
edge(3,4,1).
edge(3,5,2.2).
edge(4,5,1).
connected(X,Y) :- edge(X,Y) ; edge(Y,X).
path(A,B,Path) :- travel(A,B,[A],Q), reverse(Q,Path).
travel(A,B,P,[B|P]) :- connected(A,B).
travel(A,B,Visited,Path) :- connected(A,C), C \== B, \+member(C,Visited), travel(C,B,[C|Visited],Path). 
connected(X,Y,L) :- edge(X,Y,L) ; edge(Y,X,L).
path(A,B,Path,Len) :- travel(A,B,[A],Q,Len), reverse(Q,Path).
travel(A,B,P,[B|P],L) :- connected(A,B,L).
travel(A,B,Visited,Path,L) :- connected(A,C,D), C \== B, \+member(C,Visited), travel(C,B,[C|Visited],Path,L1), L is D+L1.  
shortest(A,B,Path,Length) :- setof([P,L],path(A,B,P,L),Set), Set = [_|_], % fail if empty  
min(Set,[Path,Length]).
min([F|R],M) :- min(R,F,M).
minimum([],M,M).
minimum([[P,L]|R],[_,M],minimum) :- L < M, !, minimum(R,[P,L],minimum). 
minimum([_|R],M,minimum) :- minimum(R,M,minimum).
