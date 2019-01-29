package com.serraimovel.dto;

public abstract class ObjetoDoDominio {

	private long id;

	public ObjetoDoDominio() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ObjetoDoDominio(long id) {
		super();
		this.id = id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
		ObjetoDoDominio other = (ObjetoDoDominio) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
