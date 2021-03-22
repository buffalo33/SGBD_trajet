JVC=javac
JV=java
JFLAGS=-ea
BUILD=build
SRC=src



src:
#	export CLASSPATH=$ORACLE_HOME/jdbc/lib/ojdbc5.jar:.:$CLASSPATH	
	${JVC} *.java
run:
	${JV} Projet
