package com.wonjin.junit.scratch;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AssertTest {

	class InsufficientFundsException extends RuntimeException {
		public InsufficientFundsException(String message) {
			super(message);
		}

		private static final long serialVersionUID = 1L;
	}

	class Account {
		int balance;
		String name;

		Account(String name) {
			this.name = name;
		}

		void deposit(int dollars) {
			balance += dollars;
		}

		void withdraw(int dollars) {
			if (balance < dollars) {
				throw new InsufficientFundsException("balance only " + balance);
			}
			balance -= dollars;
		}

		public String getName() {
			return name;
		}

		public int getBalance() {
			return balance;
		}

		public boolean hasPositiveBalance() {
			return balance > 0;
		}
	}

	class Customer {
		List<Account> accounts = new ArrayList<>();

		void add(Account account) {
			accounts.add(account);
		}

		Iterator<Account> getAccounts() {
			return accounts.iterator();
		}
	}

	private Account account;

	@Before
	public void createAccount() {
		account = new Account("an account name");
	}

	@Test
	public void hasPositiveBalance() {
		account.deposit(50);
		assertTrue(account.hasPositiveBalance());
	}
	
	@Test
	public void depositIncreasesBalance() {
		int initialBalance = account.getBalance();
		account.deposit(100);
		assertTrue(account.getBalance() > initialBalance);
	}
	
	@Test
	public void has100Dollars() {
		account.deposit(100);
		assertThat(account.getBalance(), equalTo(100));
	}
	
	// Test 애너테이션에 expected 인자를 넣어서 해당 예외가 발생하는지 확인할 수 있음
	@Test(expected=InsufficientFundsException.class)
	public void throwsWhenWithdrawingTooMuch() {
		account.withdraw(100);
	}
	
	@Test
	public void test() {
		assertThat(new String[] {"a", "b", "c"}, equalTo(new String[] {"a", "b", "c"}));
		assertThat(Arrays.asList(new String[] {"a", "b"}), equalTo(Arrays.asList(new String[] {"a", "b"})));
		assertThat(account.getName(), is(equalTo("an account name")));
		
		try {
			account.withdraw(100);
			fail();
		} catch (InsufficientFundsException expected) {
			assertThat(expected.getMessage(), equalTo("balance only 0"));
		}
	}
	
	// 다수의 실패를 다루는 한 가지 해결책은 문제가 있는 테스트에 집중하고 다른 실패 테스트는 주석 처리하는 것이다.
	// JUnit에서는 @Ignore 애너테이션으로 주석 처리보다 더 나은 기능을 제공한다.
	@Test
	@Ignore("don't forget me!")
	public void somethingWeCannotHandleRightNow() {
		
	}
}
