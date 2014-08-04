/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.tools.FileObject;

import Model.Funcionario;

/**
 *
 * @author Guima
 */
public class FuncionarioController {

//    public static void saveInformacao(Funcionario f) {
//
//        try {
//            String path = new File("").getCanonicalFile() + "\\src\\Resources\\FilesTemp\\DadosTemporarioInstrutor.ser";
//            File file = new File(path);
//            FileOutputStream out = new FileOutputStream(path);
//            ObjectOutputStream objOut = new ObjectOutputStream(out);
//
//            objOut.writeObject(f);
//            objOut.close();
//            
//            
//            loadInformacao();
//        } catch (IOException ex) {
//            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }
//
//    public static Funcionario loadInformacao() {
//        Funcionario f = new Funcionario();
//        
//        try {
//            String path = new File("").getCanonicalFile() + "\\src\\Resources\\FilesTemp\\DadosTemporarioInstrutor.ser";
//            FileInputStream in = new FileInputStream(path);
//            ObjectInputStream objIn = new ObjectInputStream(in);
//            f = (Funcionario) objIn.readObject();
//
//        } catch (IOException er) {
//            er.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//        System.out.println("Serializando:" + f.getNome());
//        return f;
//    }
}
