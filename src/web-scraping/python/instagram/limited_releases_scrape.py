from urllib.request import urlopen as uReq
from bs4 import BeautifulSoup as soup
import re
import datetime
import instaloader
from instaloader import Post, Profile, load_structure_from_file
import firebase_admin
from firebase_admin import credentials
from firebase_admin import db

# Fetch the service account key JSON file contents
cred = credentials.Certificate('broganua-59918-firebase-adminsdk-97dx0-4bb70e5f48.json')
# Initialize the app with a service account, granting admin privileges
firebase_admin.initialize_app(cred, {
    'databaseURL': 'https://broganua-59918.firebaseio.com/'
})
ref = db.reference('/limited_releases')
ref.set('empty')

products = 1

# Get instance
L = instaloader.Instaloader()

# Optionally, login or load session
retailer_no = 1
while retailer_no < 3:
    if retailer_no == 1:
        profile = 'btmenswear'
        posts = instaloader.Profile.from_username(L.context, profile).get_posts()

        for post in posts:
            item_ref = db.reference('/limited_releases/{}'.format(products))
            if ('RAFFLE' in post.caption or 'First come' in post.caption or 'a chance' in post.caption):
                link = "https://www.instagram.com/p/"+post.shortcode
                ig_profile = "@"+profile
                profile_url = "https://www.instagram.com/"+profile
                img_url = post.url
                caption = post.caption
                post_date = post.date.strftime('%H:%M %d/%m/%Y')
                products += 1
                item_ref.set({
                    'ig_profile': ig_profile,
                    'ig_profile_url': profile_url,
                    'ig_image': img_url,
                    'ig_caption': caption,
                    'ig_date': post_date,
                    'ig_link': link
                })

            else:
                pass
        retailer_no += 1
    if retailer_no == 2:
        profile = 'nowhere.ie'
        posts = instaloader.Profile.from_username(L.context, profile).get_posts()

        for post in posts:
            item_ref = db.reference('/limited_releases/{}'.format(products))
            if (post.caption is not None and ('React Element 87' in post.caption or 'ACRONYM x Nike' in post.caption or 'HU NMD' in post.caption)):
                link = "https://www.instagram.com/p/"+post.shortcode
                ig_profile = "@"+profile
                profile_url = "https://www.instagram.com/"+profile
                img_url = post.url
                caption = post.caption
                post_date = post.date.strftime('%H:%M %d/%m/%Y')
                products += 1
                item_ref.set({
                    'ig_profile': ig_profile,
                    'ig_profile_url': profile_url,
                    'ig_image': img_url,
                    'ig_caption': caption,
                    'ig_date': post_date,
                    'ig_link': link
                })
            else:
                pass
        retailer_no += 1
    else:
        retailer_no = 4