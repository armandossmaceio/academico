package br.ufpi.es.academico.system.exception.professor;

@SuppressWarnings("serial")
public class ProfessorSemTurmaException extends Exception{
	
	public ProfessorSemTurmaException(){
		super("Esse professor n�o leciona em nehuma turma!");
	}
}
