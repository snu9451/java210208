package ui.hanbit;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

public class MyTableTest implements TableCellRenderer {
//public class MyTableTest {

	String[]			obj	= { "하나", "둘", "셋" };
	DefaultTableModel	dtm	= new DefaultTableModel(new String[10][3], obj);
	JFrame				jf	= new JFrame();
	JTable				jtb	= new JTable(dtm);
	JScrollPane			jsp	= new JScrollPane(jtb);
	ImageIcon			img	= new ImageIcon("src\\ui\\hanbit\\geumja.jpg");
	JLabel				jlb	= null;
	JCheckBox			jcb = new JCheckBox();
	static int i = 0;

	public MyTableTest() {
		jlb = new JLabel();
		System.out.println("" + jtb.getCellRect(2, 1, false).width + "*****" + jtb.getCellRect(2, 1, false).height);
		jtb.setRowHeight(15);
		jtb.getColumn("둘").setWidth(74);
		Image newImg = img.getImage().getScaledInstance(jtb.getCellRect(2, 1, false).width,
				-10, 4);
		img.setImage(newImg);
		jlb.setIcon(img);
		jtb.getColumn("하나").setCellRenderer(this);
		jtb.getColumn("둘").setCellRenderer(this);
		jtb.getColumn("둘").setCellEditor(
//				new DefaultCellEditor((JCheckBox) getTableCellRendererComponent(jtb, true, true, true, 1, 1)));
				new DefaultCellEditor(jcb));
//		jtb.setRowHeight(img.getIconHeight());
		jtb.setRowHeight(100);
//		jtb.setPreferredSize(new Dimension(300,1000));
//		.setSize(new Dimension(img.getIconHeight(), img.getIconWidth()));
//		getTableCellRendererComponent(jtb, true, true, true, 1, 1);
//		System.out.println(jtb.getCellRenderer(1, 1));
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add("Center", jsp);
		jf.setSize(500, 700);
		jf.setVisible(true);
	}

	public static void main(String[] args) {
		new MyTableTest();
	}

//	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		
		Component comp = null;
//		for(int i=0; i<jtb.getRowCount(); i++) {
//			this.getTableCellRendererComponent(jtb, true, false, true, i, 0);
//			this.getTableCellRendererComponent(jtb, true, false, true, i, 1);
//			this.getTableCellRendererComponent(jtb, true, false, true, i, 2);
//			System.out.println("실행");
//		}
//		isSelected = false;
		value = true;
		if (isSelected = true) {
			i++;
			System.out.println("트루"+i);
		};
//		isSelected = false;
		if ((row == 1 || row == 4) && column == 1) {
//			comp = jlb;
//			comp = new JCheckBox();
		}
//		if(dtm.getValueAt(1, 1).getClass().equals(JCheckBox.class)) {
//			ret = (Component)dtm.getValueAt(1, 1);
//		}
		return jlb;
	}

}
