/*ALUNO: Daniel Medeiros de Proença
  RA: 1914472
*/

public class Pessoa{
	
	private int rg;
	private String nome;
	private Endereco endereco = new Endereco();
	
	public void setRg(int rg) throws RgException{
		if(rg < 50 && rg > 10){
			this.rg = rg;
		}
		else{ 
			throw new RgException();
		}
	}
	
	public int getRg(){
		return rg;
	}
	
	public void setNome(String nome){
		this.nome = nome;
	}
	
	public String getNome(){
		return nome;
	}
	
	public void setEndereco(Endereco endereco){
		this.endereco = endereco;
	}
	
	public Endereco getEndereco(){
		return endereco;
	}
	
}