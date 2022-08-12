package br.com.foodstefanini.bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.foodstefanini.dao.DAO;
import br.com.foodstefanini.modelo.Loja;

@ManagedBean
@ViewScoped
public class LojaBean {

	private Loja loja;

	public Loja getLoja() {
		return loja;
	}

	public void setLoja(Loja loja) {
		this.loja = loja;
	}

	public List<Loja> getLojas() {
		return new DAO<Loja>(Loja.class).listaTodos();
	}

	public Loja prepareCreate() {
		this.loja = new Loja();
		initializeEmbeddableKey();
		return loja;
	}

	public void gravar() {

		if (loja.getId() < 1) {
			System.out.println("Gravando loja " + this.loja.getNome());

			new DAO<Loja>(Loja.class).adiciona(this.loja);
		} else {
			System.out.println("Alterando loja " + this.loja.getNome());

			new DAO<Loja>(Loja.class).atualiza(loja);
		}

	}

	public String destroy() {
		new DAO<Loja>(Loja.class).remove(loja);
		this.loja = new Loja();
		return "loja?faces-redirect=true";
	}

	public void confereCnpj(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valorString = value.toString();
		System.out.println(valorString);
		boolean confere = valorString.matches(
				"([0-9]{2}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[\\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\\.]?[0-9]{3}[\\.]?[0-9]{3}[-]?[0-9]{2})\r\n");
		if (!confere) {
			throw new ValidatorException(new FacesMessage("CNPJ invalido."));
		}
	}

	protected void initializeEmbeddableKey() {

	}

}
