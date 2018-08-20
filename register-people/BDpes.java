/*ALUNO: Daniel Medeiros de Proença
  RA: 1914472
*/

import javax.swing.*;

public class BDpes{
		
	Pessoa p = new Pessoa();
	public Pessoa vetPes[] = new Pessoa[5];
	
	public void setPessoa(Pessoa p){
		this.p = p;
	}
	
	public Pessoa getPessoa(){
		return p;
	}
	
	int c=0;
	
	//inserir
	public Pessoa insPes(Pessoa p) throws ExistRgException{
			if(consPesRg(p) == null){
				vetPes[c] = p;
				c++;
				return p;
			}
			else{
				throw new ExistRgException();
			}
	}
	
	//consultar
	public Pessoa consPesRg(Pessoa p){
		for(int i = 0; i < vetPes.length; i++){
			if(vetPes[i] != null){
				if(vetPes[i].getRg() == p.getRg()){
					return vetPes[i];
				}
			}
		}
		return null;
	}
	
	//alterar
	public Pessoa altPes(Pessoa p){
		Pessoa x = consPesRg(p);
		if(x != null){
			return x;
		}
		return null;
	}

	//deletar
	public Pessoa delPes(Pessoa p){
		int aux1 = 0;
		int aux2 = 0;
		
		if(consPesRg(p) != null){
			for(aux1 = 0; aux1 < 5; aux1++){
				if(aux2 == 1){
					vetPes[aux1-1] = vetPes[aux1];
				}
				else if(consPesRg(p).getRg() == vetPes[aux1].getRg()){
					vetPes[aux1] = null;
					aux2 = 1;
				}
			}
		}

		if(aux2 == 1){
			return null;
		}
		
		else{
			return p;
		}
	}
}