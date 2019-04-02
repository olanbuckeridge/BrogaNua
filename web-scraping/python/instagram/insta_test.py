from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import instaloader
from instaloader import Post, Profile, load_structure_from_file

# Get instance
L = instaloader.Instaloader()

# Optionally, login or load session
L.login('olanbuckeridge', 'Hardyz12')

posts = instaloader.Profile.from_username(L.context, 'btmenswear').get_posts()

for post in posts:
    if ('RAFFLE' in post.caption):
        print (post.url)
    else:
        print ("Not Limited.")