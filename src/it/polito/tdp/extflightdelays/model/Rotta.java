package it.polito.tdp.extflightdelays.model;

public class Rotta {
	
	private Airport orig;
	private Airport dest;
	private double peso;
	public Rotta(Airport orig, Airport dest, double peso) {
		super();
		this.orig = orig;
		this.dest = dest;
		this.peso = peso;
	}
	public Airport getOrig() {
		return orig;
	}
	public void setOrig(Airport orig) {
		this.orig = orig;
	}
	public Airport getDest() {
		return dest;
	}
	public void setDest(Airport dest) {
		this.dest = dest;
	}
	public double getPeso() {
		return peso;
	}
	public void setPeso(double peso) {
		this.peso = peso;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dest == null) ? 0 : dest.hashCode());
		result = prime * result + ((orig == null) ? 0 : orig.hashCode());
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
		Rotta other = (Rotta) obj;
		if (dest == null) {
			if (other.dest != null)
				return false;
		} else if (!dest.equals(other.dest))
			return false;
		if (orig == null) {
			if (other.orig != null)
				return false;
		} else if (!orig.equals(other.orig))
			return false;
		return true;
	}
	

}
