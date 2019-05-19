from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

# Fetch the service account key JSON file contents
cred = credentials.Certificate('broganua-59918-firebase-adminsdk-97dx0-4bb70e5f48.json')
# Initialize the app with a service account, granting admin privileges
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://broganua-59918.firebaseio.com/'
})
ref = db.reference('/products')

items = 0
i = 1
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
		item_ref = db.reference('/products/life_style_sports/{}'.format(i))		
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
		item_ref.set ({
            'brand': brand,
            'model': model,
            'retailer': retailer,
            'price': price.strip('€'),
            'image': img_url
        })
		i += 1

	items += 72