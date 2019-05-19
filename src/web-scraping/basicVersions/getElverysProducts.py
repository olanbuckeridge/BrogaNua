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

# Opening connection, grabbing the page
uClient = uReq("https://www.elverys.ie/elverys/en/Elverys/Men/c/2296?q=%3Arelevance%3AcategoryCode%3A2514%3AtypeCode%3A2516&show=All")
page_html = uClient.read()
uClient.close()
page_soup = soup(page_html, "html.parser")

containers = page_soup.findAll("div", {"class":"col-sm-3 loadelement"})

items = 1
brands = ['Nike','Puma','Converse','adidas','New Balance','Timberland','Skechers']
for container in containers:
    #item_ref = db.reference('/products/elverys/{}'.format(items))
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

    link_container = container.findAll('a', {"class":"prdt-img"})
    prod_url = link_container[0]["href"]
    print (prod_url)
    
    retailer = 'Elverys'
    '''item_ref.set ({
        'brand': brand,
        'model': model,
        'retailer': retailer,
        'price': price.strip('€'),
        'image': img_url
    })
    items += 1'''