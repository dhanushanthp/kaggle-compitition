package org.comp.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import opennlp.source.conditional.chunker.ConceptExtractor;
import opennlp.tools.util.InvalidFormatException;

import org.comp.core.Answers;
import org.comp.core.Domain;
import org.comp.test.AnswerGenerator;
import org.proj.core.ReadTxtFile;

import core.util.WriteFile;

public class GenerateResultData {
	public static List<Domain> document = new ArrayList<Domain>();

	public static void main(String[] args) throws InvalidFormatException, IOException {
		List<String> lines = ReadTxtFile.getString("test/validation_set.tsv");
		for (String string : lines) {
			String[] lineArr = string.split("\t");
			Domain eachLine = new Domain();
			List<String> answers = new ArrayList<String>();

			eachLine.setId(lineArr[0]);
			eachLine.setQuestion(lineArr[1]);
			answers.add(lineArr[2]);
			answers.add(lineArr[3]);
			answers.add(lineArr[4]);
			answers.add(lineArr[5]);

			eachLine.setAnswersR(answers);

			document.add(eachLine);
		}

		for (Domain doc : document) {
			String question = doc.getQuestion();
			Set<String> possibleAnswers = AnswerGenerator.getCategories(question);

			List<String> allAnswers = doc.getAnswersR();

			for (String string : allAnswers) {
				Set<String> answerToekns = ConceptExtractor.getConcept(string);

				for (String ans : answerToekns) {
					if (possibleAnswers.contains(ans.replaceAll(" ", "_"))) {
						System.out.println(question);
						System.out.println("--"+string);
					}
				}
			}

		}
	}
}
