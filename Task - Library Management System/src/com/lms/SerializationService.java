package com.lms;

public interface SerializationService {
	
	public void serializeLibrary(Library library, String fileName);
	public Library deserializeLibrary(String fileName);
}
