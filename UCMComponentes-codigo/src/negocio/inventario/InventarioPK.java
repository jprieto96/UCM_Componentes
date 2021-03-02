/**
 * 
 */
package negocio.inventario;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class InventarioPK implements Serializable {

	private static final long serialVersionUID = 0;
	
	private int departamentoID;

	private int materialID;

	public InventarioPK() {}

	public InventarioPK(int departamentoID, int materialID) {
		this.departamentoID = departamentoID;
		this.materialID = materialID;
	}

	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof InventarioPK))
			return false;
		InventarioPK pk = (InventarioPK) obj;
		if (!(departamentoID == pk.departamentoID))
			return false;
		if (!(materialID == pk.materialID))
			return false;
		return true;
	}

	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + Objects.hashCode(this.departamentoID);
		hash = 59 * hash + Objects.hashCode(this.materialID);
		return hash;
	}

	public int getDepartamentoID() {
		return departamentoID;
	}

	public void setDepartamentoID(int departamentoID) {
		this.departamentoID = departamentoID;
	}

	public int getMaterialID() {
		return materialID;
	}

	public void setMaterialID(int materialID) {
		this.materialID = materialID;
	}

}