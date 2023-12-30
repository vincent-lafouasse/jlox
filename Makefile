JAVA_MAKEFILE = util/java.make

jlox: generate_ast
	@make -f $(JAVA_MAKEFILE) PACKAGE=lox

generate_ast:
	@make -f $(JAVA_MAKEFILE) PACKAGE=tool
	@java -cp build com.poss.tool.GenerateAST com/poss/lox

.PHONY: jlox
