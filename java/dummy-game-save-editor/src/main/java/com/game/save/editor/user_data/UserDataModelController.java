package com.game.save.editor.user_data;
import java.util.Observable;
import java.util.Observer;

import com.game.save.editor.logging.Logger;
import com.game.save.editor.user_data.AUserDataModelFactory.ModelAccess;

/**
 * Controller for the UserDataModel
 * Observers the UserDataModel & exposes its reader and writer.-
 * 
 * @author Kyle Williamson
 *
 */
public class UserDataModelController extends Observable implements Observer, IUserDataModelController {
	
	private IUserDataModelReader _reader;
	
	private IUserDataModelWriter _writer;
	
	public UserDataModelController() {
		ModelAccess modelAccess = new UserDataModelFactory().getModelAccess();
		
		_reader = modelAccess.getReader();
		_writer = modelAccess.getWriter();
		_writer.addObserver(this);
	}
	
	/*
	 * #### NOTE: #### 
	 * 
	 * This object notifies its oberservers of the update that it has received from the UserDataModel
	 * The reason that there is this additional layer between the model and 
	 * the object wanting to observe its changes is due to preventing the Model of being exposed in multiple areas of the application.
	 * This helps keep the integrity of the model.
	 * 
	 * 
	 * It also keeps the application more modular as it is does not care about the specific implementation of the ModelData Interface it is receiving
	 * 
	 * The controller acts as a method of requesting the needed interface for accessing the model.
	 * 
	 */
	public void update(Observable arg0, Object arg1) {
		Logger.info("In Controller update");
		
		setChanged();
		notifyObservers(arg1);
		clearChanged();
	}


	public IUserDataModelReader getModelReader() {
		return _reader;
	}


	public IUserDataModelWriter getModelWriter() {
		return _writer;
	}

}
