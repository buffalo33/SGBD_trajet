echo "Entrer le nom d'utilisateur:"
read id_user
echo "Entrer le mot de passe :"
read pwd_user

make src

control_word=begin
choices=()
nb_choices=0



while [ $control_word != stop ]
do
    echo "Entrer une nouvelle requete"
    read request
    control_word=$request
    
    if [ $request != stop ]
    
    then

	if [ $request == trajetzonedate ]
	then
	    echo "Entrer zone1"
	    read zone1
	    echo "Entrer zone2"
	    read zone2
	    echo "Entrer date"
	    read date
	    choices=($request $zone1 $zone2 $date)
	    nb_choices=4
	    args=($id_user $pwd_user $nb_choices ${choices[@]})
	    java Projet ${args[@]}
	else
	    choices=($request)
	    nb_choices=1    
	    args=($id_user $pwd_user $nb_choices ${choices[@]})
	    java Projet ${args[@]}
	fi

    fi

done






#args=($id_user $pwd_user $nb_choices ${choices[@]})
#echo ${args[@]}

#javac Projet.java



