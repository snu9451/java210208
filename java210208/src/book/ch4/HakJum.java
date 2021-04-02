package book.ch4;

import javax.swing.JOptionPane;

public class HakJum {

	public static void main(String[] args) {
		String	s_jumsu	= JOptionPane.showInputDialog("점수를 입력하세요");
		int		i_jumsu = Integer.parseInt(s_jumsu);
		System.out.println("당신이 입력한 점수는 "+s_jumsu+"입니다.");
		System.out.printf("당신이 입력한 점수는 %s %n 입니다.",s_jumsu);
		
	}

}
