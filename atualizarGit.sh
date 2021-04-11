#!/bin/bash
PROJETO=$1
if [ $PROJETO ]
then
	git add . -f
	git commit -m "Deploy atual"
	git push $PROJETO master
else  
	echo "Informe o nome do projeto."
fi

