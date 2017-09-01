package br.ufpi.es.academico.view.console;

import java.sql.SQLException;
import java.util.Scanner;

import br.ufpi.es.academico.controller.Fachada;

public class TelaPrincipalConsole {

	public static void main(String args[]) throws SQLException {
		Fachada fachada = new Fachada();
		int opcao;
		Scanner s = new Scanner(System.in);
		while (true) {
			System.out.println("============== Menu Principal =============");
			System.out.println("1 - Aluno");
			System.out.println("2 - Professor");
			System.out.println("3 - Turma");
			System.out.println("4 - Sair");
			System.out.println("===========================================");
			opcao = s.nextInt();
			if (opcao == 4) {
				break;
			}
			switch (opcao) {
			case 1:
				TelaAluno telaAluno = new TelaAluno();
				telaAluno.mostrarTela(opcao, s, fachada);
				break;
			case 2:
				TelaProfessor telaProfessor = new TelaProfessor();
				telaProfessor.mostrarTela(opcao, s, fachada);
				break;
			case 3:
				TelaTurma telaTurma = new TelaTurma();
				telaTurma.mostrarTela(opcao, s, fachada);
				break;
			default:
				System.out.println("Opcao invalida! Tente novamente!");
				break;
			}
		}
	}

}
