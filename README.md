# javaNetUnisexBathroom
Problem kupatila sa muskarcima, zenama, decom i domarom. Resen kao distribuiran problem preko Socketa.
Koraci pri kreiranju:
1)	Kreirati interfejs UBInterface koji deklarise sve metode koje pozivaju muskarci, zene, dece i janitor(domar).
2)	Kreirati konkurentnu aplikaciju SingleBathroomProblem kao SC monitor i proveriti da li radi. Ona treba da bude izvedena iz UBInterface.
3)	Kreirati server – ja sam ga ovde napravila kao Executor server, ali I dalje pojma nemam kako da ga ugasim
4)	Kreiranje Request Handler-a: iz nekog razloga ovo ispisivanje zakomentarisano pravi problem i blokira niti. Zasto koji k???
5)	Kreiranje SingleBathroomNet klase kojom klijenti salju zahteve serveru
6)	Kreiranje klijentskih klasa, u konstruktoru dobijaju objekat SingleBathroomNet, (NE SingleBathroomProblem objekat)
7)	Test klasa
