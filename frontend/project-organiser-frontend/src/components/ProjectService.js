import axios from 'axios'

const PROJECTS_REST_API_URL = "http://localhost:8080/api/v1/projects";

class ProjectService {
    getAllProjects() {
        return axios.get(PROJECTS_REST_API_URL);
    }

    getProjectByGithubUsernameAndId(githubUsername, id) {
        return axios.get(`${PROJECTS_REST_API_URL}/id/${githubUsername}/${id}`);
    }

    getProjectByGithubUsernameAndRepoName(githubUsername, repoName) {
        return axios.get(`${PROJECTS_REST_API_URL}/repo/${githubUsername}/${repoName}`);
    }

    getTutorials(githubUsername, repoName) {
        return axios.get(`${PROJECTS_REST_API_URL}/repo/${githubUsername}/${repoName}/tutorials`);
    }

    getAllProjectsSortedByName(){
        return axios.get(`${PROJECTS_REST_API_URL}/sortedName`);
    }

    getProjectsByStatus(status){
        return axios.get(`${PROJECTS_REST_API_URL}/status/${status}`)
    }

    createProject(project) {
        return axios.post(`${PROJECTS_REST_API_URL}/createProject`);
    }

    updateProject(projectId, newProject) {
        return axios.put(`${PROJECTS_REST_API_URL}/updateProject/${projectId}`, newProject);
    }

    deleteProject(projectId) {
        return axios.delete(`${PROJECTS_REST_API_URL}/deleteProject/${projectId}`);
    }

    updateProjectName(projectId, name) {
        let encodedName = encodeURIComponent(name); //safely converts special characters (e.g. space to %20)
        return axios.patch(`${PROJECTS_REST_API_URL}/updateProject/name/${projectId}?name=${encodedName}`);
    }

    updateProjectStatus(projectId, status) {
        let encodedStatus = encodeURIComponent(status);
        return axios.patch(`${PROJECTS_REST_API_URL}/updateProject/status${projectId}?status=${encodedStatus}`);
    }

    updateProjectLastUpdated(projectId) {
        return axios.patch(`${PROJECTS_REST_API_URL}/updateProject/last_updated/${projectId}`);
    }

    updateProject(projectId, updates) {
        return axios.patch(`${PROJECTS_REST_API_URL}/updateProject/any/${projectId}`, updates);
    }
}

export default new ProjectService();