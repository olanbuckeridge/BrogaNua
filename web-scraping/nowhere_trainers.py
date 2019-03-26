from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import pymysql

conn = pymysql.connect(host='mydbproducts.cnu1e4enw5kt.eu-west-1.rds.amazonaws.com', port=3306, user='olanbuckeridge', passwd='r9mtj6ta', db='mydbproducts')

cur = conn.cursor()
cur.execute("SELECT * FROM products")

items = 1
while items < 3:
    uClient = uReq("https://nowhere.ie/collections/footwear?page={}".format(items))
    page_html = uClient.read()
    uClient.close()
    page_soup = soup(page_html, "html.parser")

    containers = page_soup.findAll("div", {"class":"product grid__item medium-up--one-third small--one-half slide-up-animation animated"})
    #image_containers = page_soup.findAll("img")
    #for image_tag in image_containers:
    #    print (image_tag['src'])
    #img_url = image_containers.get("src")

    for container in containers:
        brand_container = container.findAll("div", {"class":"product__title text-center"})
        brand = brand_container[0].text.strip().split(":")[0]
        model_container = container.findAll("div", {"class":"product__title text-center"})
        model = model_container[0].text.strip().split(":")[1]
        if "(" in model:
            model = model.split("(")[0]
        model.strip(" ")
        price_container = container.findAll("div", {"class":"product__prices text-center"})
        current_price = price_container[0].find("span").text.strip()
        pricing = current_price.split("\n")[1].strip("€")
        price_eur = pricing.strip("EUR")
        price = price_eur.strip()
        image_container = container.findAll("div", {"class":"supports-js"})
        img = image_container[0].find("img")
        img_150 = img["src"].strip("//")
        img_url = img_150.replace("150", "540")
        retailer = 'Nowhere'
        '''print ("BRAND:",brand)
        print ("MODEL:",model)
        print ("PRICE:",price)
        print ("IMAGE:",img_url)
        print ("**************************")'''
        cur.execute("INSERT INTO products (brand, model, retailer, price, images) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", (brand, model, retailer, price, img_url))
        cur.connection.commit()

    #print (src)
    items += 1