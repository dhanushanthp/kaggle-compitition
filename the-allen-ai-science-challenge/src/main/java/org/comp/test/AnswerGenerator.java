package org.comp.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import core.util.Config;

public class AnswerGenerator {
	private static InputStream modelInChunker = null;
	private static DoccatModel chunkerModel;

	static {

		try {
			modelInChunker = new FileInputStream(Config.getModelDataPath() + "en-doccat.bin");
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}

		try {
			chunkerModel = new DoccatModel(modelInChunker);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Set<String> getCategories(String inputText) throws IOException {
		// String inputText =
		// "Which substance should a student apply to the skin if he or she gets splashed with an acid";
		DocumentCategorizerME myCategorizer = new DocumentCategorizerME(chunkerModel);
		Map<String, Double> category = myCategorizer.scoreMap(inputText);
		return category.keySet();
	}

	public static void main(String[] args) throws IOException {
		String inputText = "Which of the seeds grow better in the light or the that provides a framework of of its organs";
		DocumentCategorizerME myCategorizer = new DocumentCategorizerME(chunkerModel);
		// double[] outcomes = myCategorizer.categorize(inputText);
		Map<String, Double> category = myCategorizer.scoreMap(inputText);
		String s = "nervous_tissue";
		for (String string : category.keySet()) {
			if(string.contains(s)){
				System.out.println(s);
				System.out.println(category.get(string));
			}
		}
	}
}
