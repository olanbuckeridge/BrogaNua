from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import pymysql

conn = pymysql.connect(host='mydbproducts.cnu1e4enw5kt.eu-west-1.rds.amazonaws.com', port=3306, user='olanbuckeridge', passwd='r9mtj6ta', db='mydbproducts')

cur = conn.cursor()
cur.execute("SELECT * FROM products")

items = 0
while items < 216:
	# opening up connection, grabbing the page
	uClient = uReq("https://www.lifestylesports.com/ie/mens-trainers/?sz=72&start={}".format(items))
	page_html = uClient.read()
	uClient.close()

	# html parsing
	page_soup = soup(page_html, "html.parser")

	# grabs each product
	containers = page_soup.findAll("div", {"class":"product-tile with-quick-buy"})

	headers = "brand, model, current_price, sizes_available\n"

	for container in containers:
		brand_container = container.findAll("a", {"class":"name-link"})
		brand = brand_container[0].div.text.strip().split("\n",1)[0]
		name_container = container.findAll("a", {"class":"name-link"})
		if "Mens" in name_container[0].text.strip(): 
			model = name_container[0].text.strip().split("Mens ",1)[1]

		elif "Adults" in name_container[0].text.strip():
			model = name_container[0].text.strip().split("Adults ",1)[1]

		price_container = container.findAll("div", {"class":"product-pricing"})
		current_price = price_container[0].text.strip()
		price = current_price.split("\n")[0]
		image_container = container.findAll("div", {"class":"product-image"})
		image_container[0].find("img")
		img = image_container[0].find("img")
		if img.has_attr('data-src'):
			img_url = img["data-src"]
		else:
			img_url = img['src']
		retailer = "Life Style Sports"

		cur.execute("INSERT INTO products (brand, model, retailer, price, images) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", (brand, model, retailer, price.strip("€"), img_url))
		cur.connection.commit()
	items += 72