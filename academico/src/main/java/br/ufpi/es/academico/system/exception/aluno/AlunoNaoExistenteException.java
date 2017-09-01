package br.ufpi.es.academico.system.exception.aluno;

@SuppressWarnings("serial")
public class AlunoNaoExistenteException extends Exception{
	
	public AlunoNaoExistenteException(){
		super("Esse aluno não existe");
	}
}
