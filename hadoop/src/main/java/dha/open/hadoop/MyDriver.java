package dha.open.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

import com.sun.jersey.core.impl.provider.entity.XMLJAXBElementProvider.Text;

public class MyDriver extends Configured implements Tool {

	@Override
	public int run(String[] arg0) throws Exception {
		if (arg0.length < 2)
			return -1;

		JobConf conf = new JobConf(MyDriver.class);

		conf.setJobName("Count Names");
		FileInputFormat.addInputPath(conf, new Path(arg0[0]));
		FileOutputFormat.setOutputPath(conf, new Path(arg0[1]));

		conf.setMapperClass(MyMapper.class);
		conf.setReducerClass(MyReducer.class);

		conf.setMapOutputKeyClass(Text.class);
		conf.setMapOutputValueClass(IntWritable.class);

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(IntWritable.class);

		JobClient.runJob(conf);
		return 0;
	}

	public static void main(String[] args) throws Exception {
		int exit = ToolRunner.run(new MyDriver(), args);
		System.exit(exit);
	}

}
