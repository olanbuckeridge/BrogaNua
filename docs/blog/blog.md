<h1><b>CA400 Irish Sneakers Application Blog</b></h1>

My blog for this project can be found at https://olanca400.wordpress.com/

<h1><b> The Beginning </b></h1>

Thanks for joining me!

The project has started. I met with Gareth Jones a couple of times to discuss my project ideas and refined what I will be tackling.

I had my project proposals too where my project idea was approved last week and am ready to begin work.

The main focus over the next week or two is researching everything that will be going into the application so I can put together my functional specification.
<h2><b>Idea</b></h2>
<span style="font-weight: 400;">The idea for my app is an Irish sneaker based app. As a sneakerhead it is an area that I’m very passionate about. I am constantly getting messages off people I know and people off Instagram wondering where to get certain pairs of sneakers. I want to create an app that offers consumers a place to find their sneakers without having to crawl through multiple sites. I want to crawl the websites of Irish retailers for their sneakers and compile pages for different brands/ models. The app will show you wear the nearest retailer to purchase the sneakers you want and show you lowest prices. The app will be broken up into sections - Catalog, Hyped Releases and Community.</span>

<span style="font-weight: 400;">The Catalog section will contain data scraped from Irish Retailers allowing one channel for consumers to shop for their sneakers.</span>

<span style="font-weight: 400;">The Hyped Releases will be the very limited sneakers that are raffled off and available through competitions. There is normally one or two big drops a month and unless you’re checking retailers instagrams regularly, you won’t know about releases. I want to ensure all Irish sneakerheads get a chance at these sneakers.</span>

<span style="font-weight: 400;">The Community section will contain information about events within the community and to show off local creatives and talent.</span>

<h1><b>Functional Specification</b></h1>

I managed to get the Functional Specification completed by Week 10 - November 27th.

I discussed the draft of the spec with Gareth Jones and he pointed out some areas to add to and improve on.

![Functional Spec](https://olanca400.files.wordpress.com/2019/01/Functional-Spec.jpeg)

It was a good feeling to get it finished as I put quite a bit of research into how the application would operate and I feel now I have greater understanding of the architecture and interface.

<h1><b>Setting up Gitlab - managing devices</b></h1>

![GitLab](https://olanca400.files.wordpress.com/2019/01/GitLab.png)


Last year for my 3rd Year Project, I used Windows 10 to develop my application and did virtually all the work on my desktop.

This year I have installed Ubuntu on my desktop and my laptop to allow me to work at home, in the library or while travelling.

I now have my GitLab up and running between the two devices and have started to work on the development. I have also setup a Dropbox to manage images/ files to help with maintaining this blog.

<h1><b>App Drawer</b></h1>

Exams are starting to get close and my focus is drawn to them but I decided to try and begin development as I don’t want to fall too far behind schedule.

![App Drawer](https://olanca400.files.wordpress.com/2019/01/App-Drawer.jpeg?w=1200&h=800)

I started off by adding a basic app drawer to navigate between different activities. This allows me to further develop each section. I have been working on the Back-End trying to work on web scraping but it’s nice to take a break and see some visual improvements on the Front-End from time to time.

Although I am focusing on exams – I am going to try and commit 2 hours a day to continue developing on the application while in exam period as I don’t want to fall behind.


<h1><b>Login & Registration</b></h1>

Since my last post I put in a lot of work trying to get some of the back-end functioning.

It took a while but I now have registration, login, email verification, forgot password etc all set up and working using Firebase in my application.

You can see the current registration screen below which allows users to enter their: Name, Email, Password, Shoe Size and Profile Picture. This is all stored in a database on Firebase.

![Registration](https://olanca400.files.wordpress.com/2019/02/screenshot_20190218-155032.jpg?w=381&h=804)


<h1><b>User Profile V1</b></h1>

Once I got the User Authentication working for the Login & Registration. I set up a Database for the User Profile.

It allows the application to retrieve data from the Firebase – Database based on the UserID. The User can then edit their profile on the Database and it is updated in real-time.

There is a lot of room to improve in terms of the User Interface – but getting the back-end working is my priority right now.

![Profile](https://olanca400.files.wordpress.com/2019/02/screenshot_20190218-155010.jpg?w=381&h=804)

<h1><b>Meeting with Gareth - Planning</b></h1>

Today I met with my supervisor, Gareth Jones.

We discussed the ethical approval that is required for the application and the extent of user data that will be obtained.

While I now have a lot of the front-end technology up and running and have started web-scraping the Irish retailers for the catalogue section, I feel like I’m not as far ahead in the application that I would like to be.

Myself and Gareth decided that my next plan of action is to focus on building a well designed Database that is flexible and easy to retrieve information from. It is important to ensure that the design and structure is well thought out to store all the products from retailers.

Once the database is functional, I will be focusing on connecting to the application and displaying the information. This is an area I have no experience in and will be tough to implement.

There is a lot to do in the next few weeks but I’m optimistic after speaking to Gareth that I am on track to get the application completed.


<h1><b>Building my Database – LSS</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/lss_scrape.png)


I have began building my database starting with scraping data from the Irish retailer ‘Life Style Sports’.

This is my first time crawling and scraping websites so I had to build a few test programs to fully understand where to find the information I’m looking for and selecting what data I want in my database.

I am using Python 3.7 with the BeautifulSoup module to retrieve the information from the websites. The data I am grabbing as seen in the code is: Brand, Model, Price,Image and Retailer.

<h1><b>Building my Database – BT</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/bt_scrape.png)

After successfully completing my 'Life Style Sports' web scrape, I decided to focus on 'Brown Thomas'.

The structure of the website was quite similar to the 'Life Style Sports' website therefore it was quite easy to grab the same data required to populate the database.

It was much easier to extract the model information and the images as the information was defined clearly in the the Javascript and there was less formatting required.

<h1><b>Building my Database – Nowhere</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/nowhere_scrape.png)

As before with the 'Life Style Sports' and 'Brown Thomas' data. It was a similar process to scrape from 'Nowhere'.

There were two issues I had with retrieving information:

1. There is no structure for the brand and model text. The whole product name is one string. I had to format the text in order to extract the Brand and Model separately. Once I had the model it required further formatting for particular cases.

2. Another issue I had was with scraping the product images. They use Shopify as their web platform where the images are all stored on their servers. The images also have different resolutions therefore I was able to format the image URL to retrieve the quality I want to display.

<h1><b>Building my Database – Elverys</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/elverys_scrape.png)

‘Elverys’ was the last retailer I looked at while finalising my database.

For the most part, structurally the website was similar to ‘Life Style Sports’. The only issue I had with this retailer was yet again, the model. I had to create a list of all the brands that are stocked at ‘Elverys’ and format the models based on brands and strip the excess information in order to conform with the rest of the database.

<h1><b>MySQL – Database</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/mysql.png?w=770)

