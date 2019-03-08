from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup

my_url = 'https://www.lifestylesports.com/ie/mens-trainers/'

# opening up connection, grabbing the page
uClient = uReq(my_url)
page_html = uClient.read()
uClient.close()

# html parsing
page_soup = soup(page_html, "html.parser")

# grabs each product
containers = page_soup.findAll("div", {"class":"product-desc"})

filename = "data/lss_mens_trainers.csv"
f = open(filename, "w")

headers = "brand, product_name, current_price, sizes_available\n"

f.write(headers)

for container in containers:
	brand_container = container.findAll("a", {"class":"name-link"})
	brand = brand_container[0].div.text.strip().split("\n",1)[0]
	name_container = container.findAll("a", {"class":"name-link"})
	if "Mens" in name_container[0].text.strip(): 
		product_name = name_container[0].text.strip().split("Mens ",1)[1]

	elif "Adults" in name_container[0].text.strip():
		product_name = name_container[0].text.strip().split("Adults ",1)[1]

	price_container = container.findAll("div", {"class":"product-pricing"})
	current_price = price_container[0].text.strip()
	sizes_container = container.findAll("div", {"class":"product-sizes-in-stock"})
	sizes_available = sizes_container[0].text.strip()

	print("Brand: " + brand)
	print("Name: " + product_name)
	print("Price: " + current_price)
	print(sizes_available)
	print("*********************")

	f.write(brand + "," + product_name + "," + current_price + "," + sizes_available.replace(","," | ") + "\n")

f.close()