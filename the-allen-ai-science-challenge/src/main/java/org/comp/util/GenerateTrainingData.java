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
import org.proj.core.ReadTxtFile;

import core.util.WriteFile;

public class GenerateTrainingData {
	public static List<Domain> document = new ArrayList<Domain>();

	public static void main(String[] args) throws InvalidFormatException, IOException {
		List<String> lines = ReadTxtFile.getString("training-data/training_set.tsv");
		for (String string : lines) {
			String[] lineArr = string.split("\t");
			Domain eachLine = new Domain();
			Map<String, String> answers = new HashMap<String, String>();
			
			eachLine.setId(lineArr[0]);
			eachLine.setQuestion(lineArr[1]);
			eachLine.setCorrectAns(lineArr[2]);
			answers.put("A", lineArr[3]);
			answers.put("B", lineArr[4]);
			answers.put("C", lineArr[5]);
			answers.put("D", lineArr[6]);
			
			eachLine.setAnswers(answers);
			
			document.add(eachLine);
		}
		
		for (Domain doc : document) {
			String question = doc.getQuestion();
			String answer = doc.getAnswers().get(doc.getCorrectAns());
			
			Set<String> questionFeatures = ConceptExtractor.getConcept(question);
			Set<String> answerFeatures = ConceptExtractor.getConcept(answer);
			
			for (String qFe : questionFeatures) {
				WriteFile.writeDataWithoutOverwrite("training-data/classifier.train", qFe.replaceAll(" ", "_") + " " + answer);
				System.out.println(qFe.replaceAll(" ", "_") + " " + answer);
			}
			
			for (String aFe : answerFeatures) {
				WriteFile.writeDataWithoutOverwrite("training-data/classifier.train", aFe.replaceAll(" ", "_") + " " + question);
				System.out.println(aFe.replaceAll(" ", "_") + " " + question);
			}
			
			
		}
	}
}
