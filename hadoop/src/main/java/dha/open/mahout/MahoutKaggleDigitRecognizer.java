package dha.open.mahout;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.commons.math.util.MathUtils;
import org.apache.mahout.classifier.df.DecisionForest;
import org.apache.mahout.classifier.df.builder.DefaultTreeBuilder;
import org.apache.mahout.classifier.df.data.Data;
import org.apache.mahout.classifier.df.data.DataLoader;
import org.apache.mahout.classifier.df.ref.SequentialBuilder;
import org.apache.mahout.common.RandomUtils;

public class MahoutKaggleDigitRecognizer {
	  public static void main(String[] args) throws Exception {
	    String descriptor = "L N N I N";
	    String[] trainDataValues = fileAsStringArray("train.csv");
	 
	    Data data = DataLoader.loadData(DataLoader.generateDataset(descriptor, false, trainDataValues), trainDataValues);
	 
	    int numberOfTrees = 100;
	    DecisionForest forest = buildForest(numberOfTrees, data);
	  }
	 
	  private static DecisionForest buildForest(int numberOfTrees, Data data) {
	    int m = (int) Math.floor(MathUtils.log(2, data.getDataset().nbAttributes()) + 1);
	 
	    DefaultTreeBuilder treeBuilder = new DefaultTreeBuilder();
	    treeBuilder.setM(m);
	 
	    return new SequentialBuilder(RandomUtils.getRandom(), treeBuilder, data.clone()).build(numberOfTrees);
	  }
	 
	  private static String[] fileAsStringArray(String file) throws Exception {
	    ArrayList<String> list = new ArrayList<String>();
	 
	    DataInputStream in = new DataInputStream(new FileInputStream(file));
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	 
	    String strLine;
	    br.readLine(); // discard top one (header)
	    while ((strLine = br.readLine()) != null) {
	      list.add(strLine);
	    }
	 
	    in.close();
	    return list.toArray(new String[list.size()]);
	  }
	}