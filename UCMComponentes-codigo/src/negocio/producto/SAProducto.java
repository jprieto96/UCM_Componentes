package negocio.producto;

import java.util.List;

public interface SAProducto {

	public int altaProducto(TProducto tProducto);

	public boolean bajaProducto(int id);

	public boolean modificarProducto(TProducto tProducto);

	public TProducto mostrarProducto(int id);

	public List<TProducto> listarProductos(boolean activo);

	public TProducto productoMasVendido();

	public int productosPorProveedor(int id);

}
