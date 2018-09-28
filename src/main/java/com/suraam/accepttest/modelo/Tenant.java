package com.suraam.accepttest.modelo;

public enum Tenant {

	Chile, Peru, Corporativo, Uruguay, ElSalvado, Mexico, Proteccion;

	public static Tenant getFromAlias(String pais) {
		switch (pais) {
		case "CH":
			return Tenant.Chile;
		case "PE":
			return Tenant.Peru;
		case "CO":
			return Tenant.Corporativo;
		case "UR":
			return Tenant.Uruguay;
		case "ELS":
			return Tenant.ElSalvado;
		case "MX":
			return Tenant.Mexico;
		case "PRT":
			return Tenant.Proteccion;
		default:
			return null;
		}
	}

	public String getAlias() {
		switch (this) {
		case Chile:
			return "ch";
		case Peru:
			return "pe";
		case Corporativo:
			return "co";
		case Uruguay:
			return "ur";
		case ElSalvado:
			return "els";
		case Mexico:
			return "mx";
		case Proteccion:
			return "prt";
		default:
			return null;
		}
	}

}
