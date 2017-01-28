package br.com.posweb.serviceagendamento.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FuncoesArquivo {

	private final static String FILE = "settings.properties";

    private Properties props = new Properties();

    private static FuncoesArquivo instance;
    
    public static FuncoesArquivo getInstance(){
    	if(instance == null){
    		instance = new FuncoesArquivo();
    	}
    	return instance;
    }
    
    public String getValorProperties(String key) throws IOException{
    	InputStream in = this.getClass().getClassLoader().getResourceAsStream(FILE); 
    	props.load(in);
    	return props.getProperty(key);
    }
    
}
