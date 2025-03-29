import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:8080/api", 
    headers: {"ngrok-skip-browser-warning": "true"}
});

const updateProjectImage = (projectId, imageUrl) => {
    axios.patch(`/updateProject/image/${projectId}`, {
        image: imageUrl  // Send JSON body
    })
    .then(response => {
        console.log("Project image updated: ", response.data);
    })
    .catch(error => {
        console.error("Error updating project image:", error);
    });
};


export default instance;