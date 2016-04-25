//package com.citi.bike.test;
//
/////
////cc MaxTemperatureUsingSecondarySort Application to find the maximum temperature by sorting temperatures in the key
//import java.io.IOException;
//
//import java.nio.ByteBuffer;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.IntWritable.Comparator;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.io.WritableComparator;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
//import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
//import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
//import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
//
//
////vv MaxTemperatureUsingSecondarySort
//public class SecondarySort
//{
//	public static void main(String[] args) throws Exception {
//
//		Path inputPath = new Path("TestInput");
//		Path outputDir = new Path("TestOutut");
//
//		// Path inputPath = new Path(args[0]);
//		// Path outputDir = new Path(args[1]);
//
//		// Create configuration
//		Configuration conf = new Configuration(true);
//
//		// Create job
//		Job job = new Job(conf, "Test HIVE commond");
//		job.setJarByClass(SecondarySort.class);
//
//		// Setup MapReduce
//		job.setMapperClass(SecondarySort.MapTask.class);
//		job.setReducerClass(SecondarySort.ReduceTask.class);
//		job.setNumReduceTasks(1);
//
//		// Specify key / value
//		job.setMapOutputKeyClass(IntWritable.class);
//		job.setMapOutputValueClass(IntWritable.class);
//		job.setOutputKeyClass(IntWritable.class);
//		job.setOutputValueClass(IntWritable.class);
//		job.setSortComparatorClass(IntComparator.class);
//		// Input
//		FileInputFormat.addInputPath(job, inputPath);
//		job.setInputFormatClass(TextInputFormat.class);
//
//		// Output
//		FileOutputFormat.setOutputPath(job, outputDir);
//		job.setOutputFormatClass(TextOutputFormat.class);
//
//		/*
//		 * // Delete output if exists FileSystem hdfs = FileSystem.get(conf); if
//		 * (hdfs.exists(outputDir)) hdfs.delete(outputDir, true);
//		 * 
//		 * // Execute job int code = job.waitForCompletion(true) ? 0 : 1;
//		 * System.exit(code);
//		 */
//
//		// Execute job
//		int code = job.waitForCompletion(true) ? 0 : 1;
//		System.exit(code);
//
//	}
//
//	public static class IntComparator extends WritableComparator {
//
//		public IntComparator() {
//			super(IntWritable.class);
//		}
//
//		@Override
//		public int compare(byte[] b1, int s1, int l1,
//				byte[] b2, int s2, int l2) {
//
//			Integer v1 = ByteBuffer.wrap(b1, s1, l1).getInt();
//			Integer v2 = ByteBuffer.wrap(b2, s2, l2).getInt();
//
//			return v1.compareTo(v2) * (-1);
//		}
//	}
//
//	public static class MapTask extends
//	Mapper<LongWritable, Text, IntWritable, IntWritable> {
//		public void map(LongWritable key, Text value, Context context)
//				throws java.io.IOException, InterruptedException {
//			String line = value.toString();
//			String[] tokens = line.split(","); // This is the delimiter between
//			int keypart = Integer.parseInt(tokens[0]);
//			int valuePart = Integer.parseInt(tokens[1]);
//			context.write(new IntWritable(valuePart), new IntWritable(keypart));
//
//		}
//	}
//
//	public static class ReduceTask extends
//	Reducer<IntWritable, IntWritable, IntWritable, IntWritable> {
//		public void reduce(IntWritable key, Iterable<IntWritable> list, Context context)
//				throws java.io.IOException, InterruptedException {
//
//			for (IntWritable value : list) {
//
//				context.write(value,key);
//
//			}
//
//		}
//	}
//
//}
//
///*
//// cc MaxTemperatureUsingSecondarySort Application to find the maximum temperature by sorting temperatures in the key
//import java.io.IOException;
//
//import org.apache.hadoop.conf.Configured;
//import org.apache.hadoop.io.LongWritable;
//import org.apache.hadoop.io.NullWritable;
//import org.apache.hadoop.io.Text;
//import org.apache.hadoop.io.WritableComparable;
//import org.apache.hadoop.io.WritableComparator;
//import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapreduce.Partitioner;
//import org.apache.hadoop.mapreduce.Reducer;
//import org.apache.hadoop.util.Tool;
//import org.apache.hadoop.util.ToolRunner;
//
//// vv MaxTemperatureUsingSecondarySort
//public class MaxTemperatureUsingSecondarySort
//  extends Configured implements Tool {
//  
//  static class MaxTemperatureMapper
//    extends Mapper<LongWritable, Text, IntPair, NullWritable> {
//  
//    private NcdcRecordParser parser = new NcdcRecordParser();
//    
//    @Override
//    protected void map(LongWritable key, Text value,
//        Context context) throws IOException, InterruptedException {
//      
//      parser.parse(value);
//      if (parser.isValidTemperature()) {
//        /*[*/context.write(new IntPair(parser.getYearInt(),
//            parser.getAirTemperature()), NullWritable.get());/*]*/
//      }
//    }
//  }
//  
//  static class MaxTemperatureReducer
//    extends Reducer<IntPair, NullWritable, IntPair, NullWritable> {
//  
//    @Override
//    protected void reduce(IntPair key, Iterable<NullWritable> values,
//        Context context) throws IOException, InterruptedException {
//      
//      /*[*/context.write(key, NullWritable.get());/*]*/
//    }
//  }
//  
//  public static class FirstPartitioner
//    extends Partitioner<IntPair, NullWritable> {
//
//    @Override
//    public int getPartition(IntPair key, NullWritable value, int numPartitions) {
//      // multiply by 127 to perform some mixing
//      return Math.abs(key.getFirst() * 127) % numPartitions;
//    }
//  }
//  
//  public static class KeyComparator extends WritableComparator {
//    protected KeyComparator() {
//      super(IntPair.class, true);
//    }
//    @Override
//    public int compare(WritableComparable w1, WritableComparable w2) {
//      IntPair ip1 = (IntPair) w1;
//      IntPair ip2 = (IntPair) w2;
//      int cmp = IntPair.compare(ip1.getFirst(), ip2.getFirst());
//      if (cmp != 0) {
//        return cmp;
//      }
//      return -IntPair.compare(ip1.getSecond(), ip2.getSecond()); //reverse
//    }
//  }
//  
//  public static class GroupComparator extends WritableComparator {
//    protected GroupComparator() {
//      super(IntPair.class, true);
//    }
//    @Override
//    public int compare(WritableComparable w1, WritableComparable w2) {
//      IntPair ip1 = (IntPair) w1;
//      IntPair ip2 = (IntPair) w2;
//      return IntPair.compare(ip1.getFirst(), ip2.getFirst());
//    }
//  }
//
//  @Override
//  public int run(String[] args) throws Exception {
//    Job job = JobBuilder.parseInputAndOutput(this, getConf(), args);
//    if (job == null) {
//      return -1;
//    }
//    
//    job.setMapperClass(MaxTemperatureMapper.class);
//    /*[*/job.setPartitionerClass(FirstPartitioner.class);/*]*/
//    /*[*/job.setSortComparatorClass(KeyComparator.class);/*]*/
//    /*[*/job.setGroupingComparatorClass(GroupComparator.class);/*]*/
//    job.setReducerClass(MaxTemperatureReducer.class);
//    job.setOutputKeyClass(IntPair.class);
//    job.setOutputValueClass(NullWritable.class);
//    
//    return job.waitForCompletion(true) ? 0 : 1;
//  }
//  
//  public static void main(String[] args) throws Exception {
//    int exitCode = ToolRunner.run(new MaxTemperatureUsingSecondarySort(), args);
//    System.exit(exitCode);
//  }
//}
//// ^^ MaxTemperatureUsingSecondarySort
//*/