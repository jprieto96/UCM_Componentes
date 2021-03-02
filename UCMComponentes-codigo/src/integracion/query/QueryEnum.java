package integracion.query;

import integracion.query.queries.cliente.*;
import integracion.query.queries.producto.*;

public enum QueryEnum {

	ClienteQueMasGasta(new ClienteQueMasGasta()), ProductoMasVendido(new ProductoMasVendido());

	private Query query;

	private QueryEnum(Query query) {
		this.query = query;
	}

	public Query getQuery() {
		return query;
	}

}
