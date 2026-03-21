package io.github.blobst.bankingapp.db_objs;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * stores user information ( i.e. id, username, password, and current balance )
 *
 */

@Getter
public class User {
	private final int id;
	private final String username, password;
	private BigDecimal currentBalance;

	public User(int id, String username, String password, BigDecimal currentBalance) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.currentBalance = currentBalance;
	}

	public void setCurrentBalance(BigDecimal newBalance) {
		currentBalance = newBalance.setScale(2, RoundingMode.FLOOR);
	}
}
