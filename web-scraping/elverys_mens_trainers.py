from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import pymysql

conn = pymysql.connect(host='localhost', port=3306, user='root', passwd='r9mtj6ta', db='products')

cur = conn.cursor()
cur.execute("SELECT * FROM products")

# Opening connection, grabbing the page
uClient = uReq("https://www.elverys.ie/elverys/en/Elverys/Men/c/2296?q=%3Arelevance%3AcategoryCode%3A2514%3AtypeCode%3A2516&show=All")
page_html = uClient.read()
uClient.close()
page_soup = soup(page_html, "html.parser")

containers = page_soup.findAll("div", {"class":"col-sm-3 loadelement"})

brands = ['Nike','Puma','Converse','adidas','New Balance','Timberland','Skechers']
for container in containers:
    brand_container = container.findAll("h4")
    brand = brand_container[0].text.strip()

    model_container = container.findAll("a",{"class":"prdt-name"})
    model = model_container[0].text.strip()
    for i in range(len(brands)):
        if brands[i] in model:
            model = model.split(brands[i],1)[1]
            model = model.split(",",1)[0]
            model = model.split("Men",1)[0]

    price_container = container.findAll("div",{"class":"prdt-price"})
    sale_container = price_container[0].find("span")
    if sale_container != None:
        price = sale_container.text.strip()
    else:
        price = price_container[0].text.strip()

    image_container = container.findAll("a",{"class":"prdt-img"})
    image = image_container[0].find("img")
    img_url = "https://www.elverys.ie" + image["src"]
    retailer = 'Elverys'

    cur.execute("INSERT INTO products (brand, model, retailer, price, images) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", (brand, model, retailer, price.strip("€"), img_url))
    cur.connection.commit()