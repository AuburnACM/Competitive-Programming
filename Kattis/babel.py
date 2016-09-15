#!/bin/env python3
"""
Given a dictionary of words from English to some foreign language, read
some text written in the foreign language
"""

lang = dict()

words = input().split()
while len(words) > 1:
    lang[words[1]] = words[0]
    words = input().split()

while True:
    try:
        word = input()
        print(lang[word])
    except KeyError:
        print('eh')
    except Exception:
        break
