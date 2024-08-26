package com.lms;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationHandler implements SerializationService{
	@Override
	public void serializeLibrary(Library library, String fileName) {
		try {
			FileOutputStream fout = new FileOutputStream(fileName);
			ObjectOutputStream os = new ObjectOutputStream(fout);
			os.writeObject(library);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Library deserializeLibrary(String fileName) {
		Library lobj = null;
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ObjectInputStream ois = new ObjectInputStream(fis);
			lobj = (Library) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lobj;
	}
}
