package br.ufpi.es.academico.system.exception.turma;

@SuppressWarnings("serial")
public class TurmaSemAlunoException extends Exception{
	public TurmaSemAlunoException() {
		super("Essa turma n�o cont�m alunos.");
	}
}
