/**
 * 
 */
package integracion.query.factoria;

import integracion.query.Query;
import integracion.query.QueryEnum;
import integracion.query.factoria.imp.FactoriaQueryImp;

public abstract class FactoriaQuery {

	private static FactoriaQuery instancia;

	public static FactoriaQuery getInstancia() {
		if (instancia == null)
			instancia = new FactoriaQueryImp();
		return instancia;
	}

	public abstract Query nuevaQuery(QueryEnum nombre);
}