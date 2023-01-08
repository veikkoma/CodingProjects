class  Lukutilasto:
    def __init__(self):
        self.luvut = []
 
    def lisaa_luku(self, luku:int):
        self.luvut.append(luku)
 
    def lukujen_maara(self):
        return len(self.luvut)
 
    def summa(self):
        return sum(self.luvut)
 
    def keskiarvo(self):
        if not self.luvut:
            return 0.0
        else:
            return self.summa() / self.lukujen_maara()
 
tilasto = Lukutilasto()
parilliset = Lukutilasto()
parittomat = Lukutilasto()
while True:
    luku = int(input("Anna luku: "))
    if luku == -1:
        break
 
    tilasto.lisaa_luku(luku)
    if luku % 2 == 0:
        parilliset.lisaa_luku(luku)
    else:
        parittomat.lisaa_luku(luku)
 
print("Summa:", tilasto.summa())
print("Keskiarvo:", tilasto.keskiarvo())
print("Parillisten summa:", parilliset.summa())
print("Parittomien summa:", parittomat.summa())
 
