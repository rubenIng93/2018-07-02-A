package it.polito.tdp.extflightdelays.model;

public class VoliDistanza implements Comparable<VoliDistanza>{
	
	private Airport idPartenza;
	private Airport idArrivo;
	private double distanzaAVG;
	
	public VoliDistanza(Airport idPartenza, Airport idArrivo, Double distanzaAVG) {
		super();
		this.idPartenza = idPartenza;
		this.idArrivo = idArrivo;
		this.distanzaAVG = distanzaAVG;
	}

	public Airport getIdPartenza() {
		return idPartenza;
	}

	public void setIdPartenza(Airport idPartenza) {
		this.idPartenza = idPartenza;
	}

	public Airport getIdArrivo() {
		return idArrivo;
	}

	public void setIdArrivo(Airport idArrivo) {
		this.idArrivo = idArrivo;
	}

	public Double getDistanzaAVG() {
		return distanzaAVG;
	}

	public void setDistanzaAVG(Double distanzaAVG) {
		this.distanzaAVG = distanzaAVG;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArrivo == null) ? 0 : idArrivo.hashCode());
		result = prime * result + ((idPartenza == null) ? 0 : idPartenza.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VoliDistanza other = (VoliDistanza) obj;
		if (idArrivo == null) {
			if (other.idArrivo != null)
				return false;
		} else if (!idArrivo.equals(other.idArrivo))
			return false;
		if (idPartenza == null) {
			if (other.idPartenza != null)
				return false;
		} else if (!idPartenza.equals(other.idPartenza))
			return false;
		return true;
	}

	@Override
	public int compareTo(VoliDistanza altro) {
		return altro.getDistanzaAVG().compareTo(this.distanzaAVG);
	}

	
	
	

}
