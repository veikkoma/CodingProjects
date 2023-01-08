class Tehtava:
    id = 0
    @classmethod 
    def uusi_id(cls):
        Tehtava.id += 1
        return Tehtava.id
 
    def __init__(self, kuvaus, koodari, tyomaara):
        self.koodari = koodari
        self.kuvaus = kuvaus
        self.tyomaara = tyomaara
        self.id = Tehtava.uusi_id()
        self.valmis = False
    
    def on_valmis(self):
        return self.valmis 
 
    def merkkaa_valmiiksi(self):
        self.valmis = True
 
    def __str__(self):
        status = "EI VALMIS" if not self.valmis else "VALMIS"
        return f"{self.id}: {self.kuvaus} ({self.tyomaara} tuntia), koodari {self.koodari} {status}"
 
class Tilauskirja:
    def __init__(self):
        self.__tehtavat = []
 
    def lisaa_tilaus(self, kuvaus, koodari, tyomaara):
        self.__tehtavat.append(Tehtava(kuvaus, koodari, tyomaara))
 
    def kaikki_tilauset(self):
        return self.__tehtavat
 
    def koodarit(self):
        return list(set([t.koodari for t in self.__tehtavat]))
 
    def merkkaa_valmiiksi(self, id: int):
        for tehtava in self.__tehtavat:
            if tehtava.id == id:
                tehtava.merkkaa_valmiiksi()
                return
        
        # ei virheellinen
        raise ValueError("väärä id")
    
    def ei_valmiit_tilauset(self):
        return [t for t in self.__tehtavat if not t.on_valmis()]
 
    def valmiit_tilauset(self):
        return [t for t in self.__tehtavat if t.on_valmis()]
 
    def koodarin_status(self, koodari: str):
        if koodari not in self.koodarit():
            raise ValueError("olematon koodari")
        
        valmiit_tehtavat = [t for t in self.__tehtavat if t.koodari == koodari and t.on_valmis() ]
        ei_valmiit_tehtavat = [t for t in self.__tehtavat if t.koodari == koodari and not t.on_valmis() ]
 
        valmiit_tunnit = sum(t.tyomaara for t in valmiit_tehtavat)
        ei_valmiit_tunnit = sum(t.tyomaara for t in ei_valmiit_tehtavat)
 
        return (len(valmiit_tehtavat), len(ei_valmiit_tehtavat), valmiit_tunnit, ei_valmiit_tunnit)
 
class Sovellus:
    def __init__(self):
        self.tilaukset = Tilauskirja()
 
    def ohje(self):
        # monirivisen merkkijonon määrittely onnistuu triplahipsuilla
        ohje = """
komennot:
0 lopetus
1 lisää tilaus
2 listaa valmiit
3 listaa ei valmiit
4 merkitse tehtävä valmiiksi
5 koodarit
6 koodarin status"""
        print(ohje)
 
    def lisaa(self):
        kuvaus = input("kuvaus: ")
        koodari_arvio = input("koodari ja työmääräarvio: ")
        try:
            koodari = koodari_arvio.split(' ')[0]
            tyomaara = int(koodari_arvio.split(' ')[1])
            self.tilaukset.lisaa_tilaus(kuvaus, koodari, tyomaara)
            print("lisätty!")
        except:
            print("virheellinen syöte")
 
    def ei_valmiit(self):
        for tehtava in self.tilaukset.ei_valmiit_tilauset():
            print(tehtava)
 
    def valmiit(self):
        valmiit = self.tilaukset.valmiit_tilauset()
        if len(valmiit)==0:
            print("ei valmiita")
            return
 
        for tehtava in valmiit:
            print(tehtava)
 
    def koodarit(self):
        for koodari in self.tilaukset.koodarit():
            print(koodari)
 
    def merkkaa_valmiiksi(self):
        try:
            numero = int(input("tunniste: "))
            self.tilaukset.merkkaa_valmiiksi(numero)
            print("merkitty valmiiksi")
        except:
            print("virheellinen syöte")
 
    def koodarin_tilastot(self):
        koodari = input("koodari: ")
        if not koodari in self.tilaukset.koodarit():
            print("virheellinen syöte")
            return
 
        status = self.tilaukset.koodarin_status(koodari)
        print(f"työt: valmiina {status[0]} ei valmiina {status[1]}, tunteja: tehty {status[2]} tekemättä {status[3]}")
 
    def suorita(self):
        self.ohje()
        while True:
            komento = input("komento: ")
            if komento == "0":
                return
            elif komento == "1":
                self.lisaa()
            elif komento == "2":
                self.valmiit()
            elif komento == "3":
                self.ei_valmiit()
            elif komento == "4":
                self.merkkaa_valmiiksi()
            elif komento == "5":
                self.koodarit()
            elif komento == "6":
                self.koodarin_tilastot()
 
Sovellus().suorita()
 
