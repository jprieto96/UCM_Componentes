package negocio.cliente;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import negocio.cliente.altaCliente.TestNegocioAltaCliente;
import negocio.cliente.bajaCliente.TestNegocioBajaCliente;
import negocio.cliente.listarClientes.TestNegocioListarClientes;
import negocio.cliente.modificarCliente.TestNegocioModificarCliente;
import negocio.cliente.mostrarCliente.TestNegocioMostrarCliente;


@RunWith(Suite.class)
@SuiteClasses( { TestNegocioAltaCliente.class, TestNegocioBajaCliente.class, TestNegocioListarClientes.class, 
	TestNegocioMostrarCliente.class ,TestNegocioModificarCliente.class })
public class SuiteTestNegocioCliente {}