Now that I have all the retailer information that I require, I am storing the data in a MySQL database.

Currently the data is stored locally on my desktop computer but the plan is to host the database on Amazon Web Services so that the information can be accessed without relying on my desktop running 24/7

<h1><b>Switching MySQL to Firebase</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/firebase-products.png)

This last week was spent trying to connect my MySQL database to my android application in order to display the information and build my catalogue.

Authentication for the application is stored and run through Firebase so I decided to host my database on Firebase too. It is structured slightly differently to MySQL but after reading the documentation it took a day or two to switch my python programs to store the information on my Firebase.

I think that this option will work better with the Android application and the documentation provided is a lot more comprehensive for my use case.

<h1><b>Working Catalogue!</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/screenshot_20190329-212754.jpg?w=381)

Since switching to Firebase, I have been able to get a basic catalogue working.

The python program is extracting the information for the database. The Android app is then retrieving the information from the Firebase and displaying the information using a RecyclerView. The Brand, Model and Price are displayed in TextViews and the ImageURL is displayed on the right with an ImageView.

There is a lot of work to be done on the Front-End but I am delighted that I can finally see some of the hard work displayed.

<h1><b>Working on the Limited Releases</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/insta_scrape.png)

After connecting the Firebase to the application and having a basic functioning catalogue, I decided to work on the Limited Releases section.

The idea behind this section is to retrieve the products that are limited, find out where to purchase them, when and how.

There are two retailers that have access to limited sneakers in Ireland, Brown Thomas and Nowhere. Since these releases are limited – they never reach the websites and are strictly in-store. They release information on how to purchase theses sneakers through their Instagram channels.

I am using Python 3.7 with the Instaloader module to retrieve the information.

<h1><b>Limited Releases - Functional</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/screenshot_20190403-073357.jpg?w=381)

The information from the python program is being stored on Firebase for the limited releases. I have used a similar RecyclerView setup as in the Catalogue section to display the information regarding the releases.

As you can see on the left hand side it displays the profile that the information is from along with the information on how to purchase the sneakers. On the right hand side you can see the image of the sneakers that will be releasing.

I need to refine the information that I am extracting, for example I need to filter out events which can be seen above.

<h1><b>Refining Catalogue – Search & Colours</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/screenshot_20190408-103730.jpg?w=381)

Once I got the functionality of the Limited Releases working, my focus has turned on refining the catalogue section of the application.

I changed the colour scheme so that the images and text look a lot cleaner in the application. I also changed the alignment and spacing of the image, text and button.

![Code](https://olanca400.files.wordpress.com/2019/04/search.png?w=770)

Adding Search functionality of the database was very important for navigation and user experience. It took a few days to get this up and running as I was having issues retrieving filtered search list and displaying the information in the application.

<h1><b>Database – Structure</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/firebase.png?w=770)

Currently the structure of the Firebase Database is split into three different sections. Limited Releases, Products and Users.

Limited Releases are populated using Python to extract data from Instagram such as Images and Information about new releases.

Products are populated using Python to extract data from Irish retailers such as Brown Thomas, Life Style Sports, Elverys and Nowhere. This area stores the Brand, Model, Price, URL, Image etc.

Users are populated from within the Android application when the users are signing up or editing their profile.

<h1><b>Scheduling – Updating Catalogue</b></h1>

![Code](https://olanca400.files.wordpress.com/2019/04/task.png?w=770)

I am using Windows Task Scheduler to run my python program daily at 08:00.

This was setup in order to provide up to date information in the catalogue for the users regarding new releases or if products sold out.

By setting this up, it allows me not to worry about running the program myself to repopulate the database everyday.

