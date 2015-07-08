package fr.infini.domobox.view.page.search;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.params.FacetParams;

import fr.infini.domobox.solr.SolrHandler;
import fr.infini.domobox.solr.mapper.CapteurMapper;

@ManagedBean(name="searchCtr")
@SessionScoped
@Getter
@Setter
public class SearchController {

	@ManagedProperty(value="#{search}")
	private SearchView view;
	
	@ManagedProperty(value="#{searchResult}")
	private ResultSearchView resutView;
	
	@SneakyThrows
	public String search(){
		if(view.getValue()==null || view.getValue().trim().isEmpty()){
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Attention", "Vous devez renseigner un élément de recherche."));
			return "search";
		}
		
		//FIXME c'est pas très zolie tout ça.
		SolrQuery query = new SolrQuery();
		query.setQuery("description:*"+view.getValue()+"*");
		query.remove(FacetParams.FACET_FIELD);
		query.addFacetField("fabricant","role");
		CapteurMapper capteurMapper = new CapteurMapper();
		resutView.setResult(SolrHandler.INSTANCE.searchWithFacets(query, capteurMapper));
		
		return "searchResult?faces-redirect=true";
	}
}
