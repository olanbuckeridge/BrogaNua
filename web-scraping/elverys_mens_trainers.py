from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import pymysql

'''conn = pymysql.connect(host='localhost', port=3306, user='root', passwd='r9mtj6ta', db='products')

cur = conn.cursor()
cur.execute("SELECT * FROM products")'''

# Opening connection, grabbing the page
items = 0
while items < 6:
    uClient = uReq("https://www.elverys.ie/elverys/en/Elverys/Men/c/2296?q=%3Arelevance%3AcategoryCode%3A2514%3AtypeCode%3A2516&page={}".format(items))
    page_html = uClient.read()
    uClient.close()
    page_soup = soup(page_html, "html.parser")

    containers = page_soup.findAll("div", {"class":"product-box grid-prdt"})
    #image_containers = page_soup.findAll("img")
    #for image_tag in image_containers:
    #    print (image_tag['src'])
    #img_url = image_containers.get("src")
    for container in containers:
        brand_container = container.findAll("h4")
        brand = brand_container[0].text.strip()
        print ("Brand:",brand)
        model_container = container.findAll("a",{"class":"prdt-name"})
        model = model_container[0].text.strip()
        print (model)

    #print (containers)
    items += 1