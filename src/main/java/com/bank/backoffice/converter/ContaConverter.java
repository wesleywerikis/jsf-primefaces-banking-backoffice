package com.bank.backoffice.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.bank.backoffice.model.Conta;
import com.bank.backoffice.repository.Contas;

@Named
@RequestScoped
public class ContaConverter implements Converter {

	@Inject
	private Contas contas;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent cmp, String value) {
		if (value == null || value.isEmpty())
			return null;
		return contas.porId(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent cmp, Object obj) {
		if (obj == null)
			return "";
		Conta c = (Conta) obj;
		return c.getId() == null ? "" : c.getId().toString();
	}

}
