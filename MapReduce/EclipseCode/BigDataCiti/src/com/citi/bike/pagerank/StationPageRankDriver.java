package com.citi.bike.pagerank;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;

public class StationPageRankDriver {

	public static void main(String[] args)  throws Exception
	{ 		
///*
		//Station Data Mapper and Reducer
		JobClient client = new JobClient();

		JobConf conf1 = new JobConf(StationPageRankDriver.class);
		conf1.setJobName("Station-1/3");

		conf1.setMapperClass(StationDataMapper.class);
		conf1.setReducerClass(StationDataReducer.class);

		conf1.setOutputKeyClass(Text.class);
		conf1.setOutputValueClass(Text.class);

		conf1.setNumReduceTasks(5);

		
		FileInputFormat.setInputPaths(conf1, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf1, new Path(args[1]));

		client.setConf(conf1);

		try 
		{
			JobClient.runJob(conf1);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}

//*/		
	
///*
		//Station IterMapper and Reducer
		String input = args[1];
		String output = "";
		
		for (int i = 0; i < 4; i++) 
		{
			JobClient client2 = new JobClient();
			JobConf conf2 = new JobConf(StationPageRankDriver.class);
			conf2.setJobName("Station Iter_" + (i + 1));
			conf2.setNumReduceTasks(5);
			//~dk
			//conf.setInputFormat(org.apache.hadoop.mapred.SequenceFileInputFormat.class); 
			//conf.setOutputFormat(org.apache.hadoop.mapred.SequenceFileOutputFormat.class); 
			conf2.setOutputKeyClass(Text.class);
			conf2.setOutputValueClass(Text.class);

			if (input.length() == 0) 
			{
				System.out.println("Usage: PageRankIter <input path> <output path>");
				System.exit(0);
			}

			String op = "Station_Iter_Output_"+(i+1);
			Path outPath = new Path(op);
			//~dk
			//conf.setInputPath(new Path(args[0])); 
			//conf.setOutputPath(new Path(args[1])); 
			FileInputFormat.setInputPaths(conf2, new Path(input));
			FileOutputFormat.setOutputPath(conf2, outPath);
			//conf.setInputPath(new Path("graph2")); 
			//conf.setOutputPath(new Path("graph3")); 
			conf2.setMapperClass(StationIterMapper.class);
			conf2.setReducerClass(StationIterReducer.class);
			conf2.setCombinerClass(StationIterReducer.class);
			client2.setConf(conf2);

			try 
			{
				FileSystem dfs = FileSystem.get(outPath.toUri(), conf2);

				if(dfs.exists(outPath))
				{
					dfs.delete(outPath, true);
				}

				JobClient.runJob(conf2);
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}

			input  = op;
			output = op;
		}
		
//*/
		
	
		
///*
		
		//Viewer
		JobClient client3 = new JobClient(); 
		JobConf conf3 = new JobConf(StationPageRankDriver.class); 
		conf3.setJobName("Station Viewer"); 

		//~dk
		//conf.setInputFormat(org.apache.hadoop.mapred.SequenceFileInputFormat.class); 

		conf3.setOutputKeyClass(FloatWritable.class); 
		conf3.setOutputValueClass(Text.class); 

		
//		if (args.length < 2) 
//		{ 
//			System.out.println("Usage: SpeciesViewerDriver <input path> <output path>"); 
//			System.exit(0); 
//		} 

		//~dk
		//conf.setInputPath(new Path(args[0])); 
		//conf.setOutputPath(new Path(args[1])); 
		FileInputFormat.setInputPaths(conf3, new Path(output));
		FileOutputFormat.setOutputPath(conf3, new Path(args[2]));

		conf3.setMapperClass(StationViewerMapper.class); 
		conf3.setReducerClass(org.apache.hadoop.mapred.lib.IdentityReducer.class); 

		client3.setConf(conf3); 
		
		try 
		{ 
			JobClient.runJob(conf3); 
		} 
		catch (Exception e) 
		{ 
			e.printStackTrace(); 
		} 
		
		//*/
	}
	
	
}
