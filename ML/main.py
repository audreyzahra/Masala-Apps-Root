#!/usr/bin/env python
# coding: utf-8

import re
import tweepy as tw
from tweepy import OAuthHandler
from datetime import datetime, timedelta
import pandas as pd
import configparser
import datetime
import pymysql


# read config
config = configparser.ConfigParser()
config.read('config.ini')

api_key = config['twitter']['api_key']
api_key_secret = config['twitter']['api_key_secret']

access_token = config['twitter']['access_token']
access_token_secret = config['twitter']['access_token_secret']

# authenticate
auth = tw.OAuthHandler(api_key, api_key_secret)
auth.set_access_token(access_token, access_token_secret)

api = tw.API(auth)
    
today = datetime.date.today()
date_since = today-datetime.timedelta(days=7)
tweets = tw.Cursor(api.search_tweets,
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


tweet_text = pd.DataFrame(data=users_locs,
                    columns=['Username'])
tweet_text.insert(1, "Caption", tweetss, True)
tweet_text
databaru = tweet_text


try: 
    # Connect to the database
    connection = pymysql.connect(host='34.101.161.166',
                                port=3306,
                                user='masala',
                                password='',
                                db='masala_apps')

    cursor=connection.cursor()

    # creating column list for insertion
    cols = "`,`".join([str(i) for i in databaru.columns.tolist()])

    # Insert DataFrame recrds one by one.
    for i,row in databaru.iterrows():
        sql = "INSERT INTO `ScriptCrawling` (`" +cols + "`) VALUES (" + "%s,"*(len(row)-1) + "%s)"
        cursor.execute(sql, tuple(row))

        # the connection is not autocommitted by default, so we must commit to save our changes
        connection.commit()

    # Execute query
    sql = "SELECT * FROM `ScriptCrawling`"
    cursor.execute(sql)
    # Fetch all the records
    result = cursor.fetchall()
    for i in result:
        print(i)

except NameError:
    print ("NameError occurred. Some variable isn't defined.")

finally:
    # close the database connection using close() method.
    connection.close()
