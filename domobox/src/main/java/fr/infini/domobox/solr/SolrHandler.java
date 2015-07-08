package fr.infini.domobox.solr;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

import fr.infini.domobox.solr.mapper.Mapper;
import fr.infini.domobox.view.SolrFacetedView;
import fr.infini.domobox.view.SolrFacetedView.FacetedItemViewResult;
import fr.infini.domobox.view.SolrFacetedView.FacetedViewResult;

public enum SolrHandler {

	INSTANCE;
	
	private final SolrClient solr;
	
	private SolrHandler() {
		solr = new HttpSolrClient("http://192.168.1.22:8983/solr/capteur");
	}
	
	public void search(SolrQuery query, Mapper<SolrDocument, ?> mapper) throws SolrServerException, IOException {
		QueryResponse response = solr.query(query);
		
		SolrDocumentList results = response.getResults();
		
		for(SolrDocument objet: results){
			mapper.map(objet);
		}
	}
	
	public SolrFacetedView searchWithFacets(SolrQuery query, Mapper<SolrDocument, ?> mapper) throws SolrServerException, IOException {
		QueryResponse response = solr.query(query);
		
		SolrFacetedView toReturn = new SolrFacetedView();
		//FacetedViewResult facetedView = toReturn.new FacetedViewResult();
		
		//toReturn.setFacetedViewResult(facetedView);
		
		List<FacetField> facets = response.getFacetFields();
		
		for(FacetField facet : facets){
//			FacetedViewResult facetedView = toReturn.new FacetedViewResult();
//			
//			facetedView.setNom(facet.getName());
			
			FacetedItemViewResult itemView = toReturn.new FacetedItemViewResult();
			itemView.setNom(facet.getName());
			
			for(Count count : facet.getValues()){
				FacetedViewResult facetedViewResult = toReturn.new FacetedViewResult();
				facetedViewResult.setNom(count.getName());
				facetedViewResult.setNombre((int) count.getCount());
				
				itemView.add(facetedViewResult);
			}
			
			toReturn.add(itemView);
			
		}
		
		SolrDocumentList results = response.getResults();
		
		for(SolrDocument objet: results){
			toReturn.add(mapper.map(objet));
		}
		
		//System.out.println(toReturn);
		
		return toReturn;
	}
	
	public void addDocument(SolrInputDocument document) throws SolrServerException, IOException{
		solr.add(document);
		solr.commit();
	}
}
