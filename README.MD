##Task 2
Description
Please, complete the following task

1 point.

Write the DiskAnalyzer utility command line, which accepts a path to the input (for example, C: ) and a function number, correctly processes incorrect paths or function numbers. The utility outputs the results of its work to a file. The program should work for the C: drive of your working machine.

Required functionality:

Search for the file name with the maximum number of letters ‘s’ in the name, display the path to it.
Print Top-5 largest files (by size in bytes).
The average file size in the specified directory or any its subdirectory.
The number of files and folders, divided by the first letters of the alphabet (for example, 100,000 files and 200 folders begin with the letter A).

##Task 3
Description
Please, complete the following task

1 point.

Write several versions of the FastFileMover utility, which moves a file from one directory to another directory. It takes both file paths as command-line parameters. All exceptions must be handled correctly.

Required functionality:

It uses FileStreams;
It uses FileStreams with buffer 100 kb;
It uses FileChannel;
It uses NIO 2 File API.
After that prepare a performance report based on the next requirements.

Measure the time for copying, run on several reference files of different sizes (1 Kb, 100 Kb, 10 Mb, 1 GB). On each file, run 1000 times, get the average time.