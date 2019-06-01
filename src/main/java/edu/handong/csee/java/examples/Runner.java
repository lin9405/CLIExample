package edu.handong.csee.java.examples;

import org.apache.commons.cli.CommandLine;

import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import java.io.*;

//옵션 어떻게 넣냐면 -s 3 -c 6 
public class Runner {
	
	String path;
	boolean verbose;
	boolean help;
	boolean full;

	public static void main(String[] args) {

		Runner myRunner = new Runner();
		myRunner.run(args);
		

	}

	private void run(String[] args) {
	
		Options options = createOptions();
		
		if(parseOptions(options, args)){
			if (help){
				System.out.println("!!!");
				printHelp(options);
				return;
			}
			
			// path is required (necessary) data so no need to have a branch.
			System.out.println("You provided \"" + path + "\" as the value of the option p");
			
			// TODO show the number of files in the path
			
			if(verbose) {
		
				System.out.println("");
				
				System.out.println("Your program is terminated. (This message is shown because you turned on -v option!");
			}
			File dir = new File(path);
			
		//	File file = new File(path);
		
			if(full) {
			//System.out.println( file.getName());
				
				if(dir.listFiles() != null) {
					for(File file:dir.listFiles()) {
						if(file.isFile()) {
							System.out.println(file.getName());
							System.out.println(file.getAbsolutePath());
						}
						
					}
					}
				else {
					System.out.println(dir.getAbsolutePath());
				}
			} 
		}
	}

	private boolean parseOptions(Options options, String[] args) {
		CommandLineParser parser = new DefaultParser();

		try {

			CommandLine cmd = parser.parse(options, args);

			path = cmd.getOptionValue("p");
			verbose = cmd.hasOption("v");
			help = cmd.hasOption("h");
			full =cmd.hasOption("f");

		} catch (Exception e) {
			printHelp(options);
			System.out.println("??");
			return false;
		}

		return true;
	}

	// Definition Stage
	private Options createOptions() {
		Options options = new Options();

		// add options by using OptionBuilder
		options.addOption(Option.builder("p").longOpt("path")
				.desc("Set a path of a directory or a file to display")
				.hasArg()
				.argName("Path name to display")
				.required()
				.build());

		// add options by using OptionBuilder
		options.addOption(Option.builder("v").longOpt("verbose")
				.desc("Display detailed messages!")
				//.hasArg()     // this option is intended not to have an option value but just an option
				.argName("verbose option")
				//.required() // this is an optional option. So disabled required().
				.build());
		
		// add options by using OptionBuilder
		options.addOption(Option.builder("h").longOpt("help")
		        .desc("Help")
		        .build());
		
	options.addOption((Option.builder("f").longOpt("fullpath"))
			.desc("Help")
			.build());

		return options;
	}
	
	private void printHelp(Options options) {
		// automatically generate the help statement
		HelpFormatter formatter = new HelpFormatter();
		String header = "CLI test program";
		String footer ="\nPlease report issues at https://github.com/lifove/CLIExample/issues";
		formatter.printHelp("CLIExample", header, options, footer, true);
	}
}
