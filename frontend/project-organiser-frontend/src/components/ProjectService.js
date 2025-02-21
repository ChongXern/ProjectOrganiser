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
}

export default new ProjectService();