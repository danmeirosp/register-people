/*ALUNO: Daniel Medeiros de Proença
  RA: 1914472
*/

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;							
import javax.swing.JLabel;							
import javax.swing.JTextField;						
import javax.swing.JButton;							
import javax.swing.JOptionPane;						

public class Principal implements ActionListener{
	
	//janela
	private static JFrame janela = new JFrame();	
	
	//rotulos
	private static JLabel rotrg = new JLabel();		
	private static JLabel rotnome = new JLabel();
	private static JLabel rotrua = new JLabel();
	private static JLabel rotnumero = new JLabel();
	
	//caixas
	private static JTextField cxrg = new JTextField();
	private static JTextField cxnome = new JTextField();
	private static JTextField cxrua = new JTextField();
	private static JTextField cxnumero = new JTextField();
	
	//botões
	private static JButton botinserir = new JButton();	   
	private static JButton botconsular = new JButton();	   
	private static JButton botalterar = new JButton();     
	private static JButton botdeletar = new JButton();
	private static JButton botlimpar = new JButton();
	private static JButton botsair = new JButton();
	private static JOptionPane jop = new JOptionPane();
	
	private static Principal prin = new Principal(); 
	private BDpes bd = new BDpes();
	Pessoa x, y, z;
	Pessoa p = new Pessoa();
	int cont = 0;
	
	public static void main(String args[]){
			
		int largura = 400, altura = 300;
		
		janela.setDefaultCloseOperation(janela.DISPOSE_ON_CLOSE);
			
		janela.setSize(largura, altura); 
		
		janela.setTitle("Cadastro de Pessoas"); 
		
		//rotulos
		rotrg.setText("RG: ");
		rotnome.setText("NOME: ");
		rotrua.setText("RUA: ");
		rotnumero.setText("N: ");
		
		//caixas
		cxrg.setText("");
		cxrg.setColumns(10); 
		cxnome.setText("");
		cxnome.setColumns(15);
		cxrua.setText("");
		cxrua.setColumns(15);
		cxnumero.setText("");
		cxnumero.setColumns(12);
		
		//botoes
		botinserir.setText("Inserir");
		botconsular.setText("Consultar");
		botalterar.setText("Alterar");
		botdeletar.setText("Deletar");
		botlimpar.setText("Limpar");
		botsair.setText("Sair");
		
		//adicionando na janela
		janela.add(rotrg);
		janela.add(cxrg);
		janela.add(rotnome);
		janela.add(cxnome);
		janela.add(rotrua);
		janela.add(cxrua);
		janela.add(rotnumero);
		janela.add(cxnumero);
		janela.add(botinserir);
		janela.add(botconsular);
		janela.add(botalterar);
		janela.add(botdeletar);
		janela.add(botlimpar);
		janela.add(botsair);
		
		janela.setLayout(new FlowLayout());
		
		//tornando visivel a interface
		janela.setVisible(true);

		//adicionar botoes ActionListener
		botinserir.addActionListener(prin);
		botconsular.addActionListener(prin);
		botalterar.addActionListener(prin);
		botdeletar.addActionListener(prin);
		botlimpar.addActionListener(prin);
		botsair.addActionListener(prin);
	}
	
