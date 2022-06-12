#!/usr/bin/env python
# coding: utf-8

# In[90]:


import re
import tweepy as tw
from tweepy import OAuthHandler
from datetime import datetime, timedelta
import pandas as pd
import configparser


# In[99]:


# read config
config = configparser.ConfigParser()
config.read('config.ini')

api_key = config['twitter']['api_key']
api_key_secret = config['twitter']['api_key_secret']

access_token = config['twitter']['access_token']
access_token_secret = config['twitter']['access_token_secret']

# authenticate
auth = tweepy.OAuthHandler(api_key, api_key_secret)
auth.set_access_token(access_token, access_token_secret)

api = tweepy.API(auth)
    
import datetime
today = datetime.date.today()
date_since = today-datetime.timedelta(days=7)
tweets = tweepy.Cursor(api.search_tweets,
              q="#keluhanmasyarakat -filter:retweets",
              lang="id",
              since=date_since,
              tweet_mode='extended').items(15)

# Collect a list of tweets
tweetss = [tweet.full_text for tweet in tweets]
tweetss
        
        
tweets = tw.Cursor(api.search_tweets,
                           q="#keluhanmasyarakat -filter:retweets",
                           lang="id",
                           since=date_since).items(15)

users_locs = [[tweet.user.screen_name] for tweet in tweets]
users_locs


# In[105]:


import pandas as pd

tweet_text = pd.DataFrame(data=users_locs,
                    columns=['Username'])
tweet_text.insert(1, "Caption", tweetss, True)
tweet_text
databaru = tweet_text


# In[ ]:




