package fr.infini.domobox.search.boot;

import java.io.IOException;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

public class SolrjPopulator {

	public static void main(String[] args) throws SolrServerException, IOException {
		
		System.out.println(UUID.randomUUID());
		HttpSolrClient client = new HttpSolrClient("http://127.0.0.1:8983/solr/domobox");
		
		for (int i = 0 ; i < 1000 ; i++){
			SolrInputDocument document = new SolrInputDocument();
			document.addField("cat", "book");
			document.addField("id", "book-" + i);
			document.addField("name", "The legend of the hobbit part " + i);
			client.add(document);
			if(i % 100 == 0) client.commit();
		}
		
		client.commit();
	}
}
