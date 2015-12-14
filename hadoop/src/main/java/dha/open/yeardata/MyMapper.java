package dha.open.yeardata;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MyMapper extends MapReduceBase implements
		org.apache.hadoop.mapred.Mapper<LongWritable, Text, Text, Text> {

	@Override
	public void map(LongWritable arg0, Text arg1,
			OutputCollector<Text, Text> mapOutput, Reporter arg3) throws IOException {
		String inputText = arg1.toString();
		String key = inputText.split("\t")[0];
		String value = inputText.split("\t")[1];
	}
}
