package fr.infini.domobox.view;

import java.util.ArrayList;
import java.util.List;

import lombok.Delegate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SolrFacetedView {

	private interface SimpleObjectCollection{
		boolean add(Object object);
	}
	
	private interface FacetedViewResultCollection {
		boolean add(FacetedViewResult object);
	}
	
	private interface SimpleFacetedItemCollection{
		boolean add(FacetedItemViewResult object);
	}
	
//	@Delegate(types=FacetedViewResultCollection.class)
//	private List<FacetedViewResult> facetedViewResult = new ArrayList<FacetedViewResult>();
	
	@Delegate(types=SimpleObjectCollection.class)
	private List<Object> results = new ArrayList<Object>();
	
	@Delegate(types=SimpleFacetedItemCollection.class)
	private List<FacetedItemViewResult> items = new ArrayList<FacetedItemViewResult>();
	
	
	@ToString
	public class FacetedItemViewResult {
		
		@Getter
		@Setter
		private String nom;
		
		private int nombre;
		
		@Getter
		@Setter
		@Delegate(types=FacetedViewResultCollection.class)
		private List<FacetedViewResult> facetedItemViewResults = new ArrayList<FacetedViewResult>();
		
		public FacetedItemViewResult(){
		}
		
		public int getNombre(){
			for(FacetedViewResult facetedItemViewResult : facetedItemViewResults){
				nombre += facetedItemViewResult.nombre;
			}
			
			return nombre;
		}
	}
	
	@Getter
	@Setter
	@ToString
	public class FacetedViewResult {
		
		private String nom;
		
		private int nombre;
		
	}
	
}
