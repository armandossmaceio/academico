package br.ufpi.es.academico.system.exception.turma;

@SuppressWarnings("serial")
public class TurmasNaoCadastradasException extends Exception{
	
	public TurmasNaoCadastradasException(){
		super("Nenhuma turma foi cadastrada ainda!");
	}
}
