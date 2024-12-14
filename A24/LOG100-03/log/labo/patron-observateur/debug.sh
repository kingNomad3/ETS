#!/bin/bash

# $1: path to the Java file (*.java)
# $2, $3, etc.: arguments (optional)

display_usage() {
  echo "Usage: $0 path/to/file [arguments]"
  echo "  path/to/file  Java source file (*.java)"
  echo "  arguments     argument list to pass to the program"
}

if [ $# -eq 0 ]; then
  display_usage
  exit 1
fi

# Check that the top folder is the source one
path=$1
IFS=/ read -a folders <<< "$path"
src_dir="src"
if [ "${folders[0]}" != "$src_dir" ]; then
  echo "Source file should be in "$src_dir" directory"
  exit 1
fi

# Output directory
bin_dir=/home/codio/workspace/bin
mkdir -p $bin_dir

# Get the path inside the source folder
path_file=${path#*/}

# Move to the directory
cd $src_dir
if [ $? -ne 0 ]; then exit 1; fi

# Build with debug information
javac -g -d $bin_dir $path_file
if [ $? -ne 0 ]; then exit 1; fi

# Debug
cd $bin_dir
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=5105 ${path_file%.java} ${@:2}
if [ $? -ne 0 ]; then exit 1; fi
