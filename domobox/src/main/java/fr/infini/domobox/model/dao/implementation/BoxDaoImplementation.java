package fr.infini.domobox.model.dao.implementation;

import fr.infini.domobox.model.dao.BoxDao;
import fr.infini.domobox.model.entite.Box;
import fr.infini.domobox.utils.dao.GenericDaoImpl;

public class BoxDaoImplementation extends GenericDaoImpl<Box, Long> implements BoxDao {
	
	private BoxDaoImplementation(){
		
	}
	
	private static class BoxDaoHolder {
		private final static BoxDao instance = new BoxDaoImplementation();
	}
	
	public static BoxDao getInstance(){
		return BoxDaoHolder.instance;
	}
	
	public void addBox(Box box){
		this.save(box);
	}
	
	public void deleteBox(Box box){
		this.delete(box);
	}
	
	public void updateBox(Box box){
		
	}
	
	public void getBox(long id){
		this.get(id);
	}
}
