import requests
from bs4 import BeautifulSoup
import re
import sys

def crawl(url):
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    
    if "youtube.com" in url:
        pattern = re.compile(r'(?<=<title>)(.*?)(?=</title>)')
        title = pattern.findall(str(soup))
        if title:
            return title[0]
    else:
        output = soup.find('h1')
        if output:
            return output.get_text(strip=True)
    return ""

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Please provide a URL.")
        sys.exit(1)

    url = sys.argv[1]
    print(crawl(url))