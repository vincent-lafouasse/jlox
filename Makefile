JAVA_MAKEFILE = util/java.make

jlox:
	make -f $(JAVA_MAKEFILE) PACKAGE=lox

.PHONY: jlox
