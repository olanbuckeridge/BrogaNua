from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import pymysql

conn = pymysql.connect(host='localhost', port=3306, user='root', passwd='r9mtj6ta', db='products')

cur = conn.cursor()
cur.execute("SELECT * FROM products")

# Opening connection, grabbing the page
items = 0
while items < 265:
    uClient = uReq("https://www.brownthomas.com/men/shoes/trainers/?sz=12&productsearch=true&start={}&format=page-element&viewall=true".format(items))
    page_html = uClient.read()
    uClient.close()
    page_soup = soup(page_html, "html.parser")

    containers = page_soup.findAll("li", {"class":"grid-tile"})
    image_containers = page_soup.findAll("img")
    #for image_tag in image_containers:
    #    print (image_tag['src'])
    #img_url = image_containers.get("src")

    for container in containers:
        brand_container = container.findAll("span", {"class":"product-brand"})
        brand = brand_container[0].text.strip()
        model_container = container.findAll("span", {"class":"product-name name-link"})
        model = model_container[0].text.strip()
        price_container = container.findAll("span", {"class":"product-sales-price"})
        price = price_container[0].text.strip()
        image_container = container.findAll("img", {"class":"js-product-image-img"})
        img_url = image_container[0]['src']
        retailer = 'Brown Thomas'
        #img_url = image_container[0].text.strip()
        '''print ("BRAND:",brand)
        print ("MODEL:",model)
        print ("PRICE:",price)
        print ("IMAGE:",img_url)
        print ("**************************")'''
        cur.execute("INSERT INTO products (brand, model, retailer, price, images) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", (brand, model, retailer, price.strip("€"), img_url))
        cur.connection.commit()

    #print (src)
    items += 12