package fr.infini.domobox.view.page.search;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;

@ManagedBean(name="search")
@SessionScoped
@Getter
@Setter
public class SearchView {

	private String value;

}
