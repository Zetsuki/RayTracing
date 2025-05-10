OUT_DIR = out
SRC_DIR = src
RES_DIR = res
MAIN_CLASS = Main
SOURCES = $(shell find $(SRC_DIR) -name "*.java")

# Default
all: clean build copy-res run

# Compilation
build:
	@mkdir -p $(OUT_DIR)
	@javac -d $(OUT_DIR) $(SOURCES)
	@echo "Compilation finished."

# Copy resources
copy-res:
	@mkdir -p $(OUT_DIR)/$(RES_DIR)
	@cp -r $(RES_DIR)/* $(OUT_DIR)/$(RES_DIR)/
	@echo "Resources copied."

# Execution
run:
	@java -cp $(OUT_DIR) $(MAIN_CLASS)

# Cleaning
clean:
	@rm -rf $(OUT_DIR)
	@echo "Out dir cleaned."

.PHONY: all build run clean
