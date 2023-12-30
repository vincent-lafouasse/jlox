package com.poss.tool;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

class GenerateAST {
	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("Usage: generate_ast <output directory>");
			System.exit(64);
		}

		String outputDir = args[0];

		defineAst(outputDir, "Expr", Arrays.asList(
			"Binary   : Expr left, Token operator",
			"Grouping : Expr left, Token operator",
			"Literal  : Expr left, Token operator",
			"Unary    : Expr left, Token operator"
		));
	}

	private static void defineAst( String outputDir, String baseName,
		List<String> types) throws IOException {
		String path = outputDir + "/" + baseName + ".java";
		PrintWriter writer = new PrintWriter(path, "UTF-8");

		writer.println("package com.poss.lox;");
		writer.println();
		writer.println("import java.util.List;");
		writer.println();
		writer.println("abstract class " + baseName + " {");

		for (String type: types) {
			String className = type.split(":")[0].trim();
			String fields = type.split(":")[1].trim();
			defineType(writer, baseName, className, fields);
		}

		writer.println("}");
		writer.close();
	}

	private static void defineType(
		PrintWriter writer, String baseName,
		String className, String fieldList) {
		writer.println("\tstatic class " + className + " extends " +
			baseName + " {");

		// Constructor.
		writer.println("\t\t" + className + "(" + fieldList + ") {");

		// Store parameters in fields.
		String[] fields = fieldList.split(", ");
		for (String field : fields) {
			String name = field.split(" ")[1];
			writer.println("\t\t\tthis." + name + " = " + name + ";");
		}

		writer.println("\t\t}");

		// Fields.
		writer.println();
		for (String field : fields) {
			writer.println("\t\tfinal " + field + ";");
		}

		writer.println("\t}");
	}
}
