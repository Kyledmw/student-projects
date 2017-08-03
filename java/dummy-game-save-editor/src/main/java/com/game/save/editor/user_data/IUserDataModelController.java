package com.game.save.editor.user_data;

import java.util.Observer;

/**
 * Interface for all UserDataModelControllers to adhere to.
 * 
 * @author Kyle Williamson
 *
 */
public interface IUserDataModelController {

	public IUserDataModelReader getModelReader();
	
	public IUserDataModelWriter getModelWriter();
	
	/**
	 *  We expose this here to allow for the use of the observerable method through polymorphism
	 * @param observer
	 */
	public void addObserver(Observer observer);
}
