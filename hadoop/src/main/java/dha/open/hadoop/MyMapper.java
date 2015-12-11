package dha.open.hadoop;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;

public class MyMapper extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {

	@Override
	public void map(LongWritable arg0, Text arg1, OutputCollector<Text, IntWritable> arg2, Reporter arg3) throws IOException {
		String input = arg0.toString();
		for (String word : input.split(" ")) {
			if (word.length() > 0) {
				arg2.collect(new Text(word), new IntWritable(1));
			}
		}
	}

}
