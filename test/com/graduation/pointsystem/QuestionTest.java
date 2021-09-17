package com.graduation.pointsystem;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.graduation.utils.Grade;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

public class QuestionTest {
    Question question = new Question();
    List<QuestionDetail> questionDetails;

    @Test
    public void testGetQuestions_shouldReturnQuestionDetailList_whenCorrectGradeAndLevelPassed()
            throws InterruptedException, ExecutionException, JsonProcessingException {
        questionDetails = question.getQuestions("maths", Grade.FRESHMAN);
        assertFalse(questionDetails.isEmpty());
    }

    @Test
    public void testGetQuestions_shouldReturnFiveQuestionDetailList_whenCorrectGradeAndLevelPassed()
            throws InterruptedException, ExecutionException, JsonProcessingException {
        questionDetails = question.getQuestions("maths", Grade.FRESHMAN);
        assertEquals(5, questionDetails.size());
    }


    @Test(expected = NullPointerException.class)
    public void testGetQuestions_shouldReturnNullPointerException_whenNullGradePassed()
            throws InterruptedException, ExecutionException, JsonProcessingException {
        questionDetails = question.getQuestions("", null);
    }


    @Test
    public void testGenerateQuestions_shouldReturnTrue_whenEmptyQuestionTypePassed() {
        //assertEquals(-1, question.generateQuestions("", Grade.FRESHMAN));
    }

}