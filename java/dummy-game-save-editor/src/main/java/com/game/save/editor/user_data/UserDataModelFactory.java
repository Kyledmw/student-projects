package com.game.save.editor.user_data;

import com.game.save.editor.logging.Logger;

/**
 *  Implementation of the Abstract UserDataModelFactory
 *  
 *  
 * @author Kyle Williamson
 *
 */
public class UserDataModelFactory extends AUserDataModelFactory {
	
	/**
	 * Returns a default ModelAccess object
	 */
	public ModelAccess getModelAccess() {
		Logger.info("Getting UserDataModel Accessor");
		UserDataModel model = new UserDataModel();
		return new ModelAccess(model, model);
	}
}
