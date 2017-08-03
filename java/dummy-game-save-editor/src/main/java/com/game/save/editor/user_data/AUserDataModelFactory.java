package com.game.save.editor.user_data;

/**
 * Abstract Factory for a ModelAccess object
 * This class can be subclasses to allow for custom ModelAccess objects to be returned
 * 
 * @author Kyle Williamson
 *
 */
public abstract class AUserDataModelFactory {

	public abstract ModelAccess getModelAccess();
	
	public class ModelAccess {
		
		private IUserDataModelReader _reader;
		
		private IUserDataModelWriter _writer;
		
		/**
		 * Object that exposes the UserDataModel as a Reader or Writer.
		 * @param reader
		 * @param writer
		 */
		public ModelAccess(IUserDataModelReader reader, IUserDataModelWriter writer) {
			_reader = reader;
			_writer = writer;
		}
		
		public IUserDataModelReader getReader() {
			return _reader;
		}
		
		public IUserDataModelWriter getWriter() {
			return _writer;
		}
		
	}
}
