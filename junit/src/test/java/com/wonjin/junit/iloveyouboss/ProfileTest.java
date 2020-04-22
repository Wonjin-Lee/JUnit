package com.wonjin.junit.iloveyouboss;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfileTest {

	@Test
	public void matchAnswersFalseWhenMustMatchCriteriaNotMet() {
		Profile profile = new Profile("Bull Hockey, Inc.");
		Question question = new BooleanQuestion(1, "Got bonuses?");
		Answer profileAnswer = new Answer(question, Bool.FALSE);
		profile.add(profileAnswer);
		Criteria criteria = new Criteria();
		Answer criteriaAnswer = new Answer(question, Bool.TRUE);
		Criterion criterion = new Criterion(criteriaAnswer, Weight.MustMatch);
		
		criteria.add(criterion);
		
		boolean matches = profile.matches(criteria);
		
		assertFalse(matches);
	}
}
