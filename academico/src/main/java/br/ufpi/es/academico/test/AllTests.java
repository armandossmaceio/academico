package br.ufpi.es.academico.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * chama a execucao de todas as suites de testes
 * @author armandosoaressousa
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ AlunosTest.class })
public class AllTests {

}
