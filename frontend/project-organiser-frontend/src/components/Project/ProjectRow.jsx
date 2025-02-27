import React from "react";
import moment from "moment";

const ProjectRow = ({project}) => {
    return (
        <tr key = {String(project._id)}> 
            <td>{String(project._id).slice(-4)}</td>
            <td> {project.name}</td>   
            <td> {moment(project.startTime.date).format("YYYY-MM-DD HH:mm")} </td>
            <td> {project.githubUrl} </td>   
        </tr>
    );
};

export default ProjectRow;
