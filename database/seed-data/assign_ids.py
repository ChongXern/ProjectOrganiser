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

if isinstance(data, list):
    for project in data:
        if '_id' not in project or project['_id'] is None: project['_id'] = generate_id()

        for todo in project.get('todos', []):
            if '_id' not in todo or todo['_id'] is None: todo['_id'] = generate_id()

        for tutorial in project.get('tutorials', []):
            if '_id' not in tutorial or tutorial['_id'] is None: tutorial['_id'] = generate_id()

            for lesson in tutorial.get('lessons', []):
                if '_id' not in lesson or lesson['_id'] is None: lesson['_id'] = generate_id()

    with open('projects.json', 'w') as file:
        json.dump(data, file, indent=2)

    print("Finished adding unique ids")
else:
    print("Something wrong happened.")