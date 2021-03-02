package negocio.departamento;

import java.util.List;

import com.mysql.fabric.xmlrpc.base.Struct;

public interface SADepartamento {

	public int altaDepartamento(TDepartamento departamento);

	public boolean bajaDepartamento(int id);

	public boolean modificarDepartamento(TDepartamento departamento);

	public TDepartamento mostrarDepartamento(int id);

	public List<TDepartamento> listarDepartamentos(boolean activo);
	
	public List<String> calculoNominaDepartamento(int id);
}
