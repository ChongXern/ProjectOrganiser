import React, {useState} from 'react';
import ProjectService from '../../api/service/projectService';

const ProjectForm = ({onProjectAdded}) =>{
    //state mgmt via useState
    const [formData, setFormData] = useState({
        name: '',
        githubUsername: '',
        githubUrl: '',
        githubLastCommit: '',
    });

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    }

    const handleSubmit = async(e) =>{
        e.preventDefault();
        const response = await ProjectService.createProject(formData);
        onProjectAdded(response.data); // Update project list in UI
        setFormData({ name: "", githubUsername: "", githubUrl: "", githubLastCommit: "" }); // Reset form
    }

    return (
        <form onSubmit={handleSubmit}>
            <div>
                <label>Project Name:</label>
                <input
                    type="text"
                    name="Name: "
                    value={formData.name}
                    onChange={handleChange}
                    required
                />
            </div>
            <div>
            <label>Github Username:</label>
                <input
                    type="text"
                    name="Github Username: "
                    value={formData.githubUsername}
                    onChange={handleChange}
                    required
                />
            </div>
            
            <div>
            <label>Github URL:</label>
                <input
                    type="text"
                    name="Github URL: "
                    value={formData.githubUrl}
                    onChange={handleChange}
                    required
                />
            </div>

            <div>
            <label>Github Last Commit:</label>
                <input
                    type="text"
                    name="Github Last Commit code: "
                    value={formData.githubLastCommit}
                    onChange={handleChange}
                    required
                />
            </div>

            <button type="submit">Create Project</button>
        </form>
    )
}

export default ProjectForm;