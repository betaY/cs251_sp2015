rm results.txt 2> /dev/null
touch results.txt

FLAG=0

echo -e "Compiling..." >> results.txt
javac TreeParser.java 2>> results.txt
if [ $? != 0 ]; then
	echo -e "\n!!!!!!!!Compilation Failed" >> results.txt
	echo "Some trouble. Check results.txt inside student's directory for more information"
	exit
else
	echo -e "\n**Compilation Successful" >> results.txt
fi


for i in {1..7}
do
	echo -e "\n\n\n\n=========Running Input File "$i"===" >> results.txt
	echo -e "  -------------------------------------\n  Testing Program Behaviour on Input...\n  --------------------------------------" >> results.txt
	java -ea TreeParser < data/sample_input$i.txt > /dev/null 2> /dev/null
	if [ $? != 0 ]; then
		echo -e "\n    !!!!!!!!Exceptions when Running on Input File "$i "\n" >> results.txt
		FLAG=1
	else
		echo -e "\n    **Program working Fine on Input File "$i "\n" >> results.txt
	fi

	java TreeParser < data/sample_input$i.txt > data/prog_output$i.txt 2>> results.txt
	if [ $? != 0 ]; then
		echo -e "\n  !!!!!!!!Running TestCase"$i" Failed due to exception..." >> results.txt
		FLAG=1
	fi

	if cmp -s data/prog_output$i.txt data/sample_output$i.txt
	then
		echo "    **Output perfect :)" >> results.txt
	else
		FLAG=1
		echo "    !!!!!!!!Output Differs. Do vimdiff of data/prog_output"$i".txt and data/sample_output"$i".txt" >> results.txt
	fi
done

if [ $FLAG != 0 ]; then
	echo "Some trouble. Check results.txt inside student's directory for more information"
else
	echo "Everythings seems perfect..."
fi
