package br.ufpi.es.academico.system.exception.turma;

@SuppressWarnings("serial")
public class TurmaNaoExistenteException extends Exception{
	
	public TurmaNaoExistenteException(){
		super("Essa turma nao existe!");
	}
}
