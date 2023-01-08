import json

class Tiedostonkasittelija():
    def __init__(self, tiedosto):
        self.__tiedosto = tiedosto

    def lataa(self):
        with open(self.__tiedosto) as tiedosto:
            data = tiedosto.read()
        tulos = json.loads(data)
        return tulos

# -----------------------------------------------------------------------
class Pelaaja:
    def __init__(self, nimi: str, kansalaisuus: str, syotot: int, maalit: int, rangaistukset: int, joukkue: str, pelit: int):
        self.__nimi = nimi
        self.__kansalaisuus = kansalaisuus
        self.__syotot = syotot
        self.__maalit = maalit
        self.__rangaistus = rangaistukset
        self.__joukkue = joukkue
        self.__pelit = pelit

    def __str__(self):
        return f"{self.__nimi:20} {self.__joukkue:3} {self.__maalit:>3} + {self.__syotot:>2} = {self.__maalit + self.__syotot:>3}"


# -----------------------------------------------------------------------
class Pelaajaluettelo():
    def __init__(self):
        self.__pelaajaluettelo = []

    def lisaa_pelaaja(self, nimi, kansalaisuus, syotot, maalit, rangaistukset, joukkue, pelit):
        luettelo = Pelaaja(nimi, kansalaisuus, syotot, maalit, rangaistukset, joukkue, pelit)
        self.__pelaajaluettelo.append(luettelo)

    def pelaaja_haku(self, nimi):
        for pelaaja in self.__pelaajaluettelo:
            if pelaaja._Pelaaja__nimi == nimi:
                return pelaaja

    def joukkue_haku(self):
        joukkueet = []
        for joukkue in self.__pelaajaluettelo:
            if joukkue._Pelaaja__joukkue not in joukkueet:
                joukkueet.append(joukkue._Pelaaja__joukkue)
        return sorted(joukkueet)

    def maat_haku(self):
        maat = []
        for maa in self.__pelaajaluettelo:
            if maa._Pelaaja__kansalaisuus not in maat:
                maat.append(maa._Pelaaja__kansalaisuus)
        return sorted(maat)

    def joukkueen_pelaajien_haku(self, joukkue):
        pelaajat = []
        for pelaaja in self.__pelaajaluettelo:
            if pelaaja._Pelaaja__joukkue in joukkue:
                pelaajat.append(pelaaja)
        return sorted(pelaajat, key=lambda pelaaja: pelaaja._Pelaaja__maalit + pelaaja._Pelaaja__syotot, reverse=True)

    def pelaajien_kansalaisuus_haku(self, kansalaisuus):
        pelaajat = []
        for pelaaja in self.__pelaajaluettelo:
            if pelaaja._Pelaaja__kansalaisuus in kansalaisuus:
                pelaajat.append(pelaaja)
        return sorted(pelaajat, key=lambda pelaaja: pelaaja._Pelaaja__maalit + pelaaja._Pelaaja__syotot, reverse=True)

    def eniten_pisteita_haku(self, kuinka_monta):
        pelaajat = []
        eniten_pisteita = 0
        for pelaaja in self.__pelaajaluettelo:
            if pelaaja._Pelaaja__syotot + pelaaja._Pelaaja__maalit >= eniten_pisteita:
                pelaajat.append(pelaaja)
        # Järjestetään pisteiden perusteella ja jos sama pistemäärä niin maalien määrä ratkaisee
        lista = sorted(self.__pelaajaluettelo, key=lambda pelaaja: (pelaaja._Pelaaja__maalit + pelaaja._Pelaaja__syotot, pelaaja._Pelaaja__maalit), reverse=True)
        return lista[0:int(kuinka_monta)]

    def eniten_maaleja_haku(self, kuinka_monta):
        pelaajat = []
        eniten_pisteita = 0
        for pelaaja in self.__pelaajaluettelo:
            if pelaaja._Pelaaja__syotot + pelaaja._Pelaaja__maalit >= eniten_pisteita:
                pelaajat.append(pelaaja)
        # Järjestetään maalien määrän mukaan ja jos sama määrä niin vähemmän pelattu pelejä mukaan (huom! - merkki järjestyksen kääntämiseksi)
        lista = sorted(self.__pelaajaluettelo, key=lambda pelaaja: (pelaaja._Pelaaja__maalit, -pelaaja._Pelaaja__pelit), reverse=True)
        return lista[0:int(kuinka_monta)]

