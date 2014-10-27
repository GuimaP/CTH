package model;

import java.io.File;

public interface ICrypt<T> {
	//
	void encrypt(T value,String nameFolder) throws Exception;
	
	T unCrypt(String nameFolder);
}
