import json

with open('projects.json', 'r') as file:
    data = json.load(file)
    
for project in data:
    print(project)