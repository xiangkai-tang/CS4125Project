package control;
/*
 * @author Xiangkai Tang
 */
import account.Account;
import account.FamilyAccount;
import account.EnterpriseAccount;
import bll.AccountBLL;

public class NumberControl {

		AccountBLL<?> accountBLL;
		private String id;
		private String numbers;
		
		public NumberControl(String id, String numbers) {
			super();
			this.accountBLL = new AccountBLL();
			this.id = id;
			this.numbers = numbers;
		}
		
		public void addNumber(){   //only for familyAccount
			FamilyAccount account = (FamilyAccount)accountBLL.getAccount(id);
			account.setNumbersString(account.getNumbersString()+"  "+numbers);
			accountBLL.modifyAccount(account);
		}
		
		
}