	public void actionPerformed(ActionEvent ae){
		 int op;
		 int sob = 2;
		 
		//Inserir
		if(ae.getSource().equals(botinserir)){
			try{
                p = new Pessoa();
				p.setRg(Integer.parseInt(cxrg.getText()));
				p.setNome(cxnome.getText());
				p.getEndereco().setRua(cxrua.getText());
				p.getEndereco().setNumero(Integer.parseInt(cxnumero.getText()));
			
				x = bd.insPes(p);
				cont++;
                JOptionPane.showMessageDialog(null, "Pessoa inserida com sucesso!!", "Sucesso", JOptionPane.PLAIN_MESSAGE);
				
			}
			
			catch(RgException rge){
				cxrg.requestFocus();
			}
			
			catch(ExistRgException erg){
				sob = JOptionPane.showConfirmDialog(null, "Deseja sobrepo-lo?", "Sobrepor", jop.YES_NO_OPTION);
			}
            
			catch(ArrayIndexOutOfBoundsException e){
            JOptionPane.showMessageDialog(null, "Impossivel inserir mais pessoas!!", "Atencao", JOptionPane.PLAIN_MESSAGE);
            }
			
			if(sob == 0){
				x = bd.consPesRg(x);
				x.setNome(cxnome.getText());
				x.getEndereco().setNumero(Integer.parseInt(cxnumero.getText()));
				x.getEndereco().setRua(cxrua.getText());
				JOptionPane.showMessageDialog(null, "Pessoa inserida com sucesso!!", "Sucesso", JOptionPane.PLAIN_MESSAGE); //mostrar informa??es

			}
			
			if(sob == 1){
				cxrg.setText("");
				cxnome.setText("");
				cxrua.setText("");
				cxnumero.setText("");
				cxrg.requestFocus();
			}		
		}
		
		//Consular
		if(ae.getSource().equals(botconsular)){
            try{
				int j;
				String st1 = cxrg.getText();
				String st2;
				p = new Pessoa();

				for(j = 0; j < cont; j++){
					st2 = Integer.toString(bd.vetPes[j].getRg());
					if(st1.equals(st2)){
						p = bd.vetPes[j];
					}
				}
				
				x = bd.consPesRg(p);
				cxnome.setText(x.getNome());
				cxrua.setText(x.getEndereco().getRua());
				cxnumero.setText(Integer.toString(x.getEndereco().getNumero()));		
			}
		
			catch (NullPointerException e){
				JOptionPane.showMessageDialog(null, "Nao ha pessoas com esse RG", "Atencao", JOptionPane.PLAIN_MESSAGE);
			}		
		}
		
		//Alterar
		if(ae.getSource().equals(botalterar)){
				x = bd.altPes(p);
				
				if(x != null){
					cxnome.setText(x.getNome());
					cxrua.setText(x.getEndereco().getRua());
					cxnumero.setText(Integer.toString(x.getEndereco().getNumero()));		
					
					op = JOptionPane.showConfirmDialog(null, "Deseja altera-los?", "Alterar", jop.YES_NO_OPTION);
				
					if(op == 0){
						cxrg.requestFocus();
						
						int j;
						String st1 = cxrg.getText();
						String st2;

						for(j = 0; j < cont; j++){
							st2 = Integer.toString(bd.vetPes[j].getRg());
							if(st1.equals(st2)){
								p = bd.vetPes[j];
							}
						}
				
						y = bd.consPesRg(p);
						cxnome.setText(y.getNome());
						cxrua.setText(y.getEndereco().getRua());
						cxnumero.setText(Integer.toString(y.getEndereco().getNumero()));
						
					}
				
					if(op == 1){
						cxrg.setText("");
						cxnome.setText("");
						cxrua.setText("");
						cxnumero.setText("");
						cxrg.requestFocus();
					}
				}
				
				else{
					JOptionPane.showMessageDialog(null, "Nao ha pessoas com esse RG", "Atencao", JOptionPane.PLAIN_MESSAGE);
					cxrg.setText("");
					cxnome.setText("");
					cxrua.setText("");
					cxnumero.setText("");
					cxrg.requestFocus();
				}	
		}
		
		//Deletar
		if(ae.getSource().equals(botdeletar)){
			x = bd.consPesRg(p);
			
			if(x != null){
				cxnome.setText(x.getNome());
				cxrua.setText(x.getEndereco().getRua());
				cxnumero.setText(Integer.toString(x.getEndereco().getNumero()));		
					
				op = JOptionPane.showConfirmDialog(null, "Deseja deleta-los?", "Deletar", jop.YES_NO_OPTION);
				
				if(op == 0){		
					bd.delPes(x);
					
					cxrg.setText("");
					cxnome.setText("");
					cxrua.setText("");
					cxnumero.setText("");
					cxrg.requestFocus();
				}
				
				if(op == 1){
					cxrg.setText("");
					cxnome.setText("");
					cxrua.setText("");
					cxnumero.setText("");
					cxrg.requestFocus();
				}
			}	
			else{
				JOptionPane.showMessageDialog(null, "Nao existe pessoa com esse RG", "Atencao", JOptionPane.PLAIN_MESSAGE);
				cxrg.setText("");
				cxnome.setText("");
				cxrua.setText("");
				cxnumero.setText("");
				cxrg.requestFocus();
			}	
		}
		
		//Limpar
		if(ae.getSource().equals(botlimpar)){
			cxrg.setText("");
			cxnome.setText("");
			cxrua.setText("");
			cxnumero.setText("");
			cxrg.requestFocus();
		}
		
		//Sair
		if(ae.getSource().equals(botsair)){
			op = jop.showConfirmDialog(null, "Deseja sair?", "Sair", jop.YES_NO_OPTION);
			if(op == 0){
				janela.dispose();
			}
		}
	}
}