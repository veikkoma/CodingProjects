import sqlite3

db = sqlite3.connect("bikes.db")
db.isolation_level = None

def distance_of_user(user):
    conn = sqlite3.connect('bikes.db')
    cursor = conn.execute ("select sum(t.distance) from Trips t, Users u where u.name = ? and u.id=t.user_id",[user])
    return cursor.fetchone()[0]

def speed_of_user(user):
    conn = sqlite3.connect('bikes.db')
    cursor = conn.execute("SELECT sum(t.distance),sum(t.duration) FROM Trips t, USERS u where u.name = ? and u.id = t.user_id", [user])
    muuttuja = cursor.fetchone()
    return round((muuttuja[0]/1000)/(muuttuja[1]/60),2)

def duration_in_each_city(date):
    conn = sqlite3.connect('bikes.db')
    cursor = conn.execute("SELECT C.name, SUM(duration) FROM Bikes B, Cities C, Trips T WHERE B.city_id = C.id AND B.id = T.bike_id AND T.day=? GROUP BY C.name", [date])
    return cursor.fetchall()

def users_in_city(city):
    conn = sqlite3.connect('bikes.db')
    cursor = conn.execute("SELECT COUNT(DISTINCT T.USER_ID) FROM TRIPS T, BIKES B, CITIES C WHERE C.ID = B.CITY_ID AND B.ID = T.BIKE_ID AND C.NAME = ?",[city])
    return cursor.fetchone()[0]

def trips_on_each_day(city):
    conn = sqlite3.connect('bikes.db')
    cursor = conn.execute("SELECT t.day, count(t.day) FROM Trips t JOIN Cities c on c.id = s.city_id AND c.name = ? JOIN Stops s on s.id = t.from_id GROUP BY t.day", [city])
    return cursor.fetchall()


def most_popular_start(city):
    conn = sqlite3.connect('bikes.db')
    cursor = conn.execute("SELECT S.NAME, COUNT(T.FROM_ID) FROM TRIPS T, STOPS S, CITIES C WHERE T.FROM_ID = S.ID AND C.NAME = ? AND S.CITY_ID = C.ID GROUP BY S.NAME ORDER BY COUNT(T.FROM_ID) DESC", [city])
    return cursor.fetchone()
