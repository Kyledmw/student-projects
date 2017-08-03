package com.game.save.editor.persistence;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.game.save.editor.logging.Logger;
import com.game.save.editor.user_data.IUserDataModelController;
import com.game.save.editor.user_data.IUserDataModelReader;
import com.game.save.editor.user_data.IUserDataModelWriter;

/**
 * A controller for handling the UserData Save file
 * 
 * @author Kyle
 *
 */
public class UserDataFileController implements IFileController {
	
	private static final String FILE_DIR = "saves/userdata.sav";
	
	private IUserDataModelController _modelController;
	
	public UserDataFileController(IUserDataModelController modelController) {
		_modelController = modelController;
	}

	public void save() throws IOException {
		//retrieve model reader
		IUserDataModelReader reader = _modelController.getModelReader();
		//create serializable version of model
		UserDataSer data = new UserDataSer(reader.getUsername(), reader.getScore(), reader.getDifficulty());
		
		//use serialization to write the object to the file
		FileOutputStream fileOut = new FileOutputStream(FILE_DIR);
		ObjectOutputStream out = new ObjectOutputStream(fileOut);
		Logger.info("Writing object to file");
		out.writeObject(data);
		out.close();
		fileOut.close();
		Logger.info("File saved successfully");
	}

	public void open() throws IOException {
		//retrieve the model writer
		IUserDataModelWriter writer = _modelController.getModelWriter();
		
		UserDataSer data = null;
		
		//read file with serialization
		FileInputStream fileIn = new FileInputStream(FILE_DIR);
		ObjectInputStream in = new ObjectInputStream(fileIn);
		try {
			Logger.info("Reading object from file");
			data = (UserDataSer) in.readObject();
		} catch(ClassNotFoundException e) {
			Logger.err(e.toString());
		}
		in.close();
		fileIn.close();
		
		
		//write retrieve data to the model
		Logger.info("Writing data to model");
		writer.clearModel();
		writer.setUsername(data.getUsername());
		writer.setScore(data.getScore());
		writer.setDifficulty(data.getDifficulty());
		
	}
}
