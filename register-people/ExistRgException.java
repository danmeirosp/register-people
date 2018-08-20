/*ALUNO: Daniel Medeiros de Proença
  RA: 1914472
*/

import javax.swing.JOptionPane;						

public class ExistRgException extends Exception{
	
	public ExistRgException(){
		JOptionPane.showMessageDialog(null, "Ja existe uma pessoa com esse RG","ATENCAO",JOptionPane.PLAIN_MESSAGE);
	}
	
}