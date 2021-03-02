package integracion.cliente;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import integracion.cliente.altaCliente.TestIntegracionAltaCliente;
import integracion.cliente.bajaCliente.TestIntegracionBajaCliente;
import integracion.cliente.listarClientes.TestIntegracionListarClientes;
import integracion.cliente.modificarCliente.TestIntegracionModificarCliente;
import integracion.cliente.mostrarCliente.TestIntegracionMostrarCliente;


@RunWith(Suite.class)
@SuiteClasses( { TestIntegracionAltaCliente.class, TestIntegracionBajaCliente.class,
					TestIntegracionListarClientes.class, TestIntegracionModificarCliente.class,
						TestIntegracionMostrarCliente.class })
public class SuiteTestIntegracionCliente {}
