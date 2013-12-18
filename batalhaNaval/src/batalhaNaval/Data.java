package batalhaNaval;

import java.sql.Date;
import java.text.DateFormat;
import java.util.*;

public class Data {
	
	private int dia;
    private int mes;
    private int ano;

	public Data(final int dia, final int mes, final int ano){
			boolean valido = testaData(dia, mes, ano);
		    if(!valido)
		        System.out.println("\n\nEsta data nao eh valida!");
		    else{
		        this.dia = dia;
		        this.mes = mes;
		        this.ano = ano;
		    }
	}
	
	public boolean testaData(final int dia, final int mes, final int ano){

	    int diasMes[] =  {31,29,31,30,31,30,31,31,30,31,30,31};

	    if(mes <= 0 || mes > 12)
	        return false;
	    else if(dia > diasMes[mes-1] || dia <= 0)
	        return false;
	    else if(ano > 2013)
	        return false;
	    else if( !((ano % 4 == 0 && ano % 100 != 0) || ano % 400 == 0) && mes == 2 && dia == 29)
	        return false;

	    return true;
	}
	
	public void setData(final int dia, final int mes, final int ano){

	    boolean valido = testaData(dia, mes, ano);
	    if(!valido)
	    	System.out.println("\n\nEsta data nao eh valida!");
	    else{
	        this.dia = dia;
	        this.mes = mes;
	        this.ano = ano;
	    }
	}
	
	public String getData(){

	    return (dia + "/" + mes + "/" + ano);
	}
	
	public static String hoje(){
		Locale local = new Locale("pt","BR");  
	    
		Date hoje;  
	    String data;  
	    DateFormat dateFormat;  
	  
	    dateFormat = DateFormat.getDateInstance(DateFormat.DEFAULT, local);  
	    hoje = new Date(System.currentTimeMillis());  
	    data = dateFormat.format(hoje);  
	  
	    return data;
	}
	
	public static long time(){
        return System.currentTimeMillis();
	}
}