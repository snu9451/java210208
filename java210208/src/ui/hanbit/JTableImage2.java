package ui.hanbit;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JTableImage2 extends JFrame {
	private JTable jt = null;
	private JScrollPane jsp = null;
	String imgPath = "src\\ui\\hanbit\\";
	ImageIcon originIcon = null;  
	Image originImg = null; 
	//추출된 Image의 크기를 조절하여 새로운 Image객체 생성
	Image changedImg= null;
	//새로운 Image로 ImageIcon객체를 생성
	ImageIcon Icon = null;
	JLabel		jlb_img	 = new JLabel("이미지");
	ImageIcon imo1 = new ImageIcon(imgPath+"lion11.png");
	ImageIcon imo2 = new ImageIcon(imgPath+"lion22.png");
	ImageIcon imo3 = new ImageIcon(imgPath+"lion33.png");
	ImageIcon imo4 = new ImageIcon(imgPath+"lion44.png");	
	String[] paths = {imgPath+"lion11.png",imgPath+"lion22.png",imgPath+"lion33.png",imgPath+"lion44.png"};
//	static int i;
	
	private Object[][] str = {
			{"김유신", new Integer(4500000), imo1, new Boolean(true)},
			{"이순신", new Integer(80000000), imo2, new Boolean(true)},
			{"홍길동", new Integer(6700000), imo3, new Boolean(false)}
		 };
	private String[] str1 = {"1번", "2번", "3번", "4번"};	

	public void initDisplay() {
		DefaultTableModel dtm = new DefaultTableModel(str,str1);
		jt = new JTable(dtm);
		jt.setRowHeight(300);
		jsp = new JScrollPane(jt);	
		jt.getColumn("1번").setCellRenderer(dcr);
		this.add("Center",jsp);
		this.setSize(500,400);
		this.setVisible(true);
	}
	DefaultTableCellRenderer dcr = new DefaultTableCellRenderer()
	{
		int x = 0;
		public Component getTableCellRendererComponent  // 셀렌더러
		(JTable table, Object value, boolean isSelected, boolean hasFocus
				, int row, int column)
		{
			JLabel jlb 		= new JLabel();
//			if(i<3) {/////////////////
//     		ImageIcon originIcon = new ImageIcon(imgPath+"lion11.png");  
     		ImageIcon originIcon = new ImageIcon(paths[x++]);  
    		//ImageIcon에서 Image를 추출
    		Image originImg = originIcon.getImage(); 
    		//추출된 Image의 크기를 조절하여 새로운 Image객체 생성
    		Image changedImg= originImg.getScaledInstance(300, 400, Image.SCALE_SMOOTH );
    		//새로운 Image로 ImageIcon객체를 생성
    		ImageIcon Icon = new ImageIcon(changedImg);
    	//		image.setImage(new ImageIcon(path+img).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
    	//png는 잘되고 gif는 안된다. 왜지?	
    		jlb.setIcon(Icon);				
//    		i++;
//			}////////////////////
			return jlb;
		}
	};	
	public static void main(String[] args) {
		JTableImage2 jti = new JTableImage2();
		jti.initDisplay();

	}
	public void setImg(String img) { 
		ImageIcon originIcon = new ImageIcon(imgPath+img);  
		//ImageIcon에서 Image를 추출
		Image originImg = originIcon.getImage(); 
		//추출된 Image의 크기를 조절하여 새로운 Image객체 생성
		Image changedImg= originImg.getScaledInstance(117, 108, Image.SCALE_SMOOTH );
		//새로운 Image로 ImageIcon객체를 생성
		ImageIcon Icon = new ImageIcon(changedImg);
	//		image.setImage(new ImageIcon(path+img).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
		jlb_img.setIcon(Icon);
	}
	public void setImg2(String img){
		if(img.length()==0) {
			//JOptionPane.showMessageDialog(ba, "이미지가 없으면....");
			originIcon = new ImageIcon(imgPath+img);  
			//ImageIcon에서 Image를 추출
			originImg = originIcon.getImage(); 
			//추출된 Image의 크기를 조절하여 새로운 Image객체 생성
			changedImg= originImg.getScaledInstance(300, 400, Image.SCALE_SMOOTH );
			//새로운 Image로 ImageIcon객체를 생성
			Icon = new ImageIcon(changedImg);
		//		image.setImage(new ImageIcon(path+img).getImage().getScaledInstance(50,50,Image.SCALE_DEFAULT));
			jlb_img.setIcon(Icon);			
		}	
	}
}