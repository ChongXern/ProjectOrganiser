import { useState, useEffect } from "react";
import ProjectService from "../api/service/projectService";

const useProjects = () => {
    const [projects, setProjects] = useState([]);

    useEffect(() => {
        ProjectService.getAllProjects()
            .then(response => setProjects(response.data))
            .catch(error => console.error("Error fetching projects:", error));
    }, [])

    return projects;
};

export default useProjects;
