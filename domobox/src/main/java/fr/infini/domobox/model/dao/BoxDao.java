package fr.infini.domobox.model.dao;

import java.util.ArrayList;

import fr.infini.domobox.model.dao.implementation.BoxDaoImplementation;
import fr.infini.domobox.model.entite.Box;

public interface BoxDao {

	static BoxDao getInstance(){
		return BoxDaoImplementation.getInstance();
	}
	
	void addBox(Box box);
	
	ArrayList<Box> getAll();
	
	Box get(Long id);
	
	void deleteBox(Box box);
}
