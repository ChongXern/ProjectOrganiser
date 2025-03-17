import React from 'react';
import ProjectService from '../../api/service/projectService';
import moment from 'moment';

import styles from './MainPage.module.css';
import {getImageUrl} from "../../utils";

class ProjectComponent extends React.Component {
    constructor(props) { //init component state
        super(props); 
        this.state = {
            projects: []
        }
    }

    //lifecycle method: executed after object mounted to dom
    componentDidMount() {
        // api called to fetch all projects
        ProjectService.getAllProjects().then((response) =>{
            console.log(response.data);
            this.setState({ 
                projects: response.data //updates component state with fetched data
            })
        });
    }

    render(){
        //const [isMenuOpen, setMenuOpen] = useState(false);
        return (
            <div className={styles.mainPageContainer}>
                <div className={styles.mainPage}>
                    <div className={styles.topBar}>
                        <div className={styles.topBarLeftItems}>
                            <a href='/createProject'>
                                <img src={getImageUrl('mainPage/addIcon.png')} alt='Create New Project' />
                            </a>
                        </div>
                        <h1 className={styles.topBarTitle}> Projects List</h1>
                        <div className={styles.topBarRightItems}> 
                            {/* <ul 
                            className={`${styles.menuItems} 
                            ${isMenuOpen && styles.isMenuOpen}`}
                            onClick={() => setMenuOpen(false)}> */}
                            <ul>
                                <img src={getImageUrl('mainPage/filterIcon.png')} alt='Filter projects'/>
                            </ul>
                        </div>
                    </div>
                    <div className={styles.projectsContainer}>
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
                </div>
            </div>
        )
    }
}

export default ProjectComponent;
