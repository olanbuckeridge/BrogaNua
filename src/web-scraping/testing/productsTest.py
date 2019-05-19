import unittest
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

def testLifeStyleSports():
    try:
        # opening up connection, grabbing the page
        uClient = uReq("https://www.lifestylesports.com/ie/mens-trainers/?sz=72&start=0")
        page_html = uClient.read()
        uClient.close()
        print ("Connection to Life Style Sports successful.")
    except:
        print ("Connection to Life Style Sports unsuccessful.")

def testBrownThomas():
    try:
        # opening up connection, grabbing the page
        uClient = uReq("https://www.brownthomas.com/men/shoes/trainers/?sz=300&productsearch=true&start=0&format=page-element&viewall=true")
        page_html = uClient.read()
        uClient.close()
        print ("Connection to Brown Thomas successful.")
    except:
        print ("Connection to Brown Thomas unsuccessful.")

def testNowhere():
    try:
        # opening up connection, grabbing the page
        uClient = uReq("https://nowhere.ie/collections/footwear?page=0")
        page_html = uClient.read()
        uClient.close()
        print ("Connection to Nowhere successful.")
    except:
        print ("Connection to Nowhere unsuccessful.")

def testElverys():
    try:
        # opening up connection, grabbing the page
        uClient = uReq("https://www.elverys.ie/elverys/en/Elverys/Men/c/2296?q=%3Arelevance%3AcategoryCode%3A2514%3AtypeCode%3A2516&show=All")
        page_html = uClient.read()
        uClient.close()
        print ("Connection to Elverys successful.")
    except:
        print ("Connection to Elverys unsuccessful.")
    
if __name__ == '__main__':
    testLifeStyleSports()
    testBrownThomas()
    testNowhere()
    testElverys()