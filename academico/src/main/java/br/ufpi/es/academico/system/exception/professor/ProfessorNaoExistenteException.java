package br.ufpi.es.academico.system.exception.professor;

@SuppressWarnings("serial")
public class ProfessorNaoExistenteException extends Exception{
	
	public ProfessorNaoExistenteException(){
		super("Nao existe professor com esse dado.");
	}
}
