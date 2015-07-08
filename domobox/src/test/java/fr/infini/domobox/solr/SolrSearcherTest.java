package fr.infini.domobox.solr;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.params.FacetParams;
import org.junit.Test;

import fr.infini.domobox.solr.mapper.CapteurMapper;
import fr.infini.domobox.view.SolrFacetedView;

public class SolrSearcherTest {

	@Test
	public void simpleTest() throws SolrServerException, IOException{
//		SolrInputDocument document = new SolrInputDocument();
//		document.addField("cat", "book");
//		document.addField("id", "book-1");
//		document.addField("name", "Another book");
//		//SolrHandler.INSTANCE.addDocument(document);
//		
		SolrQuery query = new SolrQuery();
//		query.setQuery("description:*Gestion*");
		query.setQuery("*:*");
		query.remove(FacetParams.FACET_FIELD);
		query.addFacetField("fabricant","role");
//		query.setFields("nom","description","id","fabricant");
//		query.addFilterQuery("description:+gestion","nom:+chambre");
//		query.setStart(0);
//		query.set("nom", "Cuisine");
//		query.set("defType", "edismax");
		CapteurMapper capteurMapper = new CapteurMapper();
		SolrFacetedView result = SolrHandler.INSTANCE.searchWithFacets(query, capteurMapper);
		
		//for(FacetedViewResult facetedViewResult : result.getFacetedViewResult())
		System.out.println(result);
	}
	
	
	
}
