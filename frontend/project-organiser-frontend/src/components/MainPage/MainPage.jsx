import React from 'react';
import ProjectService from '../../api/service/projectService';
import moment from 'moment';

import styles from './MainPage.module.css';
import {getImageUrl} from "../../utils";

const computeStatusImageFilepath = (status) => {
    const statusIcons = {
        NOT_STARTED: 'mainPage/icons/not_started.png',
        IN_PROGRESS: 'mainPage/icons/in_progress.png',
        COMPLETED: 'mainPage/icons/completed.png',
        MODIFYING: 'mainPage/icons/modifying.png',
        ON_HOLD: 'mainPage/icons/on_hold.png'
    };
    return getImageUrl(statusIcons[status]);
}

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
                        <div className={styles.gridBody}>{
                            this.state.projects.map(project => {
                                const statusIconPath = computeStatusImageFilepath(project.status);
                                return (
                                <div key={String(project._id)} className={styles.gridItem}>
                                    {/* <div>{String(project._id).slice(-4)}</div> */}
                                    <div className={styles.gridTopSegment}>
                                        <img src={statusIconPath} alt={project.status}/>
                                        <div className={styles.projectTitle}>{project.name}</div>
                                        <img src={getImageUrl('mainPage/threeDotsIcon.png')} alt="More info"/>
                                    </div>
                                    {project.image && (
                                    <img src={project.image} alt={`${project.name} preview`} className={styles.projectImage} />
                                    )}
                                    <a href={project.githubUrl} className={styles.githubButton}> GITHUB </a>
                                    {/* <div>{moment(project.startTime.date).format("YYYY-MM-DD HH:mm")}</div>
                                    <div>{project.githubUrl}</div> */}
                                    <div className={styles.projectCategories}>
                                        {project.categories && project.categories.length > 0 ? (
                                            project.categories.map((category, index) => (
                                            <span key={index} className={styles.projectCategory}>
                                                {category}
                                            </span>
                                            ))
                                        ) : (
                                            <span>No categories available</span>
                                        )}
                                    </div>
                                </div>
                            )})
                        }</div>
                    </div>
                </div>
            </div>
        )
    }
}

export default ProjectComponent;
