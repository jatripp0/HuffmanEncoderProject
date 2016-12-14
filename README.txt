Huffman Encoder
Johnathan Tripp

Hello! This is a Huffman encoder that will take an input file and generate a binary encoding of the text in the file. This is done using a Huffman tree, which orders characters included in the text file according to their frequency of use in the file. Based on this ordering in the tree, a code is able to be assigned by traversing the nodes of the tree. The most frequently used characters are assigned a rather short code, while less frequently used characters are assigned longer codes. The end result is a binary string of encoded text that saves space in comparison to the original text file.

To use this program:

1. In a command prompt, navigate to the directory in which the JAR file for this project is located.

2. Execute the program by running the following command, inserting the directory+name of the file you wish to encode

	java -jar TrippJohnathanHuffmanEncoder.jar [example.txt] <-- This is where you type the name of your text file.

You should now see a frequency table of all the characters in the file, as well as the resulting encoding of the text.