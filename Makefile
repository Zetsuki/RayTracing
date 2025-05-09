JAR_NAME = RayTracing.jar
OUT_DIR = out
SRC_DIR = src
MAIN_CLASS = Main
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Default
all: clean build

# Compilation
build:
	@mkdir -p $(OUT_DIR)
	@javac -d $(OUT_DIR) $(SOURCES)
	@echo "Compilation finished."

# Execution
run:
	@java -cp $(OUT_DIR) $(MAIN_CLASS)

# Cleaning
clean:
	@rm -rf $(OUT_DIR)
	@echo "Out dir cleaned."

.PHONY: all build run clean
