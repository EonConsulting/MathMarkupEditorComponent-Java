MathML - LaTeX Java Implementation.
#=====================================================================#

Introduction
#============
This component uses Fmath to implement both Latex and MathML functionality of the UNISA ECS project.
FMath is a library that has support for Java, C++, JavaScript and Flash. 
The Java implementation of this library will be used for the creation of mathematical formulae on UNISA ECS project.
The MathML or LaTeX markup gets translated into a base64 image string and this string can be persisted 
to a database and later retrieved to be used as needed. 

Features:
#========
• Fmath includes a rich set of built-in commands. 
• Full programming language features allow complicated macros to be easily defined. Decodes chunked encoding.
• Most complete selection of mathematical and special symbols. 
• Fmath uses only integer arithmetic, so it processes quickly.
• Fmath is output device independent.
• Fmath creates a device-independent (DVI) output file in a standard, well-documented format.
• Filter programs then convert this file to the format needed by various printers or graphics terminals.


Implementation:
#==============
The Java support of FMath will be used on the UNISA ESC project. Grab the JAR file from the FMath official website http://www.fmath.info/ 
or the relevant maven repository. Include a maven dependency in your pom or include the JAR in your project classpath to use the relevant
provided to implement MathML or LaTeX.
 
Below is a typical FMath Java sample code which reads markup from a file and converts it to an image.

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import fmath.ApplicationConfiguration;
import fmath.components.MathMLFormula;

public class GenerateImage {

	private static File fileMathML = new File("./pathToFile/filename.mathml");
	private static String folderGeneratedImage = "./images";

	private static String folderFonts = "./fonts";
	private static String folderGlyps = "./glyphs";

	public static void main(String[] args) throws IOException {
		
		ApplicationConfiguration.setFolderUrlForFonts(folderFonts);
		ApplicationConfiguration.setFolderUrlForGlyphs(folderGlyps);
		ApplicationConfiguration.setWebApp(false);

		String mathml = getFileAsString(fileMathML);
		MathMLFormula formula = new MathMLFormula();
		BufferedImage img = formula.drawImage(mathml);

		File file = new File(folderGeneratedImage + "/test.png");
		ImageIO.write(img, "png", file);
		System.out.println("--> Image generated in folder:" + folderGeneratedImage);
	}

	
	private static String getFileAsString(File fileSource) throws FileNotFoundException {
		Scanner scanner = null;
		StringBuffer sb = new StringBuffer();
		try {
			scanner = new Scanner(new FileInputStream(fileSource), "UTF-8");
			while (scanner.hasNextLine()) {
				sb.append(scanner.nextLine() + "\n");
			}
		} finally {
			if (scanner != null)
				scanner.close();
		}

		return sb.toString();
	}

}
