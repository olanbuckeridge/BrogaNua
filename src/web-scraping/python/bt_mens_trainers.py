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

'''import pymysql

conn = pymysql.connect(host='mydbproducts.cnu1e4enw5kt.eu-west-1.rds.amazonaws.com', port=3306, user='olanbuckeridge', passwd='r9mtj6ta', db='mydbproducts')

cur = conn.cursor()
cur.execute("SELECT * FROM products")'''

# Opening connection, grabbing the page
items = 0
i = 1
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
        item_ref = db.reference('/products/brown_thomas/{}'.format(i))
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
        #cur.execute("INSERT INTO products (brand, model, retailer, price, images) VALUES (\"%s\", \"%s\", \"%s\", \"%s\", \"%s\")", (brand, model, retailer, price.strip("€"), img_url))
        #cur.connection.commit()
        item_ref.set ({
            'brand': brand,
            'model': model,
            'retailer': retailer,
            'price': price,
            'image': img_url
        })
        i += 1

    #print (src)
    items += 12