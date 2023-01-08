import sqlite3
import random

db = sqlite3.connect("Elokuvat.db")
db.isolation_level = None

db.execute("CREATE TABLE Elokuvat(id INTEGER PRIMARY KEY, nimi TEXT, vuosi int)")

from datetime import datetime

now = datetime.now()
current_time = now.strftime("%H:%M:%S")

print("lisäyksen aloitus", current_time)
#indeksi tehtävä 2

db.execute("BEGIN")
for i in range(1,1000000):
    s = ""
    for x in [chr(random.randint(65,126)) for x in range(1,8)]:
        s = s + x
    a = random.randint(1900, 2000)
    db.execute("INSERT INTO Elokuvat (id, nimi, vuosi) VALUES (?,?,?)", [i, s, a])
db.execute("COMMIT")

now = datetime.now()
current_time = now.strftime("%H:%M:%S")
print("lisäyksen lopetus", current_time)

#indeksi tehtävä 3
db.execute("CREATE INDEX idx_vuosi ON Elokuvat (vuosi)");
print("kyselyn aloitus")
for i in range (1, 1000):
    a = random.randint(1900, 2000)
    db.execute("SELECT Count(*) FROM Elokuvat WHERE vuosi = ?", [a])

now = datetime.now()
current_time = now.strftime("%H:%M:%S")
print("Haun jälkeen", current_time)
