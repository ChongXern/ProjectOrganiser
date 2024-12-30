import json

with open('projects.json', 'r') as file:
    data = json.load(file)

def generate_id():
    with open("max_id.txt", 'r') as max_id_file:
        #print(max_id_file.readline(1)) # prints 1
        curr_id = int(max_id_file.readline().strip())
        #new_lines.append(line[:-2] + '  "_id": ' + curr_id + ',')
    with open("max_id.txt", 'w') as max_id_file:
        new_id = curr_id + 1
        max_id_file.write(str(new_id))
    return curr_id

if 'projects' in data and isinstance(data['projects'], list):
    for project in data['projects']:
        project['_id'] = generate_id()

        for todo in project.get('todos', []):
            todo['_id'] = generate_id()

        for tutorial in project.get('tutorials', []):
            tutorial['_id'] = generate_id()

            for lesson in tutorial.get('lessons', []):
                lesson['_id'] = generate_id()

    with open('projects.json', 'w') as file:
        json.dump(data, file, indent=2)

    print("Finished adding unique ids")