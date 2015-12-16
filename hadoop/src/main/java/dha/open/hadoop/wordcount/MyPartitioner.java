package dha.open.hadoop.wordcount;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.Partitioner;

public class MyPartitioner implements Partitioner<Text, IntWritable> {

	@Override
	public void configure(JobConf arg0) {

	}

	@Override
	public int getPartition(Text key, IntWritable value, int numberOfReducer) {
		String s = key.toString();
		if (s.length() == 1)
			return 0;
		if (s.length() == 2)
			return 1;
		if (s.length() == 3)
			return 2;
		return 0;
	}
}
