package view;

import javax.swing.JOptionPane;
import controller.RedesController;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		controller.RedesController p = new controller.RedesController();
		int op=1;
		String os=System.getProperty("os.name");
		while (op!=0){
			op=Integer.parseInt(JOptionPane.showInputDialog("Selecione a operação desejada:\n1-IP\n2-Ping\n0-Fechar Programa"));
			switch(op){
				case 1:
					p.ip(os);
					break;
				case 2:
					p.ping(os);
					break;
				case 0:
					JOptionPane.showMessageDialog(null, "Finalizando...");
			}
		}
	}

}
