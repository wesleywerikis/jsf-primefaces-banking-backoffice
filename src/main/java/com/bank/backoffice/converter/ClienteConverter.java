package com.bank.backoffice.converter;

import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import com.bank.backoffice.model.Cliente;
import com.bank.backoffice.repository.Clientes;

@Named
@RequestScoped
public class ClienteConverter implements Converter {

	@Inject
	private Clientes clientes;

	@Override
	public Object getAsObject(FacesContext ctx, UIComponent cmp, String value) {
		if (value == null || value.isEmpty())
			return null;
		return clientes.porId(Long.valueOf(value));
	}

	@Override
	public String getAsString(FacesContext ctx, UIComponent cmp, Object obj) {
		if (obj == null)
			return "";
		Cliente c = (Cliente) obj;
		return c.getId() == null ? "" : c.getId().toString();
	}

}
