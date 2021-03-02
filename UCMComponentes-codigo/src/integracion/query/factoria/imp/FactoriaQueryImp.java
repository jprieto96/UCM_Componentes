/**
 * 
 */
package integracion.query.factoria.imp;

import integracion.query.factoria.FactoriaQuery;
import integracion.query.Query;
import integracion.query.QueryEnum;

public class FactoriaQueryImp extends FactoriaQuery {

	public Query nuevaQuery(QueryEnum nombre) {
		return nombre.getQuery();
	}

}