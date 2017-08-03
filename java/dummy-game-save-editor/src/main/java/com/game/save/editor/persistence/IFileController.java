package com.game.save.editor.persistence;

import java.io.IOException;

/**
 * Interface to be implemented for classes that work with a file
 * @author Kyle
 *
 */
public interface IFileController {

	/**
	 * Save new/updated data to the file
	 * @throws IOException
	 */
	public void save() throws IOException;
	
	/**
	 * Open and retrieve data from the file
	 * @throws IOException
	 */
	public void open() throws IOException;
	
}
