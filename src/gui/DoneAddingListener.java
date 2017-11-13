package gui;
/*
 * @author Lucian Epure
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import account.Account;
import account.AccountFactory;
import account.EnterpriseAccount;
import bll.AccountBLL;
import bll.CustomerBLL;
import control.Control;
import customer.Customer;
import exceptions.InsertException;
import region.Region;



public class DoneAddingListener implements ActionListener {

	private JTextField firstName;
	private JTextField lastName;
	private JTextField number;
	private JTextField address;
	private JTextField age;
	private JTextField email; 
	private JComboBox<String> type;
	private JComboBox<String> region;
	private JTextField endDate;
	private JTable table;
	private DefaultTableModel model;
	private Control c;
	private AccountBLL accountBll;
	private CustomerBLL customerBll;
	public DoneAddingListener(JComboBox<String> type,  JTextField firstName, JTextField lastName, JTextField number,JTextField address, JTextField age,JTextField email, JComboBox<String> region, JTextField endDate,JTable table)
	{
		
		this.firstName=firstName;
		this.lastName=lastName;
		this.number=number;
		this.address=address;
		this.age=age;
		this.email=email;
		this.type=type;
		this.region=region;
		this.endDate=endDate;
		this.table=table;
		customerBll= new CustomerBLL();
		accountBll=new AccountBLL();
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
        
		
		Customer customer = null;
	
			try {
				customer = customerBll.add(firstName.getText(), lastName.getText(), Integer.parseInt(age.getText()), address.getText(), email.getText());
			} catch (NumberFormatException | InsertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Region r=c.search(this.region.getSelectedItem().toString());
			try {
				accountBll.addAccount((String)type.getSelectedItem(), r, customer, number.getText(), endDate.getText());
			} catch (InsertException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		c=Control.getInstance();
        model= (DefaultTableModel) table.getModel();
        
    	model.addRow(new Object[]{firstName.getText(),lastName.getText(),r,(String)type.getSelectedItem(),"0","0","0.0"}); //change these
  
       
	}


}
