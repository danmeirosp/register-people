/*ALUNO: Daniel Medeiros de Proença
  RA: 1914472
*/

import javax.swing.JOptionPane;						

public class RgException extends Exception{
	
	public RgException(){
		JOptionPane.showMessageDialog(null, "O RG deve ser maior que 10 e menor que 50!!","ATENCAO",JOptionPane.PLAIN_MESSAGE);
	}
}