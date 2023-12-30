# Makefile for building a single directory of Java source files.

BUILD_DIR := build

SOURCES := $(wildcard com/poss/$(PACKAGE)/*.java)
CLASSES := $(addprefix $(BUILD_DIR)/, $(SOURCES:.java=.class))

JAVA_OPTIONS := -Werror

default: $(CLASSES)
	@: # Don't show "Nothing to be done" output.

# Compile a single .java file to .class.
$(BUILD_DIR)/%.class: %.java
	@ mkdir -p $(BUILD_DIR)
	@ javac -cp . -d $(BUILD_DIR) $(JAVA_OPTIONS) -implicit:none $<
	@ printf "%8s %-40s %s\n" javac $< "$(JAVA_OPTIONS)"

.PHONY: default
