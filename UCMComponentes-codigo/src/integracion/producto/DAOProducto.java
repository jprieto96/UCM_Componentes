package integracion.producto;

import java.util.List;

import negocio.producto.TProducto;

public interface DAOProducto {

	public int create(TProducto producto);

	public boolean delete(int id);

	public TProducto readById(int id);

	public List<TProducto> readAll();

	public boolean update(TProducto tProducto);

	public int productosPorProveedor(int id);

}
