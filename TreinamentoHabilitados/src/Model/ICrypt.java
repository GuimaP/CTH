package Model;

import java.io.File;

public interface ICrypt<T> {
	void encrypt(T value,File dir,String nameFolder);
	
	T unCrypt(File dir,String nameFolder);
}
