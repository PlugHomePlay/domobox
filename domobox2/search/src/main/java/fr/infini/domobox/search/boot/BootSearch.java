package fr.infini.domobox.search.boot;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;

/***
 * Classe permettant de gérer l'initialisation de l'indexation
 * 
 * @author Primael
 *
 */
public enum BootSearch {

	INSTANCE;

	private final TransportClient client;

	private final static String INDEXNAME = "domobox";
	private BootSearch() {
		// Doit charger les éléments de searchBox.properties
		final Settings settings = ImmutableSettings.settingsBuilder().put("cluster.name", "localtestsearch").build();

		this.client = new TransportClient(settings);

		this.client.addTransportAddress(new InetSocketTransportAddress("localhost", 9300));
	}

	public void indexer(String documentType) throws IOException {
		final IndicesExistsResponse response = this.client.admin().indices().prepareExists(INDEXNAME).execute().actionGet();
		
		//L'index existe déjà
		if(response.isExists()) {
			//Rien à faire on sort simplement
			return;
		}
		
		//Creation de l'index
		final CreateIndexRequestBuilder createIndexRequestBuilder = this.client.admin().indices().prepareCreate(INDEXNAME);
		
		//Gestion du mapping de l'objet
		//FIXME doit on avoir nécessairement un objet générique ou peut-on le spécialiser
		final XContentBuilder mappingBuilder = jsonBuilder()			//
				.startObject()											// {
				.field("type","jdbc")						 			// 	"type" : "jdbc",
				.startObject("jdbc")									// 	"jdbc": {
				.field("url","jdbc:postgresql://localhost/rodolphedev")	//		"url"  		: "jdbc
				.field("user","rodolphedev")							//		"user" 		: "user"
				.field("password","rodolphedev")						//		"password"  : "password"
				.field("sql","select * from " + documentType)				//		"sql"		: "select * from type"
				.endObject()											//	}
				.endObject();											// }
		
		System.out.println(mappingBuilder.string());
		
		createIndexRequestBuilder.addMapping("jdbc", mappingBuilder);
		
		//Mapping done
		createIndexRequestBuilder.execute().actionGet();
	}
	
	public void dropIndex(String indexName){
		final IndicesExistsResponse response = this.client.admin().indices().prepareExists(indexName).execute().actionGet();
		
		//L'index n'existe pas
		if(!response.isExists()) {
			//Rien à faire on sort simplement
			return;
		}
		
		final DeleteIndexRequestBuilder deleteIndexRequestBuilder = this.client.admin().indices().prepareDelete(indexName);
		deleteIndexRequestBuilder.execute().actionGet();
	}
	
	

}
