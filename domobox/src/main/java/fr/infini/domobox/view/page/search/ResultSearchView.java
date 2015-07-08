package fr.infini.domobox.view.page.search;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import lombok.Getter;
import lombok.Setter;
import fr.infini.domobox.view.SolrFacetedView;

@ManagedBean(name="searchResult")
@SessionScoped
@Getter
@Setter
public class ResultSearchView {

	private SolrFacetedView result;
	
}
