import unittest
from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db
import instaloader
from instaloader import Post, Profile, load_structure_from_file


def testBrownThomas():
    try:
        L = instaloader.Instaloader()
        profile = 'btmenswear'
        posts = instaloader.Profile.from_username(L.context, profile).get_posts()
        print ("Successfully retrieved Instagram posts from Brown Thomas.")
    except:
        print ("Connection to Brown Thomas unsuccessful.")

def testNowhere():
    try:
        L = instaloader.Instaloader()
        profile = 'nowhere.ie'
        posts = instaloader.Profile.from_username(L.context, profile).get_posts()
        print ("Successfully retrieved Instagram posts from Nowhere.")
    except:
        print ("Connection to Nowhere unsuccessful.")

    
if __name__ == '__main__':
    testBrownThomas()
    testNowhere()