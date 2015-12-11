package dha.open.hadoop;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

public class MyReducer extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {

	@Override
	public void reduce(Text arg0, Iterator<IntWritable> arg1, OutputCollector<Text, IntWritable> arg2, Reporter arg3) throws IOException {
		int count = 0;
		while (arg1.hasNext()) {
			IntWritable i = arg1.next();
			count += i.get();
		}
		arg2.collect(new Text(arg0), new IntWritable(count));

	}

}
