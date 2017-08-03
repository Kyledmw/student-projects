package com.game.save.editor;

import com.game.save.editor.user_data.IUserDataModelController;
import com.game.save.editor.user_data.UserDataModelController;

/**
 * Main application class
 * This application is a save data editor for a Space Invaders game.
 * 
 * <a>https://github.com/Kyledmw/java-space-invaders</a>
 * 
 * @author Kyle Williamson <a>http://kyle.ie/</a>
 *
 */
public class App 
{

	public static void main(String[] args) {
		IUserDataModelController modelController = new UserDataModelController();
		UserDataEditorController viewController = new UserDataEditorController(modelController);
	}
}