# -----------------------------------------------------------------------

class Sovellus:
    def __init__(self):
        self.__tiedoston_nimi = ""
        self.__tiedosto =  Tiedostonkasittelija(self.__tiedoston_nimi)
        self.__luettelo = Pelaajaluettelo()

    def ohje(self):
        print("komennot: ")
        print("0 lopeta")
        print("1 hae pelaaja")
        print("2 joukkueet")
        print("3 maat")
        print("4 joukkueen pelaajat")
        print("5 maan pelaajat")
        print("6 eniten pisteitä")
        print("7 eniten maaleja")

    def lue_tiedosto(self):
        self.__tiedoston_nimi = input("tiedosto: ")
        self.__tiedosto = Tiedostonkasittelija(self.__tiedoston_nimi).lataa()
        maara = len(self.__tiedosto)
        print(f'luettiin {maara} pelaajan tiedot')

        # listään tiedostossa olevat pelaajat luetteloon
        for pelaaja in self.__tiedosto:
            nimi = pelaaja['name']
            kansalaisuus = pelaaja['nationality']
            syotot = pelaaja['assists']
            maalit = pelaaja['goals']
            rangaistukset = pelaaja['penalties']
            joukkue = pelaaja['team']
            pelit = pelaaja['games']
            self.__luettelo.lisaa_pelaaja(nimi, kansalaisuus, syotot, maalit, rangaistukset, joukkue, pelit)

    def hae_pelaaja(self):
        nimi = input("nimi: ")
        pelaaja = self.__luettelo.pelaaja_haku(nimi)
        if pelaaja == None:
            print("pelaaja ei tiedossa")
            return
        print(pelaaja)

    def hae_joukkueet(self):
        joukkueet = self.__luettelo.joukkue_haku()
        for joukkue in joukkueet:
            print(joukkue)

    def hae_maat(self):
        maat = self.__luettelo.maat_haku()
        for maa in maat:
            print(maa)

    def hae_joukkueen_pelaajat(self):
        joukkue = input("joukkue: ")
        pelaajat = self.__luettelo.joukkueen_pelaajien_haku(joukkue)
        for pelaaja in pelaajat:
            print(pelaaja)

    def hae_maan_pelaajat(self):
        kansalaisuus = input("maa: ")
        pelaajat = self.__luettelo.pelaajien_kansalaisuus_haku(kansalaisuus)
        for pelaaja in pelaajat:
            print(pelaaja)

    def hae_eniten_pisteita(self):
        kuinka_monta = input("kuinka monta: ")
        pelaajat = self.__luettelo.eniten_pisteita_haku(kuinka_monta)
        for pelaaja in pelaajat:
            print(pelaaja)

    def hae_eniten_maaleja(self):
        kuinka_monta = input("kuinka monta: ")
        pelaajat = self.__luettelo.eniten_maaleja_haku(kuinka_monta)
        for pelaaja in pelaajat:
            print(pelaaja)


    def suorita(self):
        self.lue_tiedosto()
        self.ohje()
        while True:
            print("")
            komento = input("komento: ")
            if komento == "0":
                break
            elif komento == "1":
                self.hae_pelaaja()
            elif komento == "2":
                self.hae_joukkueet()
            elif komento == "3":
                self.hae_maat()
            elif komento == "4":
                self.hae_joukkueen_pelaajat()
            elif komento == "5":
                self.hae_maan_pelaajat()
            elif komento == "6":
                self.hae_eniten_pisteita()
            elif komento == "7":
                self.hae_eniten_maaleja()
            else:
                self.ohje()


sovellus = Sovellus()
sovellus.suorita()
