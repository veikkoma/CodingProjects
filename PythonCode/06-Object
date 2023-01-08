class Tavara():
    def __init__(self, nimi:str, paino:int):
        self.__nimi = nimi
        self.__paino = paino
 
    def nimi(self):
        return self.__nimi
 
    def paino(self):
        return self.__paino
 
    def __str__(self):
        return f"{self.__nimi} ({self.__paino} kg)"
 
class Matkalaukku():
    def __init__(self, maxpaino:int):
        self.maxpaino = maxpaino
        self.tavarat = []
        self.matkalaukun_paino = 0
        self.tavara_lkm = 0
        self.raskain = 0
 
    def lisaa_tavara(self, tavara:Tavara):
        self.tavarat.append(tavara)
        if self.maxpaino > self.matkalaukun_paino + tavara.paino():
            self.tavara_lkm +=1
            self.matkalaukun_paino += tavara.paino()
 
    def tulosta_tavarat(self):
        for tavara in self.tavarat:
            print (tavara)
 
    def paino(self):
        return int(f"{self.matkalaukun_paino}")
 
    def raskain_tavara(self):
        raskain = self.tavarat[0]
        if not self.tavarat:
            return None
        else:
            for tavara in self.tavarat:
                if tavara.paino() >= raskain.paino():
                    raskain = tavara
            return raskain
 
    def __str__(self):
        if self.tavara_lkm == 1:
            return f"{self.tavara_lkm} tavara ({self.matkalaukun_paino} kg)"
        elif self.tavara_lkm !=1:
            return f"{self.tavara_lkm} tavaraa ({self.matkalaukun_paino} kg)"
 
class Lastiruuma():
    def __init__(self, maxpaino:int):
        self.maxpaino = maxpaino
        self.yhteispaino = 0
        self.matkalaukkujen_lkm = 0
        self.ruuma = []
 
    def lisaa_matkalaukku(self, laukku:Matkalaukku):
        self.ruuma.append(laukku)
        if self.maxpaino > laukku.matkalaukun_paino + self.yhteispaino:
            self.yhteispaino += laukku.matkalaukun_paino
            self.matkalaukkujen_lkm +=1
 
    def tulosta_tavarat(self):
        for laukku in self.ruuma:
            laukku.tulosta_tavarat()
        # 93%
        # return laukku.tulosta_tavarat()
        # tulostaa täysin oikein, silti vain 93
 
        # 96%
        # for laukku in self.ruuma:
        #     return laukku.tulosta_tavarat()
 
        # 93%
        # for laukku in self.ruuma:
        #     print (laukku.tulosta_tavarat())
        # tulostaa tanhu, nokia, None, TIilis, None
 
        # 93%
        #return laukku.tulosta_tavarat()
 
    def __str__(self):
        if self.matkalaukkujen_lkm ==1:
            return f"{self.matkalaukkujen_lkm} matkalaukku, tilaa {self.maxpaino - self.yhteispaino} kg"
        elif self.matkalaukkujen_lkm !=1:
            return f"{self.matkalaukkujen_lkm} matkalaukkua, tilaa {self.maxpaino - self.yhteispaino} kg"
 
if __name__ == "__main__":
    kirja = Tavara("Tanhupallon paluu", 2)
    puhelin = Tavara("Nokia 3432", 1)
    tiiliskivi = Tavara("Tiiliskivi", 4)
    print ("Kirjan nimi: ", kirja.nimi())
    print ("Kirjan paino: ", kirja.paino())
    print ("Kirja: ", kirja)
    print ("Puhelin: ", puhelin)
 
    laukku = Matkalaukku(10)
    print (laukku)
 
    laukku.lisaa_tavara(kirja)
    laukku.lisaa_tavara(puhelin)
    laukku.lisaa_tavara(tiiliskivi)
 
    print("Matkalaukussa on seuraavat tavarat:")
    laukku.tulosta_tavarat()
    paino_yht = laukku.paino()
    print(f"Yhteispaino: {paino_yht} kg")
 
    raskain = laukku.raskain_tavara()
    print(f"Raskain tavara: {raskain}")
 
    lastiruuma = Lastiruuma(1000)
 
    allunellun_laukku = Matkalaukku(10)
    allunellun_laukku.lisaa_tavara(kirja)
    allunellun_laukku.lisaa_tavara(puhelin)
 
    helmutin_laukku=Matkalaukku(10)
    helmutin_laukku.lisaa_tavara(tiiliskivi)
    lastiruuma.lisaa_matkalaukku(allunellun_laukku)
 
    lastiruuma.lisaa_matkalaukku(helmutin_laukku)
    print (lastiruuma)
 
    print("Ruuman matkalaukuissa on seuraavat tavarat:")
    lastiruuma.tulosta_tavarat()
