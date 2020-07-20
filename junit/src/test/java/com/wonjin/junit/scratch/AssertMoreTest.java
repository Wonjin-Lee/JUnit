package com.wonjin.junit.scratch;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AssertMoreTest {
	
	/*
	 * BeforeClass, AfterClass는 테스트를 처음 실행하기 전, 또는 테스트 종료 후에 한 번만 실행된다.
	 * 
	 * AssertMoreTest 클래스를 JUnit으로 실행한 흐름은 다음과 같다.
	 * 
	 * @BeforeClass initializeSomethingReallyExpensive
	 * @Before createAccount
	 * @Test depositIncreasesBalance
	 * @After closeConnections
	 * @Before createAccount
	 * @Test hasPositiveBalance
	 * @After closeConnections
	 * @AfterClass cleanUpSomethingReallyExpensive
	 * 
	 */
	
	@BeforeClass
	public static void initializeSomethingReallyExpensive() {
		
	}
	
	@AfterClass
	public static void cleanUpSomethingReallyExpensive() {
		
	}
	
	@Before
	public void createAccount() {
		
	}
	
	@After
	public void closeConnections() {
		
	}
	
	@Test
	public void depositIncreasesBalance() {
		
	}
	
	@Test
	public void hasPositiveBalance() {
		
	}
}