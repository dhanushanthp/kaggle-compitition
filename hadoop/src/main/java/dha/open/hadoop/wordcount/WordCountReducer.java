package dha.open.hadoop.wordcount;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class WordCountReducer extends MapReduceBase implements
		Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	public void reduce(Text key, Iterator<IntWritable> collection,
			OutputCollector<Text, IntWritable> output, Reporter report)
			throws IOException {
		int count = 0;
		while (collection.hasNext()) {
			IntWritable i = collection.next();
			count += i.get();
		}
		output.collect(new Text(key), new IntWritable(count));
	}

}
