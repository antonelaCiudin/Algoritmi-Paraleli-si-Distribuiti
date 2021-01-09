					Tema 2. Traffic simulator
				ALGORITMI PARALELI SI DISTRIBUITI
					Ciudin Antonela - 335CB

1 -> Simple Semaphore:
	Pentru cerinta 1, am folosit doar un sleep(), care primeste ca parametru 
	timpul de asteptare personal al fiecarei masini.

2 -> Simple N Roundabout:
	Am folosit un semafor, dupa exemplul din laborator, care permite trecerea 
	unei singure masini. Astfel daca o masina a intrat in intersectie, se 
	apeleaza sleep(), cu un timp egal pentru toate masinile, si abia cand 
	acest timp expira, poate intra o alta masina.

3 -> Simple Strict One Car Roundabout:
	Am creat un vector de semafoare. Fiecare linie are semaforul sau. Cand o 
	masina ajunge la intersectie, verific de pe ce linie vine si o las sa intre 
	sau nu in intersectie, in dependenta de semaforul de pe aceasta linie.

5 -> Simple Max X Car Roundabout:
	Este similar exercitiului 3, doar ca toate semafoarele nu mai sunt 
	initializate cu 1, ci cu numarul maxim de masini care pot intra in 
	intersectie, citit din fisier.

7 -> Crosswalk:
	Atata timp cat isFinished este fals, adica nu au trecut toti pietonii:
		- daca pietonii stau pe loc si ultima culoare nu e verde, afisez 
			culoarea verde si o pastrez.
		- daca pietonii trec, iar ultima culoare e verde schimb culoarea 
			(messages) din verde in rosu si afisez.

10 -> Railroad:
	Initial am stocat in doua cozi, id-ul si directia de unde vine fiecare 
	masina, pentru a pastra oridnea lor. Am adaugat o bariera imediat dupa, 
	pentru ca toate masinile sa ajunga la intersectie, dupa trenul poate sa 
	"treaca". Aici am mai pus o bariera, pentru ca sa pot printa o singura 
	data cant trenul trece. Apoi apelez metoda din clasa Railroad pentru 
	fiecare masina, care extrage din cozi si afiseaza sincron faptul ca 
	masina a pornit.

De asemenea, din pacate, nu am reusit sa instalez versiunea 14 sau 15 de java, 
de aceea am adaptat codul dupa versiunea 11. Am schimbat cu locurile switch-ul 
si return (cum a fost sugerat in tema) si am modificat swicth-ul sintactic, 
pentru a fi acceptat de versiunea mea.