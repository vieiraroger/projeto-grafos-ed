package com.gohorse.database.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

public class StoreData<T> {
	private static String FILE_EXTENSION = ".DAT";
	private static String FILE_URI = "C:\\Users\\comp15\\";

	private final Class<T> type;

	public StoreData(Class<T> type) {
		this.type = type;
	}

	public void serialize(Map<Integer, T> obj) {
		ObjectOutputStream oos = null;
		FileOutputStream fout = null;
		String uri = createUri(FILE_URI, type.getSimpleName().toUpperCase(), FILE_EXTENSION);
		try {
			fout = new FileOutputStream(uri, false);
			oos = new ObjectOutputStream(fout);
			oos.writeObject(obj);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@SuppressWarnings("unchecked")
	public Map<Integer, T> deserialize() {
		Map<Integer, T> mapObj = null;
		ObjectInputStream objectinputstream = null;
		String uri = createUri(FILE_URI, type.getSimpleName().toUpperCase(), FILE_EXTENSION);
		try {
			if (!checkIfFileExists(uri)) {
				return null;
			}
			FileInputStream streamIn = new FileInputStream(uri);
			objectinputstream = new ObjectInputStream(streamIn);
			mapObj = (Map<Integer, T>) objectinputstream.readObject();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (objectinputstream != null) {
				try {
					objectinputstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mapObj;
	}

	private Boolean checkIfFileExists(String uri) throws FileNotFoundException, Exception {
		File fw = new File(uri);
		if (!fw.exists()) {
			return false;
		}
		return true;
	}

	private String createUri(String uri, String fileName, String extension) {
		return uri + fileName + extension;
	}
}
