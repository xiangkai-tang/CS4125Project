package controller;

/*
 * @author Lucian Epure
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import account.Account;
import control.Control;

public class RemoveAccountListener implements ActionListener {

	private Control c;
	private JTable table;
	private int row;
	private int col;

	public RemoveAccountListener(JTable table, int row, int col) {
		this.table = table;
		this.row = row;
		this.col = col;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you wish to remove it?", null,JOptionPane.YES_NO_OPTION);
		if (result == JOptionPane.YES_OPTION) {
			String id= table.getValueAt(row, col).toString();
			/*************************************************
			 * Andrei if you can add method to delete account from data base
			 */

			((DefaultTableModel) table.getModel()).removeRow(row);
		}
	}

}