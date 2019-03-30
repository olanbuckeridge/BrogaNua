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

products = 1

retailer_no = 1

while retailer_no < 5:
    if retailer_no == 1:
        uClient = uReq("https://www.brownthomas.com/men/shoes/trainers/?sz=300&productsearch=true&start=0&format=page-element&viewall=true")
        page_html = uClient.read()
        uClient.close()
        page_soup = soup(page_html, "html.parser")

        containers = page_soup.findAll("li", {"class":"grid-tile"})

        for container in containers:
            item_ref = db.reference('/products/{}'.format(products))

            brand_container = container.findAll("span", {"class":"product-brand"})
            brand = brand_container[0].text.strip()

            model_container = container.findAll("span", {"class":"product-name name-link"})
            model = model_container[0].text.strip()

            price_container = container.findAll("span", {"class":"product-sales-price"})
            price = price_container[0].text.strip()

            image_container = container.findAll("img", {"class":"js-product-image-img"})
            img_url = image_container[0]['src']

            url_container = container.findAll("a", {"class":"thumb-link js-thumb-link js-product-url"})
            prod_url = url_container[0]['href']

            retailer = 'Brown Thomas'

            item_ref.set ({
                'brand': brand,
                'model': model,
                'retailer': retailer,
                'price': price,
                'image': img_url,
                'link': prod_url
            })
            products += 1
        retailer_no += 1

    if retailer_no == 2:
        items = 0
        while items < 150:
            # opening up connection, grabbing the page
            uClient = uReq("https://www.lifestylesports.com/ie/mens-trainers/?sz=72&start={}".format(items))
            page_html = uClient.read()
            uClient.close()

            # html parsing
            page_soup = soup(page_html, "html.parser")

            # grabs each product
            containers = page_soup.findAll("div", {"class":"product-tile with-quick-buy"})

            for container in containers:
                item_ref = db.reference('/products/{}'.format(products))		
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

                link_container = container.findAll("a", {"class":"thumb-link thumb-quick-buy"})
                prod_url = link_container[0]['href']

                retailer = "Life Style Sports"
                item_ref.set ({
                    'brand': brand,
                    'model': model,
                    'retailer': retailer,
                    'price': price,
                    'image': img_url,
                    'link': prod_url
                })
                products += 1

            items += 72
        retailer_no += 1

    if retailer_no == 3:
        items = 1
        while items < 3:
            uClient = uReq("https://nowhere.ie/collections/footwear?page={}".format(items))
            page_html = uClient.read()
            uClient.close()
            page_soup = soup(page_html, "html.parser")

            containers = page_soup.findAll("div", {"class":"product grid__item medium-up--one-third small--one-half slide-up-animation animated"})

            for container in containers:
                item_ref = db.reference('/products/{}'.format(products))		
                
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
                img_url = "https://" + img_150.replace("150", "540")

                link_container = brand_container[0].find("a")
                prod_url = "https://nowhere.ie"+link_container['href']

                retailer = 'Nowhere'
                item_ref.set ({
                    'brand': brand,
                    'model': model,
                    'retailer': retailer,
                    'price': price,
                    'image': img_url,
                    'link': prod_url
                })
                products += 1

            items += 1
        retailer_no += 1

    if retailer_no == 4:
       # Opening connection, grabbing the page
        uClient = uReq("https://www.elverys.ie/elverys/en/Elverys/Men/c/2296?q=%3Arelevance%3AcategoryCode%3A2514%3AtypeCode%3A2516&show=All")
        page_html = uClient.read()
        uClient.close()
        page_soup = soup(page_html, "html.parser")

        containers = page_soup.findAll("div", {"class":"col-sm-3 loadelement"})

        brands = ['Nike','Puma','Converse','adidas','New Balance','Timberland','Skechers']
        for container in containers:
            item_ref = db.reference('/products/{}'.format(products))
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

            link_container = container.findAll("a", {"class":"prdt-img"})
            prod_url = "https://www.elverys.ie" + link_container[0]["href"]

            retailer = 'Elverys'
            item_ref.set ({
                'brand': brand,
                'model': model,
                'retailer': retailer,
                'price': price,
                'image': img_url,
                'link': prod_url
            })
            products += 1 
        retailer_no += 1
    else:
        retailer_no = 5            