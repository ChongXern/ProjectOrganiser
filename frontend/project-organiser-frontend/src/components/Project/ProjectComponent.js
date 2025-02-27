import React from 'react';
import ProjectService from '../../api/service/projectService';
import moment from 'moment';

class ProjectComponent extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            projects: []
        }
    }

    componentDidMount() {
        ProjectService.getAllProjects().then((response) =>{
            console.log(response.data);
            this.setState({ 
                projects: response.data
            })
        });
    }

    render(){
        return (
            <div>
                <h1 className="text-center"> Projects List</h1>
                <table className="table table-striped">
                    <thead>
                        <tr>

                            <td> Project Id</td>
                            <td> Project Name</td>
                            <td> Project Start Time</td>
                            <td> Project Github URL </td>
                        </tr>

                    </thead>
                    <tbody>
                        {
                            this.state.projects.map(project => 
                                <tr key = {String(project._id)}> 
                                    <td>{String(project._id).slice(-4)}</td>
                                    <td> {project.name}</td>   
                                    <td> {moment(project.startTime.date).format("YYYY-MM-DD HH:mm")} </td>
                                    <td> {project.githubUrl} </td>   
                                </tr>
                            )
                        }

                    </tbody>
                </table>
            </div>
        )
    }
}

export default ProjectComponent;
