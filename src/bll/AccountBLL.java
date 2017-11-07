package bll;

import account.Account;
import account.AccountFactory;
import customer.Customer;
import dao.AbstractDAO;
import exceptions.InsertException;
import region.Region;

public abstract class AccountBLL<T extends Account> {
	
	private AbstractDAO<T> accountDAO;
	
	public AccountBLL(AbstractDAO<T> abstractDAO){
		this.accountDAO = abstractDAO;
	}
	
	public Integer createAccount(String type, Region region, Customer customer,String number,String endDate) throws InsertException{
		
		Account account = AccountFactory.getAccount(type, 0, region, customer, number, endDate);
		return accountDAO.add((T)account);
	}
	
}