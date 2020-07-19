package com.wonjin.junit.iloveyouboss;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfileTest {

	private Profile profile;
	private BooleanQuestion question;
	private Criteria criteria;
	
	@Before
	public void create() {
		profile = new Profile("Bull Hockey, Inc.");
		question = new BooleanQuestion(1, "Got bonuses?");
		criteria = new Criteria();
	}
	
	@Test
	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		profile.add(new Answer(question, Bool.FALSE));
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));
		
		boolean matches = profile.matches(criteria);
		
		assertFalse(matches);
	}
	
	@Test
	public void matchAnswersTrueForAnyDontCareCriteria() {
		profile.add(new Answer(question, Bool.FALSE));
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));
		
		boolean matches = profile.matches(criteria);
		
		assertTrue(matches);
	}
	
	/*
	 * 여러 테스트 케이스를 하나의 테스트 메서드에 넣는 경우
	 * 테스트를 분리했을 때 실행되는 반복적인 공통 초기화의 부담을 줄일 수 있을지는 모르나
	 * 테스트 이름이 일반적이게 되며 의미를 잃게 된다.
	 * 
	 * 테스트를 분리하게 되면 여러 장점이 있고 그 중 하나는 모든 케이스가 실행되었음을 보장할 수 있다는 점이 있다.
	 */
	@Test
	public void matches() {
		Profile profile = new Profile("Bull Hockey, Inc.");
		Question question = new BooleanQuestion(1, "Got milk?");
		
		// must-match 항목이 맞지 않으면 false
		profile.add(new Answer(question, Bool.FALSE));
		Criteria criteria = new Criteria();
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.MustMatch));
		
		assertFalse(profile.matches(criteria));
		
		// don't care 항목에 대해서는 true
		profile.add(new Answer(question, Bool.FALSE));
		criteria = new Criteria();
		criteria.add(new Criterion(new Answer(question, Bool.TRUE), Weight.DontCare));
		
		assertTrue(profile.matches(criteria));
	}
}
