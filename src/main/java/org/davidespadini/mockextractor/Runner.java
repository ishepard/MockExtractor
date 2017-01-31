package org.davidespadini.mockextractor;

import java.io.IOException;

import org.davidespadini.mockextractor.core.Parser;
import org.davidespadini.mockextractor.utils.FileUtils;

public class Runner {

	public static void main(String[] args) throws IOException {
		if (args.length < 3){
			System.out.println("Usage: java -jar <tool>.jar <directory> <dir-deps> <output.csv>");
			System.exit(-1);
		}
		
		String dir = args[0];
		String[] deps = FileUtils.getAllJarFiles(args[1]);
		String output = args[2];

		Parser parser = new Parser(dir, output, deps);
		
		parser.parse();
		
	}

}
