package com.init.bar.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.init.bar.dao.BarDAO;
import com.init.bar.model.Bar;

@RestController
@RequestMapping("bar")
public class BarREST {
	
	int[]vectorPrimos;
	StringBuilder vectorB = new StringBuilder();
	StringBuilder vectorC = new StringBuilder();
	String[]vectorA;
	StringBuilder vectorRespuesta = new StringBuilder();

	@Autowired
	private BarDAO barDAO;
	
	@GetMapping("/listar")
	public List<Bar> listar(){
		return barDAO.findAll();
	}
	
	@GetMapping("/vaso/{iq}/{id}")
	public String respuesta(@PathVariable("iq") Integer iq, @PathVariable("id") Integer id){
		
		vectorRespuesta.setLength(0);
		vectorA = barDAO.findById(id).get().getInput_array().split(",");
		numerosPrimos(iq);
		
		for(int primo:vectorPrimos) {
			for(int j=0; j<vectorA.length; j++) {
				if (Integer.parseInt(vectorA[j]) % primo == 0) {
					vectorB.append(vectorA[j]);
				}else {
					vectorC.append(vectorA[j]);
				}
			}
			vectorA = new String[vectorC.length()];
			for (int i = 0; i < vectorC.length(); i++){
				vectorA[i] = Character.toString(vectorC.charAt(i));
			}
			vectorC.setLength(0);
			if(vectorB.length()>0) {
				vectorRespuesta.append(vectorB.reverse());
				vectorB.setLength(0);
			}
			
		}
		for(int j=0; j<vectorA.length; j++) {
			vectorRespuesta.append(vectorA[j]);
		}

		return vectorRespuesta.toString();
	}
	
	public int[] numerosPrimos(int iq) {
		int contador = 0;
		int i=2;
		vectorPrimos = new int[iq];
		while(contador<iq){            
            if(esPrimo(i)){
                vectorPrimos[contador]=i;
                contador++; //incrementamos por cada número primo
            }
            i++; //siguiente número a evaluar si es primo            
        }
		return vectorPrimos;
	}
	
	public static boolean esPrimo(int numero){
        int contador = 2;
        boolean primo=true;
        while ((primo) && (contador!=numero)){
          if (numero % contador == 0)
            primo = false;
          contador++;
        }
        return primo;
    }
}
